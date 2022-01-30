package com.playtomic.tests.wallet.exceptions;

public class ErrorMessage {
	
	 //Messages to display to user
    public static final String NO_WALLET_FOUND = "No wallet with id %s exists in the system.";
    public static final String ERROR_CREATE_WALLET = "Error creating the wallet";
    public static final String ERROR_PAYMENT = "Error creating the Payment";
    public static final String ERROR_SMALL_AMOUNT = "Error with the PaymentProvider, review the data of your payment and try again.";
}
