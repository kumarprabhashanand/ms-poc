package kpit.poc.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class Account {
	//auto generated
	int accountNumber;
	
	@NotNull
	@Size(min=2, message="Name should have atleast 2 characters")
	String name;
	
	@NotNull
	@Size(min=10, message="Address should have atleast 10 characters")
	String address;
	
	@NotNull
	@Min(value=100000, message="PIN Code must be of 6 digits")
	@Max(value=999999, message="PIN Code must be of 6 digits")
	int pincode;
	
	@NotNull
	@PositiveOrZero(message="Account balace must be greater than or equal to zero")
	double accountBalance;
	
	@NotNull
	@Email(message="Invalid email address")
	String email;
	
	@NotNull
	@Size(min=10, max=13, message="Phone no should be of 10 to 13 digits")
	String phoneNo;

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
}