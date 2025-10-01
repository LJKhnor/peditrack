package joachim.lejeune.peditrack.controller.patient;

import joachim.lejeune.peditrack.bodyDto.PatientBodyDto;
import joachim.lejeune.peditrack.controller.ApplicationControllerIT;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SqlConfig(encoding = "UTF-8")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {
        "/joachim.lejeune.peditrack/clean-all.sql",
        "/joachim.lejeune.peditrack/controller/create_base.sql"})
class PatientControllerIT extends ApplicationControllerIT {

    private final String apiBaseUrl = "/api";

    @Test
    void getPatients() throws Exception {
        mockMvc.perform(get(apiBaseUrl + "/patients").contentType("application/json"))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("lejeune")))
                .andExpect(jsonPath("$[0].firstname", is("joachim")))
                .andExpect(jsonPath("$[0].personOfContact", is("lizen valériane")))
                .andExpect(jsonPath("$[0].doctor", is("Smeets Morgane")))
        ;
    }

    @Test
    void getPatient_byId() throws Exception {
        this.mockMvc.perform(get(apiBaseUrl + "/patients/1"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.patientDto.id", is(1)))
                .andExpect(jsonPath("$.patientDto.name", is("lejeune")))
                .andExpect(jsonPath("$.patientDto.firstname", is("joachim")))
                .andExpect(jsonPath("$.patientDto.personOfContact", is("lizen valériane")))
                .andExpect(jsonPath("$.patientDto.doctor", is("Smeets Morgane")))
        ;
    }

    @Test
    void createPatient() throws Exception {
        // Créer un objet DTO pour simuler les données d'entrée
        PatientBodyDto patientBodyDto = new PatientBodyDto();
        patientBodyDto.setName("Autre");
        patientBodyDto.setFirstname("Personne");
        patientBodyDto.setBirthdate("18-12-1987");
        patientBodyDto.setPhoneNum("0474893021");
        patientBodyDto.setAddress("chaussée de marche 250 5100 jambes");
        patientBodyDto.setBirthdate("1987-12-18T00:00:00.000Z");
        patientBodyDto.setEmail("lejeunejoachim@hotmail.com");
        patientBodyDto.setPersonOfContact("valériane Lizen");
        patientBodyDto.setPersonOfContactPhoneNumber("0477886853");
        patientBodyDto.setReferenceBy("moi");
        patientBodyDto.setDoctor("Smeets Morgane");
        patientBodyDto.setMutual("chrétienne");
        patientBodyDto.setComments("aucun");

        // Convertir en JSON
        String patientJson = objectMapper.writeValueAsString(patientBodyDto);

        // Simuler l'appel POST et vérifier les résultats
        mockMvc.perform(post(apiBaseUrl + "/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patientBodyDto)))  // Envoyer les données JSON
                .andExpect(status().isCreated())  // Vérifier que le statut est 201 (Created)
                .andDo(print())
                .andExpect(jsonPath("$.name").value("Autre"))  // Vérifier que la réponse contient les bonnes données
                .andExpect(jsonPath("$.firstname").value("Personne"))
                .andExpect(jsonPath("$.name").value("Autre"))
                .andExpect(jsonPath("$.birthdate").value("1987-12-18T00:00:00Z"));
    }

    @Test
    void updatePatient() throws Exception {
        PatientBodyDto patientBodyDto = getPatientBodyDto();

        // Convertir en JSON
        String patientJson = objectMapper.writeValueAsString(patientBodyDto);

        // Simuler l'appel POST et vérifier les résultats
        mockMvc.perform(put(apiBaseUrl + "/patients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientJson))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1));
    }

    private static @NotNull PatientBodyDto getPatientBodyDto() {
        PatientBodyDto patientBodyDto = new PatientBodyDto();
        patientBodyDto.setName("Lejeune");
        patientBodyDto.setFirstname("Joachim");
        patientBodyDto.setBirthdate("18/12/1987");
        patientBodyDto.setPhoneNum("0474893021");
        patientBodyDto.setAddress("chaussée de marche 250 5100 jambes");
        patientBodyDto.setBirthdate("1987-12-18T00:00:00.000Z");
        patientBodyDto.setEmail("lejeunejoachim@hotmail.com");
        patientBodyDto.setPersonOfContact("valériane Lizen");
        patientBodyDto.setPersonOfContactPhoneNumber("0477886853");
        patientBodyDto.setReferenceBy("moi");
        patientBodyDto.setDoctor("Smeets Morgane");
        patientBodyDto.setMutual("chrétienne");
        patientBodyDto.setComments("aucun");

        patientBodyDto.setGroupType(1);
        patientBodyDto.setDiabeteType(1);
        patientBodyDto.setWithHeartDisorder(true);
        patientBodyDto.setAllergies("aucune");
        patientBodyDto.setDrugs("aucun");

//        patientBodyDto.setFootType("Creux");
//        patientBodyDto.setSweatType("Hyperhydrose");
//        patientBodyDto.setRemarkType("Cors");
//        patientBodyDto.setCirculationType("Pieds froids");
//        patientBodyDto.setDermatosisType("Eczéma");
//        patientBodyDto.setFootDeformityType("Orteils en marteau");
//        patientBodyDto.setCareDate("2024-10-11");
//        patientBodyDto.setCare("panssements");
//        patientBodyDto.setProductsUsed("habituel");
//        patientBodyDto.setMaterialsUsed("habituel");
//        patientBodyDto.setPossibleInjuries("aucune");
//        patientBodyDto.setAdvice("aucun");
        return patientBodyDto;
    }
}