package joachim.lejeune.peditrack.controller.auth;

import joachim.lejeune.peditrack.model.user.User;
import joachim.lejeune.peditrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository; // Interface de gestion des utilisateurs en base de données

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Cherche l'utilisateur par son nom d'utilisateur
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        // Convertit l'utilisateur en un UserDetailsImpl
        return UserDetailsImpl.build(user);
    }
}
