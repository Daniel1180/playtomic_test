package com.playtomic.tests.wallet.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Wallet {

	@Id
	@Column(name = "wallet_id",nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "UserId cannot be null")
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "credit",nullable = false)
	private BigDecimal credit;
	
	@NotNull(message = "Currency should be provided")
	@Column(name = "currency")
	private String currency;

	@Column(name = "last_updated")
	private Date lastUpdated;


	@OneToMany(mappedBy = "wallet", fetch = FetchType.LAZY)
	private List<Payment> payments;

	public Wallet(){
	}

	public Wallet(String userId, String currency, BigDecimal credit) {
		this.userId = userId;
		this.credit = credit;
		this.currency = currency;
		this.lastUpdated = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
