package com.paparising.receiptmanager.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "onlineOrder")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "edition_code")
    private String editionCode;

    @Column(name = "pricing_duration")
    private String pricingDuration;
    
	@ManyToOne
    @JsonIgnore
    private Payload payload;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getEditionCode() {
		return editionCode;
	}

	public void setEditionCode(String editionCode) {
		this.editionCode = editionCode;
	}

	public String getPricingDuration() {
		return pricingDuration;
	}

	public void setPricingDuration(String pricingDuration) {
		this.pricingDuration = pricingDuration;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((editionCode == null) ? 0 : editionCode.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((pricingDuration == null) ? 0 : pricingDuration.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (editionCode == null) {
			if (other.editionCode != null)
				return false;
		} else if (!editionCode.equals(other.editionCode))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pricingDuration == null) {
			if (other.pricingDuration != null)
				return false;
		} else if (!pricingDuration.equals(other.pricingDuration))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", editionCode=" + editionCode
				+ ", pricingDuration=" + pricingDuration + ", payload="
				+ payload + "]";
	}

}
