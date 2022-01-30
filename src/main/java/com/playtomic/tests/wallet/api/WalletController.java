package com.playtomic.tests.wallet.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.playtomic.tests.wallet.exceptions.CustomException;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.services.WalletService;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {
    
    private WalletService walletService;
	
    @Autowired
	public WalletController(WalletService walletService) {
		this.walletService = walletService;
	}
   
    @GetMapping("/")
    public List<Wallet> listWallets() throws CustomException {
        return walletService.findAll();
    }

    @GetMapping("/find")
    public Wallet findById(@RequestParam("id") Integer id) throws CustomException {
        return walletService.findById(id);
    }

    @PostMapping("/")
    public Wallet createWallet(@RequestBody Wallet wallet) throws CustomException {
        return walletService.createWallet(wallet);
    }

}
