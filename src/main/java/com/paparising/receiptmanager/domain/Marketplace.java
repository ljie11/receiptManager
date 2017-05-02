package com.paparising.receiptmanager.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "creator")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Marketplace implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "baseUrl")
    private String baseUrl;

    @Column(name = "partner")
    private String partner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Marketplace marketplace = (Marketplace) o;
        if(marketplace.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, marketplace.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Marketplace [id=" + id + ", baseUrl=" + baseUrl + ", partner=" + partner + "]";
    }

}
