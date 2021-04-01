package com.lansrod.api.validation;

import com.lansrod.api.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Philemon Globlehi <philemon.globlehi@gmail.com>
 */
public class UniqueSirenValidator implements ConstraintValidator<UniqueSiren, String> {
    @Autowired
    CompanyRepository companyRepository;

    @Override
    public void initialize(UniqueSiren constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !this.companyRepository.findCompanyBySiren(s);
    }
}
