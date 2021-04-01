package com.lansrod.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lansrod.api.entity.Company;
import com.lansrod.api.helpers.utils.PageResponseFactory;
import com.lansrod.api.service.CompanyService;
import com.lansrod.api.validation.Create;
import com.lansrod.api.validation.Update;
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
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping(name = "list")
    @ResponseStatus(HttpStatus.OK)
    public String list(@PageableDefault Pageable pageable) throws JSONException, JsonProcessingException {
        Page<Company> companies = this.companyService.getCompanies(pageable);
        return PageResponseFactory.createResponseJsonBody(companies).toString();
    }

    @GetMapping(value = "/{id}", name = "read")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Company> read(@PathVariable Long id) {
        Optional<Company> company = this.companyService.getCompany(id);
        if (!company.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company with id \"" + id + "\" is not found.");
        }

        return company;
    }

    @PostMapping(name = "create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Company> add(@RequestBody @Validated(Create.class) Company company) {
        this.companyService.saveCompany(company);
        return new ResponseEntity<>(company, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", name = "update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Company> update(@RequestBody @Validated(Update.class) Company company, @PathVariable Long id) {
        Optional<Company> response = this.companyService.getCompany(id);
        if (!response.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company with id \"" + id + "\" is not found.");
        }

        this.companyService.saveCompany(company);
        return new ResponseEntity<>(company, HttpStatus.OK);
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
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The request cannot be processed in the current state, check that the resource with the identifier \"" + id + "\" is not linked to any other resources.");
        }
    }

}
