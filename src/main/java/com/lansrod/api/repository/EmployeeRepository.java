package com.lansrod.api.repository;

import com.lansrod.api.entity.Company;
import com.lansrod.api.entity.Employee;
import com.lansrod.api.helpers.utils.TypeOfContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

/**
 * @author Philemon Globlehi <philemon.globlehi@gmail.com>
 */
@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
    public boolean findEmployeeBySocialSecurityNumber(String socialSecurityNumber);
    public Iterable<Employee> findEmployeeByCompanyAndHiringDate(Company company, Date hiringDate);
    public Iterable<Employee> findEmployeeByCompanyAndTypeOfContract(Company company, Enum<TypeOfContract> typeOfContract);

}
