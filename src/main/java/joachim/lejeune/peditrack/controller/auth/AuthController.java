package joachim.lejeune.peditrack.controller.auth;

import jakarta.servlet.http.HttpServletRequest;
import joachim.lejeune.peditrack.model.user.JwtResponse;
import joachim.lejeune.peditrack.model.user.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final LoginRateLimiter rateLimiter;

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, LoginRateLimiter rateLimiter) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.rateLimiter = rateLimiter;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        String clientKey = request.getRemoteAddr() + ":" + loginRequest.getUsername();

        if (rateLimiter.isBlocked(clientKey)) {
            LOG.warn("Rate limit exceeded for: {}", loginRequest.getUsername());
            return new ResponseEntity<>("Too many failed attempts. Try again later.", HttpStatus.TOO_MANY_REQUESTS);
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

            rateLimiter.recordSuccess(clientKey);
            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getLocation(), roles));
        } catch (Exception e) {
            rateLimiter.recordFailure(clientKey);
            LOG.warn("Authentication failed for user: {}", loginRequest.getUsername());
            return new ResponseEntity<>("Invalid username or password.", HttpStatus.UNAUTHORIZED);
        }
    }
}
