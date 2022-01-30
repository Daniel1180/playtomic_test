package com.playtomic.tests.wallet.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.playtomic.tests.wallet.exceptions.CustomException;
import com.playtomic.tests.wallet.model.Payment;
import com.playtomic.tests.wallet.model.PaymentRepository;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.model.WalletRepository;
import com.playtomic.tests.wallet.services.StripeService;
import com.playtomic.tests.wallet.services.WalletService;
import com.playtomic.tests.wallet.services.impl.PaymentServiceImpl;

@RunWith(SpringRunner.class)
public class PaymentServiceTest {
	
	public static final String USER_TEST1 = "USER TEST 1";
    
	public static final String TEST_CURRENCY = "EUR";
    public static final String WALLET_ID = "1";
    public static final String AMOUNT = "10";
    public static final String CREDIT_CARD = "2457230016543489";
    
    
	PaymentServiceImpl paymentServiceImpl;
	
    @Mock
    PaymentRepository paymentRepository;
    
    @Mock
    WalletService walletService;
    
    @Mock
    StripeService stripeService;
    
    @Mock
    WalletRepository walletRepository;
    
    Payment pay1;
    
    Wallet wallet1;
    
    @Before
    public void setUp() {
    	wallet1 = new Wallet(USER_TEST1, TEST_CURRENCY, new BigDecimal(0));
    	wallet1.setId(1);
    	
    	//Payments wallet1
    	pay1 = new Payment(new BigDecimal(10), wallet1, TEST_CURRENCY);
    	pay1.setId(1);
        
        //Create Payment
        Optional<Wallet> walletOptional = Optional.of((Wallet) wallet1);
        Mockito.<Optional<Wallet>>when(walletRepository.findById(Integer.valueOf(1))).thenReturn(walletOptional);
        Mockito.doNothing().when(stripeService).charge(CREDIT_CARD,  new BigDecimal(AMOUNT));
        Mockito.when(walletRepository.save(Mockito.any(Wallet.class))).thenReturn(wallet1);
        Mockito.when(paymentRepository.save(Mockito.any(Payment.class))).thenReturn(pay1);
        
    }
	
	
    @Test
    public void createPayment() throws CustomException {
    	paymentServiceImpl = new PaymentServiceImpl(paymentRepository, walletService, stripeService);
    	Payment pay1 = paymentServiceImpl.createPayment(TEST_CURRENCY, WALLET_ID, AMOUNT, CREDIT_CARD);
    	
        assertNotNull(pay1);
        assertNotNull(pay1.getWallet());
        
        assertTrue(pay1.getWallet().getUserId().equals(wallet1.getUserId()) );
    }
    
}
