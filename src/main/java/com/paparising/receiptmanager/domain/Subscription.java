package com.paparising.receiptmanager.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.iocs.portal.domain.User;
import com.iocs.portal.domain.enumeration.AgreementStatus;
import com.paparising.receiptmanager.domain.enumeration.SubscriptionType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A subscription.
 */
@Entity
@Table(name = "subscription")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Subscription implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private SubscriptionType type;
    
    @ManyToOne
    private Creator creator;
    
    @ManyToOne
    private Marketplace marketplace;

    @Column(name = "created_date")
    private ZonedDateTime createdDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public ZonedDateTime getCreated_date() {
        return createdDate;
    }

    public Subscription created_date(ZonedDateTime created_date) {
        this.createdDate = created_date;
        return this;
    }

    public void setCreated_date(ZonedDateTime created_date) {
        this.createdDate = created_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Subscription receipt = (Subscription) o;
        if(receipt.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, receipt.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Receipt{" +
            "id=" + id +
            ", created_by='" + createdBy + "'" +
            ", created_date='" + createdDate + "'" +
            '}';
    }
}
