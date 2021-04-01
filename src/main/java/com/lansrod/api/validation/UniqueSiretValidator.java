package com.lansrod.api.validation;

import com.lansrod.api.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Philemon Globlehi <philemon.globlehi@gmail.com>
 */
public class UniqueSiretValidator implements ConstraintValidator<UniqueSiret, String> {
    @Autowired
    CompanyRepository companyRepository;

    @Override
    public void initialize(UniqueSiret constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !this.companyRepository.findCompanyBySiret(s);
    }
}
