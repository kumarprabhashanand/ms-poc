package kpit.poc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kpit.poc.error.AccountNotFoundException;
import kpit.poc.model.Account;
import kpit.poc.service.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@GetMapping("/accounts")
	public List<Account> getAllAccounts() {
		return accountService.getAllAccounts();
	}
	
	@GetMapping("/accounts/{accountNumber}")
	public Account getAccount(@PathVariable int accountNumber) {
		Account account = accountService.getAccount(accountNumber);
		if(account==null)
			throw new AccountNotFoundException("Account Number : "+accountNumber);
		return account;		
	}
	
	@PostMapping("/accounts")
	public ResponseEntity<Account> addAccount(@Valid @RequestBody Account account) {
		Account updatedAccount = accountService.addAccount(account);
		return new ResponseEntity<Account>(updatedAccount, HttpStatus.CREATED);
	}
	
	@PutMapping("/accounts/{accountNumber}")
	public ResponseEntity<Account> updateAccount(@PathVariable int accountNumber, @Valid @RequestBody Account account) {
		Account updatedAccount = accountService.updateAccount(accountNumber, account);
		if(updatedAccount==null)
			return ResponseEntity.notFound().build();
		return new ResponseEntity<Account>(updatedAccount, HttpStatus.OK);
	}
	
	@DeleteMapping("/accounts/{accountNumber}")
	public String deleteAccount(@PathVariable int accountNumber) {
		return accountService.deleteAccount(accountNumber);
	}
	
}