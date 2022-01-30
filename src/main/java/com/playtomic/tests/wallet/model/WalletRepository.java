package com.playtomic.tests.wallet.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
}

