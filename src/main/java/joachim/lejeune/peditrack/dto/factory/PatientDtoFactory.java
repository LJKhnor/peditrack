package joachim.lejeune.peditrack.dto.factory;

import joachim.lejeune.peditrack.dto.PatientDto;
import joachim.lejeune.peditrack.model.patient.Health;
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
        dto.setPhoneNum(patient.getPhoneNum());
        dto.setBirthdate(patient.getBirthdate());
        dto.setEmail(patient.getEmail());
        dto.setAddress(patient.getAddress());
        dto.setPersonOfContact(patient.getPersonOfContact());
        dto.setPersonOfContactPhoneNumber(patient.getPersonOfContactPhoneNumber());
        dto.setReferenceBy(patient.getReferenceBy());
        dto.setDoctor(patient.getDoctor());
        dto.setMutual(patient.getMutual());
        dto.setComments(patient.getComments());

        return dto;
    }
}
