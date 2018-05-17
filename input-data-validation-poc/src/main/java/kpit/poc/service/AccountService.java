package kpit.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kpit.poc.dao.AccountDAO;
import kpit.poc.model.Account;

@Service
public class AccountService {
	
	@Autowired
	AccountDAO accountDAO;
	
	public List<Account> getAllAccounts() {
		return accountDAO.getAllAccounts();
	}
	
	public Account getAccount(int accountNumber) {
		return accountDAO.getAccount(accountNumber);
	}
	
	public Account addAccount(Account account) {
		return accountDAO.addAccount(account);
	}
	
	public Account updateAccount(int accountNumber, Account account) {
		return accountDAO.updateAccount(accountNumber, account);
	}
	
	public String deleteAccount(int accountNumber) {
		return accountDAO.deleteAccount(accountNumber);
	}
}