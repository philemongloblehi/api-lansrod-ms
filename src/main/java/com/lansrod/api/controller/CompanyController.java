package com.lansrod.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lansrod.api.entity.Company;
import com.lansrod.api.helpers.utils.PageResponseFactory;
import com.lansrod.api.helpers.utils.TypeOfContract;
import com.lansrod.api.service.CompanyService;
import com.lansrod.api.service.EmployeeService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author Philemon Globlehi <philemon.globlehi@gmail.com>
 */
@RestController
@RequestMapping(name = "api_companies_", path = "/api/v1/rest/companies", produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping(name = "list")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String list(@PageableDefault Pageable pageable) throws JSONException, JsonProcessingException {
        Page<Company> companies = this.companyService.getCompanies(pageable);
        return PageResponseFactory.createResponseJsonBody(companies).toString();
    }

    @GetMapping(value = "/{id}", name = "read")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Optional<Company> read(@PathVariable Long id) {
        Optional<Company> company = this.companyService.getCompany(id);
        if (!company.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company with id \"" + id + "\" is not found.");
        }

        return company;
    }

    @PostMapping(name = "create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<Company> add(@Valid @RequestBody Company company) {
        Company companySaved = this.companyService.saveCompany(company);
        return new ResponseEntity<>(companySaved, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", name = "update")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Company> update(@Valid @RequestBody Company company, @PathVariable Long id) {
        Optional<Company> companyObj = this.companyService.getCompany(id);
        if (!companyObj.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company with id \"" + id + "\" is not found.");
        }

        Company companyUpdated = this.companyService.saveCompany(company);
        return new ResponseEntity<>(companyUpdated, HttpStatus.OK);
    }

    @DeleteMapping (value = "/{id}", name = "delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        Optional<Company> company = this.companyService.getCompany(id);
        if (!company.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company with id \"" + id + "\" is not found.");
        }

        try {
            this.companyService.deleteCompany(id);
        } catch (HttpClientErrorException.Conflict ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The request cannot be processed in the current state, check that the resource with the identifier \"" + id + "\" is not linked to any other resources.");
        }
    }

    @GetMapping(value = "/{companyId}/typeOfContract/{typeOfContract}/maximum/salary", name = "maximum_salary")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public double maxSalaryByTypeOfContract(@PathVariable Long companyId, @PathVariable Enum<TypeOfContract> typeOfContract) {
        Optional<Company> company = this.companyService.getCompany(companyId);
        if (!company.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company with id \"" + companyId + "\" is not found.");
        }

        return this.employeeService.getMaxSalaryByCompanyAndTypeOfContract(company, typeOfContract);
    }

    @GetMapping(value = "/{companyId}/typeOfContract/{typeOfContract}/minimum/salary", name = "minimum_salary")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public double minSalaryByTypeOfContract(@PathVariable Long companyId, @PathVariable Enum<TypeOfContract> typeOfContract) {
        Optional<Company> company = this.companyService.getCompany(companyId);
        if (!company.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company with id \"" + companyId + "\" is not found.");
        }

        return this.employeeService.getMinSalaryByCompanyAndTypeOfContract(company, typeOfContract);
    }

    @GetMapping(value = "/{companyId}/typeOfContract/{typeOfContract}/average/salary", name = "average_salary")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public double averageSalaryByTypeOfContract(@PathVariable Long companyId, @PathVariable Enum<TypeOfContract> typeOfContract) {
        Optional<Company> company = this.companyService.getCompany(companyId);
        if (!company.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company with id \"" + companyId + "\" is not found.");
        }

        return this.employeeService.getAverageSalaryByCompanyAndTypeOfContract(company, typeOfContract);
    }

}
