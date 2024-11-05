package joachim.lejeune.peditrack.controller.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils; // Classe pour gérer les opérations sur le JWT

    @Autowired
    private UserDetailsServiceImpl userDetailsService; // Service pour charger les détails de l'utilisateur

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // Récupère le JWT du header de la requête
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                // Si le JWT est valide, récupère le nom d'utilisateur du token
                String username = jwtUtils.getUserNameFromJwtToken(jwt);

                // Charge les détails de l'utilisateur avec ce nom d'utilisateur
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Crée un objet d'authentification basé sur les détails de l'utilisateur
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Définit l'authentification dans le contexte de sécurité
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            System.err.println("Cannot set user authentication: " + e.getMessage());
        }

        // Continue avec le reste de la chaîne de filtres
        filterChain.doFilter(request, response);
    }
    // Méthode pour extraire le JWT du header "Authorization"
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7); // Extrait le token sans "Bearer "
        }

        return null;
    }
}
