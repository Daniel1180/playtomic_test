package com.playtomic.tests.wallet.services;

import java.util.List;
import com.playtomic.tests.wallet.exceptions.CustomException;
import com.playtomic.tests.wallet.model.Wallet;

public interface WalletService {

	/**Method findAll: Retrieve all the wallets found in the database. 
	 * @return List of wallets
	 * @throws CustomException
	 */
	public List<Wallet> findAll() throws CustomException;
    
	/**Method findById: Returns the specific wallet searched by the id.
	 * @param id: Id of the wallet
	 * @return Wallet object
	 * @throws CustomException
	 */
	public Wallet findById(Integer id) throws CustomException;
    
	/**Method createWallet: Create a new wallet.
	 * @param wallet: Object Wallet
	 * @return Wallet object
	 * @throws CustomException
	 */
	public Wallet createWallet(Wallet wallet) throws CustomException;
    
	/**Method updateWalletAmount: Update the amount of the wallet credit.
	 * @param wallet: Object Wallet
	 * @param amount: Amount of the payment.
	 * @return Wallet object
	 * @throws CustomException
	 */
	public Wallet updateWalletAmount(Wallet wallet,String amount) throws CustomException;
}
