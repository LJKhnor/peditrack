package joachim.lejeune.peditrack.service;

import joachim.lejeune.peditrack.bodyDto.PatientBodyDto;
import joachim.lejeune.peditrack.controller.auth.UserDetailsImpl;
import joachim.lejeune.peditrack.model.patient.Patient;
import joachim.lejeune.peditrack.model.user.User;
import joachim.lejeune.peditrack.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void shouldCreatePatient() {
        // Arrange
        PatientBodyDto patientBodyDto = new PatientBodyDto();
        patientBodyDto.setName("John");
        patientBodyDto.setFirstname("Doe");
        patientBodyDto.setEmail("john.doe@example.com");
        patientBodyDto.setBirthdate("2023-11-26T10:15:30Z");

        UserDetailsImpl userDetails = mock(UserDetailsImpl.class);
        User user = new User();
        when(userDetails.getUser()).thenReturn(user);

        Patient savedPatient = new Patient();
        savedPatient.setName("John");
        savedPatient.setFirstName("Doe");
        when(patientRepository.save(any(Patient.class))).thenReturn(savedPatient);

        // Act
        Patient patient = patientService.createPatient(patientBodyDto, userDetails);

        // Assert
        assertNotNull(patient);
        assertEquals("John", patient.getName());
        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    void shouldGetAllPatients() {
        // Arrange
        when(patientRepository.findAll()).thenReturn(List.of(new Patient(), new Patient()));

        // Act
        List<Patient> patients = patientService.getAllPatients();

        // Assert
        assertNotNull(patients);
        assertEquals(2, patients.size());
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void shouldGetPatientById() {
        // Arrange
        Long patientId = 1L;
        Patient patient = new Patient();
        patient.setId(patientId);
        when(patientRepository.getReferenceById(patientId)).thenReturn(patient);

        // Act
        Patient retrievedPatient = patientService.getPatientById(patientId);

        // Assert
        assertNotNull(retrievedPatient);
        assertEquals(patientId, retrievedPatient.getId());
        verify(patientRepository, times(1)).getReferenceById(patientId);
    }

    @Test
    void shouldUpdatePatient() {
        // Arrange
        Long patientId = 1L;
        Patient existingPatient = new Patient();
        existingPatient.setId(patientId);
        existingPatient.setName("Old Name");

        PatientBodyDto updatedPatientDto = new PatientBodyDto();
        updatedPatientDto.setName("New Name");

        when(patientRepository.findById(patientId)).thenReturn(Optional.of(existingPatient));
        when(patientRepository.save(any(Patient.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Patient updatedPatient = patientService.updatePatient(patientId, updatedPatientDto);

        // Assert
        assertNotNull(updatedPatient);
        assertEquals("New Name", updatedPatient.getName());
        verify(patientRepository, times(1)).findById(patientId);
        verify(patientRepository, times(1)).save(existingPatient);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentPatient() {
        // Arrange
        Long patientId = 1L;
        PatientBodyDto updatedPatientDto = new PatientBodyDto();
        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> patientService.updatePatient(patientId, updatedPatientDto));

        assertEquals("Patient not found with id " + patientId, exception.getMessage());
        verify(patientRepository, times(1)).findById(patientId);
    }

    @Test
    void shouldDeletePatient() {
        // Arrange
        Long patientId = 1L;
        when(patientRepository.existsById(patientId)).thenReturn(true);

        // Act
        patientService.deletePatient(patientId);

        // Assert
        verify(patientRepository, times(1)).existsById(patientId);
        verify(patientRepository, times(1)).deleteById(patientId);
    }
    @Test
    void shouldThrowExceptionWhenDeletingNonExistentPatient() {
        // Arrange
        Long patientId = 1L;
        when(patientRepository.existsById(patientId)).thenReturn(false);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> patientService.deletePatient(patientId));

        assertEquals("Patient not found with id " + patientId, exception.getMessage());
        verify(patientRepository, times(1)).existsById(patientId);
        verify(patientRepository, never()).deleteById(patientId);
    }

    @Test
    void shouldFindPatientsByUser() {
        // Arrange
        User user = new User();
        when(patientRepository.findByUser(user)).thenReturn(List.of(new Patient(), new Patient()));

        // Act
        List<Patient> patients = patientService.findByUser(user);

        // Assert
        assertNotNull(patients);
        assertEquals(2, patients.size());
        verify(patientRepository, times(1)).findByUser(user);
    }

    @Test
    void createPatient() {
    }

    @Test
    void getAllPatients() {
    }

    @Test
    void getPatientById() {
    }

    @Test
    void updatePatient() {
    }

    @Test
    void deletePatient() {
    }

    @Test
    void findByUser() {
    }
}