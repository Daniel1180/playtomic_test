package com.playtomic.tests.wallet.services;

import java.math.BigDecimal;
import java.net.URI;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.playtomic.tests.wallet.exceptions.StripeRestTemplateResponseErrorHandler;
import com.playtomic.tests.wallet.exceptions.StripeServiceException;
import com.playtomic.tests.wallet.model.ChargeRequest;

import lombok.NonNull;


/**
 * Handles the communication with Stripe.
 *
 * A real implementation would call to String using their API/SDK.
 * This dummy implementation throws an error when trying to charge less than 10€.
 */
@Service
public class StripeService {

	@NonNull
    private URI chargesUri;

    @NonNull
    private URI refundsUri;

    @NonNull
    private RestTemplate restTemplate;

    public StripeService(@Value("stripe.simulator.charges-uri") @NonNull URI chargesUri,
                         @Value("stripe.simulator.refunds-uri") @NonNull URI refundsUri,
                         @NotNull RestTemplateBuilder restTemplateBuilder) {
        this.chargesUri = chargesUri;
        this.refundsUri = refundsUri;
        this.restTemplate =
                restTemplateBuilder
                .errorHandler(new StripeRestTemplateResponseErrorHandler())
                .build();
    }

    /**
     * Charges money in the credit card.
     *
     * Ignore the fact that no CVC or expiration date are provided.
     *
     * @param creditCardNumber The number of the credit card
     * @param amount The amount that will be charged.
     *
     * @throws StripeServiceException
     */
    public void charge(@NonNull String creditCardNumber, @NonNull BigDecimal amount) throws StripeServiceException {
        ChargeRequest body = new ChargeRequest(creditCardNumber, amount);
        // Object.class because we don't read the body here.
        restTemplate.postForObject(chargesUri, body, Object.class);
    }

    /**
     * Refunds the specified payment.
     */
    public void refund(@NonNull String paymentId) throws StripeServiceException {
        // Object.class because we don't read the body here.
        restTemplate.postForEntity(chargesUri.toString(), null, Object.class, paymentId);
    }
}
