package com.lansrod.api.service;

import com.lansrod.api.entity.Company;
import com.lansrod.api.repository.CompanyRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Philemon Globlehi <philemon.globlehi@gmail.com>
 */
@Data
@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public Optional<Company> getCompany(final Long id) {
        return companyRepository.findById(id);
    }

    public Iterable<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public void deleteCompany(final Long id) {
        companyRepository.deleteById(id);
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }
}
