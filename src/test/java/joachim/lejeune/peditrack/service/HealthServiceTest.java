package joachim.lejeune.peditrack.service;

import joachim.lejeune.peditrack.bodyDto.PatientBodyDto;
import joachim.lejeune.peditrack.model.enums.*;
import joachim.lejeune.peditrack.model.patient.Health;
import joachim.lejeune.peditrack.model.patient.Patient;
import joachim.lejeune.peditrack.repository.HealthRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class HealthServiceTest {
    @InjectMocks
    private HealthService healthService;

    @Mock
    private HealthRepository healthRepository;

    private Patient patient;
    private PatientBodyDto patientBodyDto;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialisation des objets nécessaires
        patient = new Patient();
        patient.setId(1L);

        patientBodyDto = new PatientBodyDto();
        patientBodyDto.setGroupType(GroupType.LOW_RISK);
        patientBodyDto.setDiabeteType(DiabeteType.ONE);
        patientBodyDto.setWithHeartDisorder(true);
        patientBodyDto.setWithBleedingDisorder(false);
        patientBodyDto.setWithThyroideDisorder(true);
        patientBodyDto.setHasHipProsthesis(false);
        patientBodyDto.setHasKneeProsthesis(true);
        patientBodyDto.setHasRecentDiseases(true);
        patientBodyDto.setHasRecentOperations(false);
        patientBodyDto.setAllergies("Pollen");
        patientBodyDto.setDrugs("Aspirin");
        patientBodyDto.setSkinType("Sèche");
        patientBodyDto.setFootType("Creux");
        patientBodyDto.setSweatType("Hyperhidrose");
        patientBodyDto.setCirculationType("Varicosités");
        patientBodyDto.setDermatosisType("Psoriasis");
        patientBodyDto.setNailConditionType("Onychogryphoses");
        patientBodyDto.setFootDeformityType("Hallux varus");
        patientBodyDto.setCareDate("2024-11-26T10:15:30+01:00");
        patientBodyDto.setCare("Basic care");
        patientBodyDto.setProductsUsed("Cream");
        patientBodyDto.setMaterialsUsed("Scissors");
        patientBodyDto.setPossibleInjuries("None");
        patientBodyDto.setAdvice("Wear comfortable shoes");

        // Simuler le comportement du repository
        when(healthRepository.save(any(Health.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void createNewHealth_ShouldMapFieldsCorrectly() {
        // Appel de la méthode à tester
        Health health = healthService.createNewHealth(patient, patientBodyDto);

        // Vérification des champs mappés
        assertEquals(patient, health.getPatient());
        assertEquals(GroupType.LOW_RISK, health.getGroupType());
        assertEquals(DiabeteType.ONE, health.getDiabeteType());
        assertEquals(true, health.isWithHeartDisorder());
        assertEquals(false, health.isWithBleedingDisorder());
        assertEquals(true, health.isWithThyroidDisorder());
        assertEquals(false, health.isHasHipProthesis());
        assertEquals(true, health.isHasKneeProthesis());
        assertEquals(true, health.isHasRecentDiseases());
        assertEquals(false, health.isHasRecentOperation());
        assertEquals("Pollen", health.getAllergies());
        assertEquals("Aspirin", health.getDrugs());
        assertEquals(SkinType.valueForLabel("Sèche"), health.getSkinType());
        assertEquals(FootType.valueForLabel("Creux"), health.getFootType());
        assertEquals(SweatType.valueForLabel("Hyperhidrose"), health.getSweatType());
        assertEquals(CirculationType.valueForLabel("Varicosités"), health.getCirculationType());
        assertEquals(DermatosisType.valueForLabel("Psoriasis"), health.getDermatosisType());
        assertEquals(NailConditionType.valueForLabel("Onychogryphoses"), health.getNailConditionType());
        assertEquals(FootDeformityType.valueForLabel("Hallux varus"), health.getFootDeformityType());
        assertEquals(OffsetDateTime.parse("2024-11-26T10:15:30+01:00"), health.getDateConsultation());
        assertEquals("Basic care", health.getCares());
        assertEquals("Cream", health.getProductsUsed());
        assertEquals("Scissors", health.getMaterialsUsed());
        assertEquals("None", health.getPossibleInjuries());
        assertEquals("Wear comfortable shoes", health.getAdvice());

        // Vérification que la méthode save a été appelée
        verify(healthRepository, times(1)).save(health);
    }
}