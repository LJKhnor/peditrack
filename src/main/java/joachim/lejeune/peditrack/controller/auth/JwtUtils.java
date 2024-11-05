package joachim.lejeune.peditrack.controller.auth;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.SignatureException;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String jwtSecret; // Clé secrète pour signer les tokens

    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs; // Durée de validité du token

    // Génère un JWT basé sur les informations d'authentification
    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername())) // Le nom d'utilisateur comme sujet du token
                .setIssuedAt(new Date()) // Date de création du token
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) // Date d'expiration
                .signWith(SignatureAlgorithm.HS512, jwtSecret) // Signature avec la clé secrète
                .compact();
    }

    // Récupère le nom d'utilisateur à partir du token JWT
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    // Valide le JWT
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true; // Si le parsing réussit, le token est valide
        } catch (MalformedJwtException e) {
            System.err.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.err.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.err.println("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("JWT claims string is empty: " + e.getMessage());
        }

        return false; // Si une exception est levée, le token n'est pas valide
    }
}
