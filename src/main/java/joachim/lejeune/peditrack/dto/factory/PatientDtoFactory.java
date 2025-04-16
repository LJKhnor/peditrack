package joachim.lejeune.peditrack.dto.factory;

import joachim.lejeune.peditrack.NominatimUtility;
import joachim.lejeune.peditrack.dto.PatientDto;
import joachim.lejeune.peditrack.model.patient.Patient;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PatientDtoFactory {
    private static  NominatimUtility nominatimUtility;
    public PatientDtoFactory() {
    }

    public PatientDto convert(Patient patient) throws IOException {
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

        // pas d'appel à nominatim à la création ou à l'update on délègue ça à un Cron
//        if (!patient.getAddress().isEmpty()){
//            dto.setPosition(nominatimUtility.AdresseConverter(patient.getAddress()));
//        }

        return dto;
    }
}
