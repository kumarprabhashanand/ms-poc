package kpit.poc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

@Entity(name="ms_users")
public class User {
	@Id
	@Column(name="user_id")
	@ApiModelProperty(dataType="int", notes="Database generated user ID")
	private int userID;
	
	@Column(name="user_name")
	@ApiModelProperty(dataType="String", notes="User Name")
	private String userName;
	
	@Column(name="email")
	@ApiModelProperty(dataType="String", notes="Email of the user")
	private String email;
	
	@Column(name="phone_no")
	@ApiModelProperty(dataType="String", notes="Phone no of the user")
	private String phone;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(int userID, String userName, String email, String phone) {
		this.userID = userID;
		this.userName = userName;
		this.email = email;
		this.phone = phone;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}