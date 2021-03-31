package com.lansrod.api.controller;

import com.lansrod.api.entity.Company;
import com.lansrod.api.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Philemon Globlehi <philemon.globlehi@gmail.com>
 */
@RestController
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping(value = "/companies")
    public Iterable<Company> list() {
        return this.companyService.getCompanies();
    }

    @GetMapping(value = "/companies/{id}")
    public Optional<Company> read(@PathVariable Long id) {
        return this.companyService.getCompany(id);
    }

    @PostMapping(value = "/companies")
    public Company add(@RequestBody Company company) {
        return this.companyService.saveCompany(company);
    }

    @PutMapping(value = "/companies/{id}")
    public Company add(@RequestBody Company company, @PathVariable Long id) {
        return this.companyService.saveCompany(company);
    }

    @DeleteMapping (value = "/companies/{id}")
    public void remove(@PathVariable Long id) {
        this.companyService.deleteCompany(id);
    }

}
