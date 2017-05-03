package com.paparising.receiptmanager.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.paparising.receiptmanager.config.Constants;
import com.paparising.receiptmanager.domain.Creator;
import com.paparising.receiptmanager.domain.Marketplace;
import com.paparising.receiptmanager.domain.Payload;

public class SubscriptionDTO {

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String type;
    
    @NotNull
    private Marketplace marketplace;
    
    @NotNull
    private Creator creator;
    
    @NotNull
    private Payload payload;


    public SubscriptionDTO() {
    }


	public SubscriptionDTO(String type, Marketplace marketplace,
			Creator creator, Payload payload) {
		super();
		this.type = type;
		this.marketplace = marketplace;
		this.creator = creator;
		this.payload = payload;
	}@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result
				+ ((marketplace == null) ? 0 : marketplace.hashCode());
		result = prime * result + ((payload == null) ? 0 : payload.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		SubscriptionDTO other = (SubscriptionDTO) obj;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (marketplace == null) {
			if (other.marketplace != null)
				return false;
		} else if (!marketplace.equals(other.marketplace))
			return false;
		if (payload == null) {
			if (other.payload != null)
				return false;
		} else if (!payload.equals(other.payload))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubscriptionDTO [type=" + type + ", marketplace=" + marketplace
				+ ", creator=" + creator + ", payload=" + payload + "]";
	}


	


}
