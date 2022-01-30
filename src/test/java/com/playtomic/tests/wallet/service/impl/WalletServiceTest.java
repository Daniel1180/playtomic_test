package com.playtomic.tests.wallet.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.playtomic.tests.wallet.exceptions.CustomException;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.model.WalletRepository;
import com.playtomic.tests.wallet.services.impl.WalletServiceImpl;

@RunWith(SpringRunner.class)
public class WalletServiceTest {

    public static final String TEST_CURRENCY = "EUR";
    public static final String USER_TEST1 = "USER TEST 1";
    public static final String USER_TEST2 = "USER TEST 2";
    
    Wallet wallet1;
    Wallet wallet2;
    
    @Mock
    WalletRepository walletRepository;
    
    WalletServiceImpl walletServiceImpl;
	
    @Before
    public void setUp() {
    	wallet1 = new Wallet(USER_TEST1, TEST_CURRENCY, new BigDecimal(0));
    	wallet1.setId(1);
    	wallet2 = new Wallet(USER_TEST2,TEST_CURRENCY, new BigDecimal(10));
    	wallet2.setId(2);
    	
    	//walletService.findAll
        Mockito.when(walletRepository.findAll()).thenReturn(Arrays.asList(wallet1, wallet2));
        
        //walletService.findById
        Optional<Wallet> walletOptional = Optional.of((Wallet) wallet2);
        Mockito.<Optional<Wallet>>when(walletRepository.findById(Integer.valueOf(2))).thenReturn(walletOptional);
        
        //CreateWallet
        Mockito.when(walletRepository.save(Mockito.any(Wallet.class))).thenReturn(wallet1);
    }
	
    @Test
    public void testFindAll() throws CustomException {
    	walletServiceImpl = new WalletServiceImpl(walletRepository);
        List<Wallet> walletFound = walletServiceImpl.findAll();
        assertNotNull(walletFound);
        assertTrue(walletFound.size() == 2);
        
        assertTrue(walletFound.get(0).getId().equals(wallet1.getId()) );
        assertTrue(walletFound.get(1).getId().equals(wallet2.getId()) );
    }

    
    @Test
    public void testFindById() throws CustomException {
    	walletServiceImpl = new WalletServiceImpl(walletRepository);
        Wallet walletFound = walletServiceImpl.findById(Integer.valueOf(2));
        assertNotNull(walletFound);
        assertTrue(walletFound.getId().equals(wallet2.getId()) );
    }
    
    @Test
    public void createWallet() throws CustomException {
    	walletServiceImpl = new WalletServiceImpl(walletRepository);
        Wallet walletFound = walletServiceImpl.createWallet(wallet1);
        
        assertNotNull(walletFound);
        assertTrue(walletFound.getId().equals(wallet1.getId()) );
    }
}
