package com.lansrod.api.entity;

/**
 * @author Philemon Globlehi <philemon.globlehi@gmail.com>
 */
public class Company {
    private Long id;
    private String socialReason;
    private String siren;
    private String siret;
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
