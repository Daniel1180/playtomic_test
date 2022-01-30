package com.playtomic.tests.wallet.services.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.playtomic.tests.wallet.exceptions.CustomException;
import com.playtomic.tests.wallet.exceptions.ErrorMessage;
import com.playtomic.tests.wallet.exceptions.StripeAmountTooSmallException;
import com.playtomic.tests.wallet.model.Payment;
import com.playtomic.tests.wallet.model.PaymentRepository;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.services.PaymentService;
import com.playtomic.tests.wallet.services.StripeService;
import com.playtomic.tests.wallet.services.WalletService;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	 private PaymentRepository paymentRepository;
	 
	 private WalletService walletService;
	 
	 private StripeService stripeService;
	 
	 @Autowired
	 public PaymentServiceImpl(PaymentRepository paymentRepository,WalletService walletService,
			 StripeService stripeService){
		 this.paymentRepository = paymentRepository;
		 this.walletService = walletService;
		 this.stripeService = stripeService;
	 }

	 @Override
	 public List<Payment> getPaymentsByWalletId(String walletId) throws CustomException {
		 Wallet wallet = walletService.findById(Integer.valueOf(walletId));
		 if(wallet != null) {
			 return paymentRepository.findByWallet(wallet);
		 } else {
			 throw new CustomException(String.format(ErrorMessage.NO_WALLET_FOUND, walletId),HttpStatus.BAD_REQUEST.value());
		 }
	 }

	 @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = CustomException.class)
	 @Override
	 public Payment createPayment(String currency,String walletId,String amount,String creditCard) throws CustomException{
		 try {
			 Wallet wallet = walletService.findById(Integer.valueOf(walletId));
			 
			 stripeService.charge(creditCard, new BigDecimal(amount));
			 
			 //Update wallet
			 wallet = walletService.updateWalletAmount(wallet, amount);
			 
			 //Create payment
			 Payment payment = new Payment(new BigDecimal(amount), wallet, currency);

			 return paymentRepository.save(payment);

		 } catch(NumberFormatException e){
			 throw new CustomException(ErrorMessage.ERROR_PAYMENT,HttpStatus.UNPROCESSABLE_ENTITY.value());
		 } catch (StripeAmountTooSmallException ex) {
			 throw new CustomException(ErrorMessage.ERROR_SMALL_AMOUNT,HttpStatus.UNPROCESSABLE_ENTITY.value());
		 }
	 }
}
