package com.playtomic.tests.wallet.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	List<Payment> findByWallet(Wallet wallet);
}
