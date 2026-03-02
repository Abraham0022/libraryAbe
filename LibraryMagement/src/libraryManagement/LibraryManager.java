package libraryManagement;

import java.time.LocalDate;
import java.util.ArrayList;

public class LibraryManager {
	ArrayList<User>	userList	= new ArrayList<>();
	ArrayList<Loan>	loanList	= new ArrayList<>();

	public void registerUser();

	public void registerLoan(String bookCode, String bookTitle, User member, LocalDate loanDate);

	public boolean returnBook(String bookCode, LocalDate returnDate);

	public User findUser(String memberNumber);

	public ArrayList<User> getUserList() {
		return userList;
	}

	public ArrayList<Loan> getLoanList() {
		return loanList;
	}

	@Override
	public String toString() {
		return "LibraryManager [userList=" + userList + ", loanList=" + loanList + "]";
	}

}
