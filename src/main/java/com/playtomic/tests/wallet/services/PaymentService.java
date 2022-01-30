package com.playtomic.tests.wallet.services;

import java.util.List;

import com.playtomic.tests.wallet.exceptions.CustomException;
import com.playtomic.tests.wallet.model.Payment;

public interface PaymentService {
	
	/**Method getPaymentsByWalletId: Obtain the list of payments associated to a wallet
	 * @param walletId: Id value of the wallet
	 * @return List of payments associated to that wallet
	 * @throws CustomException
	 */
	public List<Payment> getPaymentsByWalletId(String walletId) throws CustomException;
    
	
	/**Method createPayment: Create a payment associated to a specific wallet.
	 * @param currency: Currency used for the payment
	 * @param walletId: Wallet associated to that payment
	 * @param amount: Amount of the payment
	 * @param crediCard: Credit card used to pay.
	 * @return Payment object.
	 * @throws CustomException
	 */
	public Payment createPayment(String currency, String walletId,String amount,String crediCard) throws CustomException;
	
}
