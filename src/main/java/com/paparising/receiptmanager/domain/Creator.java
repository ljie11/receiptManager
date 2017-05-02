package com.paparising.receiptmanager.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.paparising.receiptmanager.domain.enumeration.ReceiptCategory;


@Entity
@Table(name = "creator")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Creator implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private ReceiptCategory category;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "created_date")
    private ZonedDateTime createdDate;

    @Column(name = "total")
    private String total;

    @Column(name = "claimed_amount")
    private Double claimedAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReceiptCategory getCategory() {
        return category;
    }

    public Creator category(ReceiptCategory category) {
        this.category = category;
        return this;
    }

    public void setCategory(ReceiptCategory category) {
        this.category = category;
    }

    public Integer getCreated_by() {
        return createdBy;
    }

    public Creator created_by(Integer created_by) {
        this.createdBy = created_by;
        return this;
    }

    public void setCreated_by(Integer created_by) {
        this.createdBy = created_by;
    }

    public ZonedDateTime getCreated_date() {
        return createdDate;
    }

    public Creator created_date(ZonedDateTime created_date) {
        this.createdDate = created_date;
        return this;
    }

    public void setCreated_date(ZonedDateTime created_date) {
        this.createdDate = created_date;
    }

    public String getTotal() {
        return total;
    }

    public Creator total(String total) {
        this.total = total;
        return this;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Double getClaimed_amount() {
        return claimedAmount;
    }

    public Creator claimed_amount(Double claimed_amount) {
        this.claimedAmount = claimed_amount;
        return this;
    }

    public void setClaimed_amount(Double claimed_amount) {
        this.claimedAmount = claimed_amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Creator receipt = (Creator) o;
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
            ", category='" + category + "'" +
            ", created_by='" + createdBy + "'" +
            ", created_date='" + createdDate + "'" +
            ", total='" + total + "'" +
            ", claimed_amount='" + claimedAmount + "'" +
            '}';
    }
}
