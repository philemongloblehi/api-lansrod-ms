package com.lansrod.api.entity;

import com.lansrod.api.helpers.utils.TypeOfContract;

import java.util.Date;

/**
 * @author Philemon Globlehi <philemon.globlehi@gmail.com>
 */
public class Employee {
    private Long id;
    private String lastName;
    private String firstName;
    private String socialSecurityNumber;
    private Date hiringDate;
    private TypeOfContract typeOfContract;
    private double salary;
    private Company company;

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public Date getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(Date hiringDate) {
        this.hiringDate = hiringDate;
    }

    public TypeOfContract getTypeOfContract() {
        return typeOfContract;
    }

    public void setTypeOfContract(TypeOfContract typeOfContract) {
        this.typeOfContract = typeOfContract;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
                ", hiringDate=" + hiringDate +
                ", typeOfContract=" + typeOfContract +
                ", salary=" + salary +
                ", company=" + company +
                '}';
    }
}
