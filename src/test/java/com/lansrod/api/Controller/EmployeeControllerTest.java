package com.lansrod.api.Controller;

import com.lansrod.api.repository.EmployeeRepository;
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
public class EmployeeControllerTest {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private MockMvc mvc;

    private static JSONObject json;

    @BeforeAll
    public void setUp() throws Exception {
        employeeRepository.deleteAll();
        json = null;
    }

    @AfterAll
    public void tearDown() throws Exception {
        employeeRepository.deleteAll();
        json = null;
    }

    @Test
    @Order(value = 0)
    public void testThatCanCreateEmployees() throws Exception {
        MvcResult result = this.mvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/rest/employees")
                        .content("{"
                                + 	"\"lastName\": \"Philemon\","
                                + 	"\"firstName\": \"Globlehi\","
                                + 	"\"socialSecurityNumber\": \"CIV-225-001\","
                                + 	"\"hiringDate\": \"2021-06-01\","
                                + 	"\"typeOfContract\": \"CDI\","
                                + 	"\"salary\": 3000"
                                + "}"
                        )
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.lastName", is("Philemon")))
                .andExpect(jsonPath("$.firstName", is("Globlehi")))
                .andExpect(jsonPath("$.socialSecurityNumber", is("CIV-225-001")))
                .andExpect(jsonPath("$.hiringDate", is("2021-06-01")))
                .andExpect(jsonPath("$.typeOfContract", is("CDI")))
                .andExpect(jsonPath("$.salary", is(3000.0)))
                .andReturn()
                ;
        // Keep the previous object created for future tests
        json = new JSONObject(result.getResponse().getContentAsString());
    }

    @Test
    @Order(value = 1)
    public void testThatCanReadEmployees() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders
                        .get("/api/v1/rest/employees/" + json.getInt("id"))
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(json.getInt("id"))))
                .andExpect(jsonPath("$.lastName", is("Philemon")))
                .andExpect(jsonPath("$.firstName", is("Globlehi")))
                .andExpect(jsonPath("$.socialSecurityNumber", is("CIV-225-001")))
                .andExpect(jsonPath("$.hiringDate", is("2021-06-01")))
                .andExpect(jsonPath("$.typeOfContract", is("CDI")))
                .andExpect(jsonPath("$.salary", is(3000.0)))
        ;
    }

    @Test
    @Order(value = 2)
    public void testThatCanListEmployees() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders
                        .get("/api/v1/rest/employees")
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
                .andExpect(jsonPath("$.items[0].lastName", is("Philemon")))
                .andExpect(jsonPath("$.items[0].firstName", is("Globlehi")))
                .andExpect(jsonPath("$.items[0].socialSecurityNumber", is("CIV-225-001")))
                .andExpect(jsonPath("$.items[0].hiringDate", is("2021-06-01")))
                .andExpect(jsonPath("$.items[0].typeOfContract", is("CDI")))
                .andExpect(jsonPath("$.items[0].salary", is(3000)))
        ;
    }

    @Test
    @Order(value = 3)
    public void testThatCanUpdateEmployees() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders
                        .patch("/api/v1/rest/employees/" + json.getInt("id"))
                        .content("{"
                                + 	"\"lastName\": \"Philemon\","
                                + 	"\"firstName\": \"Globlehi\","
                                + 	"\"socialSecurityNumber\": \"CIV-225-001\","
                                + 	"\"hiringDate\": \"2021-06-01\","
                                + 	"\"typeOfContract\": \"CDI\","
                                + 	"\"salary\": 6000"
                                + "}"
                        )
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName", is("Philemon")))
                .andExpect(jsonPath("$.firstName", is("Globlehi")))
                .andExpect(jsonPath("$.socialSecurityNumber", is("CIV-225-001")))
                .andExpect(jsonPath("$.hiringDate", is("2021-06-01")))
                .andExpect(jsonPath("$.typeOfContract", is("CDI")))
                .andExpect(jsonPath("$.salary", is(6000.0)))
        ;
    }

    @Test
    @Order(value = 4)
    public void testThatCanDeleteEmployees() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders
                        .delete("/api/v1/rest/employees/" + json.getInt("id"))
        )
                .andExpect(status().isNoContent());
    }

}
