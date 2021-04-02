package com.lansrod.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lansrod.api.entity.Company;
import com.lansrod.api.entity.Employee;
import com.lansrod.api.helpers.utils.PageResponseFactory;
import com.lansrod.api.helpers.utils.TypeOfContract;
import com.lansrod.api.service.CompanyService;
import com.lansrod.api.service.EmployeeService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

/**
 * @author Philemon Globlehi <philemon.globlehi@gmail.com>
 */
@RestController
@RequestMapping(name = "api_employees_", path = "/api/v2/rest/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    CompanyService companyService;

    @GetMapping(name = "list")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String list(@PageableDefault Pageable pageable) throws JSONException, JsonProcessingException {
        Page<Employee> employees = this.employeeService.getEmployees(pageable);
        return PageResponseFactory.createResponseJsonBody(employees).toString();
    }

    @GetMapping(value = "/{id}", name = "read")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Optional<Employee> read(@PathVariable Long id) {
        Optional<Employee> employee = this.employeeService.getEmployee(id);
        if (!employee.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with id \"" + id + "\" is not found.");
        }

        return employee;
    }

    @PostMapping(name = "create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<Employee> add(@Valid @RequestBody Employee employee) {
        verifySalary(employee.getSalary());
        Employee employeeSaved = this.employeeService.saveEmployee(employee);
        return new ResponseEntity<>(employeeSaved, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{id}", name = "update", method = {RequestMethod.PUT, RequestMethod.PATCH})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Employee> update(@Valid @RequestBody Employee employee, @PathVariable Long id) {
        Optional<Employee> employeeObj = this.employeeService.getEmployee(id);
        if (!employeeObj.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with id \"" + id + "\" is not found.");
        }

        verifySalary(employee.getSalary());
        verifyTypeOfContract(employeeObj.get().getTypeOfContract(), employee.getTypeOfContract());
        Employee employeeUpdated = this.employeeService.saveEmployee(employee);
        return new ResponseEntity<>(employeeUpdated, HttpStatus.OK);
    }

    @DeleteMapping (value = "/{id}", name = "delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        Optional<Employee> employee = this.employeeService.getEmployee(id);
        if (!employee.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with id \"" + id + "\" is not found.");
        }

        try {
            this.employeeService.deleteEmployee(id);
        } catch (HttpClientErrorException.Conflict ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The request cannot be processed in the current state, check that the resource with the identifier \"" + id + "\" is not linked to any other resources.");
        }
    }

    @GetMapping(value = "/companyId/{companyId}/hiringDate/{hiringDate}/typeOfContract/{typeOfContract}/salary/{salary}", name = "list_by_criteria")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Iterable<Employee> listByCriteria(@PathVariable Long companyId, @PathVariable Date hiringDate, @PathVariable Enum<TypeOfContract> typeOfContract, @PathVariable double salary) {
        Optional<Company> company = this.companyService.getCompany(companyId);
        if (!company.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company with id \"" + companyId + "\" is not found.");
        }

        return this.employeeService.getEmployeesByCriteria(company, hiringDate, typeOfContract, salary);
    }

    private static void verifyTypeOfContract(Enum<TypeOfContract> typeOfContractActual, Enum<TypeOfContract> newTypeOfContract) {
        if (TypeOfContract.CDD == typeOfContractActual && TypeOfContract.ALTERNATION == newTypeOfContract || TypeOfContract.CDI == typeOfContractActual && TypeOfContract.ALTERNATION == newTypeOfContract) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee type of contract \"" + newTypeOfContract + "\" is not valid.");
        }
    }

    private static void verifySalary(Double salary) {
        if (0 >= salary) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee salary \"" + salary + "\" is not valid.");
        }
    }
}
