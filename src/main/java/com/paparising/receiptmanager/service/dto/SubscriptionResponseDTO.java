package com.paparising.receiptmanager.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SubscriptionResponseDTO {

    @NotNull
    private boolean success;
    
    @NotNull
    @Size(min = 1, max = 50)
    private String accountIdentifier;
    
    @NotNull
    @Size(min = 1, max = 50)
    private String errorCode; 
    
    @NotNull
    @Size(min = 1, max = 50)
    private String message;

    public SubscriptionResponseDTO() {
    }

	public SubscriptionResponseDTO(boolean success, String accountIdentifier,
			String errorCode, String message) {
		super();
		this.success = success;
		this.accountIdentifier = accountIdentifier;
		this.errorCode = errorCode;
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((accountIdentifier == null) ? 0 : accountIdentifier
						.hashCode());
		result = prime * result
				+ ((errorCode == null) ? 0 : errorCode.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + (success ? 1231 : 1237);
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
		SubscriptionResponseDTO other = (SubscriptionResponseDTO) obj;
		if (accountIdentifier == null) {
			if (other.accountIdentifier != null)
				return false;
		} else if (!accountIdentifier.equals(other.accountIdentifier))
			return false;
		if (errorCode == null) {
			if (other.errorCode != null)
				return false;
		} else if (!errorCode.equals(other.errorCode))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (success != other.success)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubscriptionResponseDTO [success=" + success
				+ ", accountIdentifier=" + accountIdentifier + ", errorCode="
				+ errorCode + ", message=" + message + "]";
	}



}
