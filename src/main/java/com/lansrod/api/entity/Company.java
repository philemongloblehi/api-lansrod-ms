package com.lansrod.api.entity;

import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Philemon Globlehi <philemon.globlehi@gmail.com>
 */
@Entity
@Table(name = "company")
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="social_reason")
    @NotBlank(message = "socialReason must be not blank")
    @NotNull(message = "socialReason must be not null")
    @Length(min = 5, max = 100, message = "lastName too long or too short.")
    private String socialReason;

    @Column(name="siren")
    @NotBlank(message = "siren must be not blank")
    @NotNull(message = "siren must be not null")
    private String siren;

    @Column(name="siret")
    @NotBlank(message = "siret must be not blank")
    @NotNull(message = "siret must be not null")
    private String siret;

    @Column(name="address")
    @Length(min = 5, max = 100, message = "lastName too long or too short.")
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
