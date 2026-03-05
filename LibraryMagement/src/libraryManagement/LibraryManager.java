package libraryManagement;

import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.BookNotAvailableException;
import exceptions.InvalidLoanException;
import exceptions.RepeatedUserException;
import exceptions.SanctionedUserException;

public class LibraryManager {
	ArrayList<User> userList = new ArrayList<>();
	ArrayList<Loan> loanList = new ArrayList<>();

	public void registerUser(User newUser) throws RepeatedUserException {
		if (findUser(newUser.getMemberNumber()) != null) {
			throw new RepeatedUserException("The user already Exists");
		} else {
			userList.add(newUser);
		}

	}

	public void registerLoan(String bookCode, String bookTitle, User member, LocalDate loanDate)
			throws InvalidLoanException, SanctionedUserException {
		if (member.isSantioned()) {
			throw new SanctionedUserException("The User is Sanctioned until " + member.getSanctionEndDate().toString());
		} else if (findUser(member.getMemberNumber()) == null) {
			throw new InvalidLoanException("The user does not exists");
		} else {
			Loan auxLoan = new Loan(bookCode, bookTitle, member, loanDate);
			loanList.add(auxLoan);
		}

	}

	public boolean returnBook(String bookCode, LocalDate returnDate)
			throws InvalidLoanException, BookNotAvailableException {
		boolean santioned = false;
		Loan auxLoan = findLoan(bookCode);
		if (auxLoan == null) {
			throw new BookNotAvailableException("Code not found");
		}else if (returnDate.isBefore(auxLoan.getLoanDate())) {
					throw new InvalidLoanException("Return date can not be previous to loan date");
				} else {
					auxLoan.registerReturn(returnDate);
					if (auxLoan.isOverdue()) {
						int sanctionDays = auxLoan.calculateDelayDays();
						auxLoan.getLibraryMember().sanction(sanctionDays);
						santioned = true;
					}else {
						santioned = false;
				}
					return santioned;
			}

	}

	public Loan findLoan(String bookCode) {
		for (Loan loan : loanList) {
			if (loan.getBookCode().equals(bookCode)) {
				return loan;
			}
		}
		return null;

	}

	public User findUser(String memberNumber) {
		for (User user : userList) {
			if (memberNumber.equals(user.getMemberNumber())) {
				return user;
			}
		}
		return null;

	}

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
