package com.playtomic.tests.wallet.services.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.playtomic.tests.wallet.exceptions.CustomException;
import com.playtomic.tests.wallet.exceptions.ErrorMessage;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.model.WalletRepository;
import com.playtomic.tests.wallet.services.WalletService;

@Service
public class WalletServiceImpl implements WalletService {
	
	private WalletRepository walletRepository;
	
	@Autowired
	public WalletServiceImpl(WalletRepository walletRepository){
		this.walletRepository = walletRepository;
	}
	
	@Override
	public List<Wallet> findAll() throws CustomException{
		return walletRepository.findAll();
	}
	
	@Override
	public Wallet findById(Integer id) throws CustomException{
		Optional<Wallet> optionalWallet =  walletRepository.findById(id);
		return optionalWallet.orElseThrow(() -> new CustomException(String.format(ErrorMessage.NO_WALLET_FOUND,id.toString()),HttpStatus.BAD_REQUEST.value()));
	}
	
    @Transactional
    @Override
    public Wallet createWallet(Wallet wallet) throws CustomException{
        try {
        	wallet.setCredit(new BigDecimal(0));
        	wallet.setLastUpdated(new Date());
            return walletRepository.save(wallet);
        } catch (IllegalArgumentException e){
            throw new CustomException(ErrorMessage.ERROR_CREATE_WALLET,HttpStatus.BAD_REQUEST.value());
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = CustomException.class)
	@Override
	public Wallet updateWalletAmount(Wallet wallet, String amount) throws CustomException {
        //update wallet
        wallet.setCredit(wallet.getCredit().add(new BigDecimal(amount)));
        wallet.setLastUpdated(new Date());
        
		return walletRepository.save(wallet);
	}
}
