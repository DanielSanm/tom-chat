package br.com.tomchat.models;

public class User {

	private String firstName;
	private String lastName;
	private String nickName;
	private String birthDate;
	private String email;
	private String phone;
	private String password;

	public User(String firstName, String lastName, String nickName, String birthDate, String email, String phone,
			String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
		this.birthDate = birthDate;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
