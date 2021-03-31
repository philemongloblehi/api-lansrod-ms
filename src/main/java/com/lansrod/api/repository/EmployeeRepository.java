package com.lansrod.api.repository;

import com.lansrod.api.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Philemon Globlehi <philemon.globlehi@gmail.com>
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
