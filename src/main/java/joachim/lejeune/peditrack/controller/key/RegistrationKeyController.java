package joachim.lejeune.peditrack.controller.key;

import joachim.lejeune.peditrack.service.RegistrationKeyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/keys")
public class RegistrationKeyController {

    private final RegistrationKeyService registrationKeyService;

    public RegistrationKeyController(RegistrationKeyService registrationKeyService) {
        this.registrationKeyService = registrationKeyService;
    }

    @PostMapping("/generate")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<String>> generateKeys(
            @RequestParam(defaultValue = "1") int count,
            @RequestParam(defaultValue = "30") int validityDays) {

        if (count < 1 || count > 50) {
            return ResponseEntity.badRequest().build();
        }
        if (validityDays < 1 || validityDays > 365) {
            return ResponseEntity.badRequest().build();
        }

        List<String> keys = registrationKeyService.generateKeys(count, validityDays);
        return ResponseEntity.ok(keys);
    }
}
