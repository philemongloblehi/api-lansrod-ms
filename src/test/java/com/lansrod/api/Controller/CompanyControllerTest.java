package com.lansrod.api.Controller;

import com.lansrod.api.repository.CompanyRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.Matchers.not;


/**
 * @author Philemon Globlehi <philemon.globlehi@gmail.com>
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class CompanyControllerTest {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    private MockMvc mvc;

    private static JSONObject json;

    @BeforeAll
    public void setUp() throws Exception {
        companyRepository.deleteAll();
        json = null;
    }

    @AfterAll
    public void tearDown() throws Exception {
        companyRepository.deleteAll();
        json = null;
    }

    @Test
    @Order(value = 0)
    public void testThatCanCreateCompanies() throws Exception {
        MvcResult result = this.mvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v2/rest/companies")
                        .content("{"
                                + 	"\"socialReason\": \"Lansrod\","
                                + 	"\"siren\": \"SIREN-001\","
                                + 	"\"siret\": \"SIRET-001-PARIS\","
                                + 	"\"address\": \"France, Paris\""
                                + "}"
                        )
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.socialReason", is("Lansrod")))
                .andExpect(jsonPath("$.siren", is("SIREN-001")))
                .andExpect(jsonPath("$.siret", is("SIRET-001-PARIS")))
                .andExpect(jsonPath("$.address", is("France, Paris")))
                .andReturn()
                ;
        // Keep the previous object created for future tests
        json = new JSONObject(result.getResponse().getContentAsString());
    }

    @Test
    @Order(value = 1)
    public void testThatCanReadCompanies() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders
                        .get("/api/v2/rest/companies/" + json.getInt("id"))
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(json.getInt("id"))))
                .andExpect(jsonPath("$.socialReason", is("Lansrod")))
                .andExpect(jsonPath("$.siren", is("SIREN-001")))
                .andExpect(jsonPath("$.siret", is("SIRET-001-PARIS")))
                .andExpect(jsonPath("$.address", is("France, Paris")))
        ;
    }

    @Test
    @Order(value = 2)
    public void testThatCanListCompanies() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders
                        .get("/api/v2/rest/companies")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page", is(1)))
                .andExpect(jsonPath("$.itemPerPage", is(10)))
                .andExpect(jsonPath("$.itemOffset", is(0)))
                .andExpect(jsonPath("$.fullyItems", is(1)))
                .andExpect(jsonPath("$.pageNumber", is(1)))
                .andExpect(jsonPath("$.items", hasSize(1)))
                .andExpect(jsonPath("$.items[0].id", is(json.getInt("id"))))
                .andExpect(jsonPath("$.items[0].socialReason", is("Lansrod")))
                .andExpect(jsonPath("$.items[0].siren", is("SIREN-001")))
                .andExpect(jsonPath("$.items[0].siret", is("SIRET-001-PARIS")))
                .andExpect(jsonPath("$.items[0].address", is("France, Paris")))
        ;
    }

    @Test
    @Order(value = 3)
    public void testThatCanUpdateCompanies() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders
                        .patch("/api/v2/rest/companies/" + json.getInt("id"))
                        .content("{"
                                + 	"\"socialReason\": \"Lansrod\","
                                + 	"\"siren\": \"SIREN-001\","
                                + 	"\"siret\": \"SIRET-001-ABIDJAN\","
                                + 	"\"address\": \"Cote d'Ivoire, Abidjan\""
                                + "}"
                        )
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.socialReason", is("Lansrod")))
                .andExpect(jsonPath("$.siren", is("SIREN-001")))
                .andExpect(jsonPath("$.siret", is("SIRET-001-ABIDJAN")))
                .andExpect(jsonPath("$.address", is("Cote d'Ivoire, Abidjan")))
        ;
    }

    @Test
    @Order(value = 4)
    public void testThatCanDeleteCompanies() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders
                        .delete("/api/v2/rest/companies/" + json.getInt("id"))
        )
                .andExpect(status().isNoContent());
    }

}
