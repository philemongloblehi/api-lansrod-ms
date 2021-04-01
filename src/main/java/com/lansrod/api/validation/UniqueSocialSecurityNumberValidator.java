package com.lansrod.api.validation;

import com.lansrod.api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Philemon Globlehi <philemon.globlehi@gmail.com>
 */
public class UniqueSocialSecurityNumberValidator implements ConstraintValidator<UniqueSocialSecurityNumber, String> {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void initialize(UniqueSocialSecurityNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !this.employeeRepository.findEmployeeBySocialSecurityNumber(s);
    }
}
