package joachim.lejeune.peditrack.dto.factory;

import joachim.lejeune.peditrack.dto.PatientDto;
import joachim.lejeune.peditrack.model.patient.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientDtoFactory {
    public PatientDtoFactory() {
    }

    public PatientDto convert(Patient patient) {
        PatientDto dto = new PatientDto();
        dto.setId(patient.getId());
        dto.setName(patient.getName());
        dto.setFirstname(patient.getFirstName());
        dto.setPhoneNum(patient.getNumTel());
        dto.setBirthdate(patient.getBirthdate());
        return dto;
    }
}
