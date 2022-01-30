package com.playtomic.tests.wallet.service.impl;


import java.math.BigDecimal;
import java.net.URI;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.ActiveProfiles;

import com.playtomic.tests.wallet.exceptions.StripeAmountTooSmallException;
import com.playtomic.tests.wallet.exceptions.StripeServiceException;
import com.playtomic.tests.wallet.services.StripeService;

/**
 * This test is failing with the current implementation.
 *
 * How would you test this?
 */

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
public class StripeServiceTest {
	
	
    URI testUri = URI.create("http://how-would-you-test-me.localhost");
	
	//@InjectMocks
    StripeService s = new StripeService(testUri, testUri, new RestTemplateBuilder());
    
    @Test
    public void test_exception() {
        Assertions.assertThrows(StripeAmountTooSmallException.class, () -> {
            s.charge("4242 4242 4242 4242", new BigDecimal(5));
        });
    }

    @Test
    public void test_ok() throws StripeServiceException {
        s.charge("4242 4242 4242 4242", new BigDecimal(15));
    }
}
