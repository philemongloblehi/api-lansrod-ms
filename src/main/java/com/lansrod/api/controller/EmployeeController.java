package com.lansrod.api.controller;

import com.lansrod.api.entity.Company;
import com.lansrod.api.entity.Employee;
import com.lansrod.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Philemon Globlehi <philemon.globlehi@gmail.com>
 */
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/employees")
    public Iterable<Employee> list() {
        return this.employeeService.getEmployees();
    }

    @GetMapping(value = "/employees/{id}")
    public Optional<Employee> read(@PathVariable Long id) {
        return this.employeeService.getEmployee(id);
    }

    @PostMapping(value = "/employees")
    public Employee add(@RequestBody Employee company) {
        return this.employeeService.saveEmployee(company);
    }

    @PutMapping(value = "/companies/{id}")
    public Employee add(@RequestBody Employee company, @PathVariable Long id) {
        return this.employeeService.saveEmployee(company);
    }

    @DeleteMapping (value = "/employees/{id}")
    public void remove(@PathVariable Long id) {
        this.employeeService.deleteEmployee(id);
    }
}
