package joachim.lejeune.peditrack.service;

import joachim.lejeune.peditrack.dto.PatientDto;
import joachim.lejeune.peditrack.model.patient.Patient;
import joachim.lejeune.peditrack.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient findOnePatientById(Long id) {
        return patientRepository.findOnePatientById(id);
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
}
