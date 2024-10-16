package joachim.lejeune.peditrack.dto;

import joachim.lejeune.peditrack.model.patient.Health;
import joachim.lejeune.peditrack.model.patient.Patient;

public class PatientRecordDto {
    private PatientDto patientDto;
    private HealthDto healthDto;

    public PatientRecordDto() {
    }

    public PatientDto getPatientDto() {
        return patientDto;
    }

    public void setPatientDto(PatientDto patientDto) {
        this.patientDto = patientDto;
    }

    public HealthDto getHealthDto() {
        return healthDto;
    }

    public void setHealthDto(HealthDto healthDto) {
        this.healthDto = healthDto;
    }
}
