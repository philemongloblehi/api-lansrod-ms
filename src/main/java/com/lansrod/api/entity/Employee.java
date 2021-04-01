package com.lansrod.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.lansrod.api.helpers.utils.TypeOfContract;
import com.lansrod.api.serialization.SerializationGroup;
import com.lansrod.api.validation.Create;
import com.lansrod.api.validation.UniqueSocialSecurityNumber;
import com.lansrod.api.validation.Update;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Philemon Globlehi <philemon.globlehi@gmail.com>
 */
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(SerializationGroup.Summary.class)
    private Long id;

    @Column(name="last_name")
    @NotBlank( groups = {Create.class})
    @Length(min = 5, max = 100, groups = {Create.class})
    @JsonView(SerializationGroup.Summary.class)
    private String lastName;

    @Column(name="first_name")
    @NotBlank( groups = {Create.class})
    @Length(min = 5, max = 100, groups = {Create.class})
    @JsonView(SerializationGroup.Summary.class)
    private String firstName;

    @Column(name="social_security_number")
    @UniqueSocialSecurityNumber(groups = {Create.class, Update.class})
    @NotBlank( groups = {Create.class})
    @JsonView(SerializationGroup.Summary.class)
    private String socialSecurityNumber;

    @Column(name="hiring_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @NotBlank( groups = {Create.class})
    @JsonView(SerializationGroup.Summary.class)
    private Date hiringDate;

    @Column(name="type_of_contract")
    @JsonView(SerializationGroup.Summary.class)
    private TypeOfContract typeOfContract;

    @Column(name="salary")
    @Min(value = 1)
    @JsonView(SerializationGroup.Summary.class)
    private double salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
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
