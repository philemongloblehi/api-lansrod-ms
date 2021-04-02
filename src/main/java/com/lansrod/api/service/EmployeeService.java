package com.lansrod.api.service;

import com.lansrod.api.entity.Company;
import com.lansrod.api.entity.Employee;
import com.lansrod.api.helpers.utils.TypeOfContract;
import com.lansrod.api.repository.CompanyRepository;
import com.lansrod.api.repository.EmployeeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.Optional;

/**
 * @author Philemon Globlehi <philemon.globlehi@gmail.com>
 */
@Data
@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Optional<Employee> getEmployee(final Long id) {
        return employeeRepository.findById(id);
    }

    public Page<Employee> getEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public void deleteEmployee(final Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Iterable<Employee> getEmployeesByCriteria(Optional<Company> company, Date hiringDate, Enum<TypeOfContract> typeOfContract, double salary) {
        if (0 == salary) {
            return this.employeeRepository.findEmployeeByCompanyAndHiringDateAndTypeOfContract(company, hiringDate, typeOfContract);
        }

        return this.employeeRepository.findEmployeeByCompanyAndHiringDateAndTypeOfContractAndSalary(company, hiringDate, typeOfContract, salary);
    }

    public double getMaxSalaryByCompanyAndTypeOfContract(Optional<Company> company, Enum<TypeOfContract> typeOfContract) {
        return this.employeeRepository.findMaxSalaryByCompanyAndTypeOfContract(company, typeOfContract);
    }

    public double getMinSalaryByCompanyAndTypeOfContract(Optional<Company> company, Enum<TypeOfContract> typeOfContract) {
        return this.employeeRepository.findMinSalaryByCompanyAndTypeOfContract(company, typeOfContract);
    }

    public double getAverageSalaryByCompanyAndTypeOfContract(Optional<Company> company, Enum<TypeOfContract> typeOfContract) {
        return this.employeeRepository.findAverageSalaryByCompanyAndTypeOfContract(company, typeOfContract);
    }

}
