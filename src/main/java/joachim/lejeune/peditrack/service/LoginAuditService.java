package joachim.lejeune.peditrack.service;

import joachim.lejeune.peditrack.model.auth.LoginAudit;
import joachim.lejeune.peditrack.repository.LoginAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class LoginAuditService {

    @Autowired
    private LoginAuditRepository loginAuditRepository;

    @Async
    public void record(String username, String ip, boolean success, String failureReason) {
        loginAuditRepository.save(new LoginAudit(username, ip, success, failureReason));
    }
}
