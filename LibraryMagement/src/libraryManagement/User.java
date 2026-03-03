package libraryManagement;

import java.time.LocalDate;

import exceptions.InvalidUserException;

public class User {
	private String name, email, memberNumber;
	private LocalDate registrationDate, sanctionEndDate;
	private boolean santioned;
	// regular expresion to check emails
	private String emailRegex = "[\\w.-]+@[\\w.-]+\\.\\w{2,3}";

//regular expresion to check memberNumber
	private String memberRegex = "SOC\\d{5}";

	public User(String name, String email, String memberNumber, LocalDate registrationDate) throws InvalidUserException {
		super();
		this.name = name;
		setEmail(email);
		setMemberNumber(memberNumber);
		this.registrationDate = registrationDate;
		this.santioned = false;
		this.sanctionEndDate=null;

	}

	public void sanction(int days) {
		santioned=true;
		sanctionEndDate=LocalDate.now().plusDays(days);
	}
	
	public void liftSanction() {
		santioned=false;
		sanctionEndDate=null;
	}
	
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getMemberNumber() {
		return memberNumber;

	}

	public LocalDate getRegistrationDate() {
		return registrationDate;

	}

	public LocalDate getSanctionEndDate() {
//		if(sanctionEndDate==null) {
//			return LocalDate.of(0,0,1900);
//		}else {
//		  return sanctionEndDate;
//		}
		return sanctionEndDate;
	}

	public boolean isSantioned() {
		return santioned;

	}

	public void setName(String name) {
		this.name = name;

	}

	public void setEmail(String email) throws InvalidUserException {
		if (email.matches(emailRegex)) {
			this.email = email;
		} else {
			throw new InvalidUserException("Bad email format");
		}

	}

	public void setMemberNumber(String memberNumber) throws InvalidUserException {
		if (memberNumber.matches(memberRegex)) {
			this.memberNumber = memberNumber;
		} else {
			throw new InvalidUserException("Bad member number format");
		}

	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "User "+memberNumber+" name=" + name + ", email=" + email + ", registrationDate="
				+ registrationDate + ", sanctionEndDate=" + sanctionEndDate + ", santioned=" + santioned;
	}
	


	

	
}
