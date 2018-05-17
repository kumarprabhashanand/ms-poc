package kpit.poc.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import kpit.poc.model.Account;

@Repository
public class AccountDAO {
	private static final List<Account> ACCOUNT_LIST = new ArrayList<>();
	
	static{
		Account account = new Account();
		account.setAccountBalance(10000.00);
		account.setAccountNumber(123456);
		account.setAddress("Pune, India");
		account.setEmail("prabhash@kpit.com");
		account.setName("Prabhash");
		account.setPhoneNo("1234567890");
		account.setPincode(411027);
		ACCOUNT_LIST.add(account);
	}
	
	public List<Account> getAllAccounts() {
		return ACCOUNT_LIST;
	}
	
	public Account getAccount(int accountNumber) {
		for(Account acc : ACCOUNT_LIST) {
			if(acc.getAccountNumber()==accountNumber) {
				return acc;
			}
		}
		return null;
	}
	
	public Account addAccount(Account account) {
		account.setAccountNumber(ACCOUNT_LIST.get(ACCOUNT_LIST.size()-1).getAccountNumber()+1);
		ACCOUNT_LIST.add(account);
		return account;
	}
	
	public Account updateAccount(int accountNumber, Account account) {
		account.setAccountNumber(accountNumber);
		for(int i=0;i<ACCOUNT_LIST.size();i++) {
			if(ACCOUNT_LIST.get(i).getAccountNumber()==accountNumber) {
				ACCOUNT_LIST.set(i, account);
				return account;
			}
		}
		return null;
	}
	
	public String deleteAccount(int accountNumber) {
		for(int i=0;i<ACCOUNT_LIST.size();i++) {
			if(ACCOUNT_LIST.get(i).getAccountNumber()==accountNumber) {
				ACCOUNT_LIST.remove(i);
				return "Account Successfully Deleted.";
			}
		}
		return "Account does not exist.";
	}
}