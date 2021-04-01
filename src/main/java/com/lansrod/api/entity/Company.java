package com.lansrod.api.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.lansrod.api.serialization.SerializationGroup;
import com.lansrod.api.validation.Create;
import com.lansrod.api.validation.UniqueSiren;
import com.lansrod.api.validation.UniqueSiret;
import com.lansrod.api.validation.Update;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Philemon Globlehi <philemon.globlehi@gmail.com>
 */
@Entity
@Table(name = "company")
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(SerializationGroup.Summary.class)
    private Long id;

    @Column(name="social_reason")
    @NotBlank( groups = {Create.class})
    @Length(min = 5, max = 100, groups = {Create.class})
    @JsonView(SerializationGroup.Summary.class)
    private String socialReason;

    @Column(name="siren")
    @UniqueSiren(groups = {Create.class, Update.class})
    @NotBlank( groups = {Create.class})
    @JsonView(SerializationGroup.Summary.class)
    private String siren;

    @Column(name="siret")
    @UniqueSiret(groups = {Create.class, Update.class})
    @NotBlank( groups = {Create.class})
    @JsonView(SerializationGroup.Summary.class)
    private String siret;

    @Column(name="address")
    @Length(min = 5, max = 100, groups = {Create.class})
    @JsonView(SerializationGroup.Summary.class)
    private String address;

    public Company() {
    }

    public Long getId() {
        return id;
    }

    public String getSocialReason() {
        return socialReason;
    }

    public void setSocialReason(String socialReason) {
        this.socialReason = socialReason;
    }

    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", socialReason='" + socialReason + '\'' +
                ", siren='" + siren + '\'' +
                ", siret='" + siret + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
