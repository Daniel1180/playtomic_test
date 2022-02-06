package com.playtomic.tests.wallet.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NonNull;

public class ChargeRequest {
	
	public ChargeRequest(@NonNull String creditCardNumber, @NonNull BigDecimal amount) {
    	this.creditCardNumber = creditCardNumber;
    	this.amount = amount;
	}

	@NonNull
    @JsonProperty("credit_card")
    String creditCardNumber;

    @NonNull
    @JsonProperty("amount")
    BigDecimal amount;

}
