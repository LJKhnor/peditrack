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
        dto.setBirthdate(patient.getBirthdate());
        dto.setName(patient.getName());
        dto.setNumTel(patient.getNumTel());
        dto.setFirstName(patient.getFirstName());
        dto.setDoctorId(patient.getDoctorId());
        dto.setPersonOfContact(patient.getPersonOfContact());
        return dto;
    }
}
