package joachim.lejeune.peditrack.controller.patient;

import com.fasterxml.jackson.databind.ObjectMapper;
import joachim.lejeune.peditrack.bodyDto.PatientBodyDto;
import joachim.lejeune.peditrack.controller.ApplicationControllerIT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SqlConfig(encoding = "UTF-8")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {
        "/joachim.lejeune.peditrack/clean-all.sql",
        "/joachim.lejeune.peditrack/controller/patient/create_patient.sql"})
class PatientControllerIT extends ApplicationControllerIT {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper objectMapper;
    private MockMvc mockMvc;


    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void getPatients() throws Exception {
        this.mockMvc.perform(get("/patients").contentType("application/json"))

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
        this.mockMvc.perform(get("/patients/1"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("lejeune")))
                .andExpect(jsonPath("$.firstname", is("joachim")))
                .andExpect(jsonPath("$.personOfContact", is("lizen valériane")))
                .andExpect(jsonPath("$.doctor", is("Smeets Morgane")))
        ;
    }

    @Test
    void createPatient() throws Exception {
        // Créer un objet DTO pour simuler les données d'entrée
        PatientBodyDto patientBodyDto = new PatientBodyDto();
        patientBodyDto.setName("Autre");
        patientBodyDto.setFirstname("Personne");
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
        mockMvc.perform(post("/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patientBodyDto)))  // Envoyer les données JSON
                .andExpect(status().isCreated())  // Vérifier que le statut est 201 (Created)
                .andDo(print())
                .andExpect(jsonPath("$.name").value("Autre"))  // Vérifier que la réponse contient les bonnes données
                .andExpect(jsonPath("$.firstname").value("Personne"));
    }

    @Test
    @Disabled
    void updatePatient() throws Exception {
        PatientBodyDto patientBodyDto = new PatientBodyDto();
        patientBodyDto.setName("Lejeune");
        patientBodyDto.setFirstname("Joachim");
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


        // Convertir en JSON
        String patientJson = objectMapper.writeValueAsString(patientBodyDto);

        // Simuler l'appel POST et vérifier les résultats
        mockMvc.perform(put("/patients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientJson))
                .andExpect(status().isOk())
                .andDo(print());
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.groupType").value("LOW_RISK"));
    }
}