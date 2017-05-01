package com.paparising.receiptmanager.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.paparising.receiptmanager.domain.enumeration.ReceiptCategory;

/**
 * A Receipt.
 */
@Entity
@Table(name = "receipt")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Receipt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private ReceiptCategory category;

    @Column(name = "created_by")
    private Integer created_by;

    @Column(name = "created_date")
    private ZonedDateTime created_date;

    @Column(name = "total")
    private String total;

    @Column(name = "claimed_amount")
    private Double claimed_amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReceiptCategory getCategory() {
        return category;
    }

    public Receipt category(ReceiptCategory category) {
        this.category = category;
        return this;
    }

    public void setCategory(ReceiptCategory category) {
        this.category = category;
    }

    public Integer getCreated_by() {
        return created_by;
    }

    public Receipt created_by(Integer created_by) {
        this.created_by = created_by;
        return this;
    }

    public void setCreated_by(Integer created_by) {
        this.created_by = created_by;
    }

    public ZonedDateTime getCreated_date() {
        return created_date;
    }

    public Receipt created_date(ZonedDateTime created_date) {
        this.created_date = created_date;
        return this;
    }

    public void setCreated_date(ZonedDateTime created_date) {
        this.created_date = created_date;
    }

    public String getTotal() {
        return total;
    }

    public Receipt total(String total) {
        this.total = total;
        return this;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Double getClaimed_amount() {
        return claimed_amount;
    }

    public Receipt claimed_amount(Double claimed_amount) {
        this.claimed_amount = claimed_amount;
        return this;
    }

    public void setClaimed_amount(Double claimed_amount) {
        this.claimed_amount = claimed_amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Receipt receipt = (Receipt) o;
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
            ", created_by='" + created_by + "'" +
            ", created_date='" + created_date + "'" +
            ", total='" + total + "'" +
            ", claimed_amount='" + claimed_amount + "'" +
            '}';
    }
}
