package com.playtomic.tests.wallet.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.playtomic.tests.wallet.exceptions.CustomException;
import com.playtomic.tests.wallet.model.Payment;
import com.playtomic.tests.wallet.services.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
	
	private PaymentService paymentService;
	
	@Autowired
	public PaymentController(PaymentService paymentService){
		this.paymentService = paymentService;
	}
	
	Logger logger = LoggerFactory.getLogger(PaymentController.class);
	   
	@GetMapping("/")
	public List<Payment> getPaymentsByWalletId(@RequestParam("walletId") String walletId) throws CustomException {
		logger.info("Enter to getPaymentsByWalletId");
		return paymentService.getPaymentsByWalletId(walletId);
	}


	@PostMapping("/")
	public Payment createPayment(@RequestParam("currency") String currency,@RequestParam("walletId") String walletId,
			@RequestParam("amount") String amount,@RequestParam("creditCard") String creditCard) throws CustomException {
		return paymentService.createPayment(currency, walletId, amount,creditCard);
	}

}
