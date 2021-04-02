package com.lansrod.api.repository;

import com.lansrod.api.entity.Company;
import com.lansrod.api.entity.Employee;
import com.lansrod.api.helpers.utils.TypeOfContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

/**
 * @author Philemon Globlehi <philemon.globlehi@gmail.com>
 */
@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
    public Iterable<Employee> findEmployeeByCompanyAndHiringDateAndTypeOfContract(Optional<Company> company, Date hiringDate, Enum<TypeOfContract> typeOfContract);
    public Iterable<Employee> findEmployeeByCompanyAndHiringDateAndTypeOfContractAndSalary(Optional<Company> company, Date hiringDate, Enum<TypeOfContract> typeOfContract, double salary);

    @Query("SELECT max (e.salary) FROM Employee e WHERE e.company = :company and e.typeOfContract = :typeOfContract")
    public double findMaxSalaryByCompanyAndTypeOfContract(@Param("company") Optional<Company> company, @Param("typeOfContract") Enum<TypeOfContract> typeOfContract);

    @Query("SELECT min (e.salary) FROM Employee e WHERE e.company = :company and e.typeOfContract = :typeOfContract")
    public double findMinSalaryByCompanyAndTypeOfContract(@Param("company") Optional<Company> company, @Param("typeOfContract") Enum<TypeOfContract> typeOfContract);

    @Query("SELECT avg (e.salary) FROM Employee e WHERE e.company = :company and e.typeOfContract = :typeOfContract")
    public double findAverageSalaryByCompanyAndTypeOfContract(@Param("company") Optional<Company> company, @Param("typeOfContract") Enum<TypeOfContract> typeOfContract);

}
