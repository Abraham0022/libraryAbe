package libraryManagement;

import java.time.LocalDate;

import exceptions.InvalidLoanException;

public class Loan {
	private String bookCode, bookTitle;
	private final String REG_BOOK_CODE = "[A-Z]{3}\\d{4}";
	private User libraryMember;
	public User getLibraryMember() {
		return libraryMember;
	
	}

	public void setLibraryMember(User libraryMember) throws InvalidLoanException {
		if(libraryMember!=null)
			throw new InvalidLoanException("Library member in null");
		this.libraryMember = libraryMember;
	
	}
	private LocalDate loanDate, dueDate, actualReturnDate;

	public Loan(String bookCode, String bookTitle, User member, LocalDate loanDate) throws InvalidLoanException {
		super();
		setBookCode(bookCode);
		setBookCode(bookCode);
		setLibraryMember(member);
		setLoanDate(loanDate);

	}

	public String getBookCode() {
		return bookCode;

	}

	public String getBookTitle() {
		return bookTitle;

	}

	public LocalDate getDueDate() {
		return dueDate;

	}

	public void setBookCode(String bookCode) throws InvalidLoanException {
		if (!bookCode.matches(REG_BOOK_CODE))
			throw new InvalidLoanException("Bad book Code");
		else
			this.bookCode = bookCode;

	}

	public void setBookTitle(String bookTitle) throws InvalidLoanException {
		if (bookTitle.isEmpty())
			throw new InvalidLoanException("Book title is empty");
		else
			this.bookTitle = bookTitle;

	}

	public void setLoanDate(LocalDate loanDate) throws InvalidLoanException {
		if (loanDate == null || loanDate.compareTo(LocalDate.now()) > 0)
			throw new InvalidLoanException("Bad Loan Date");
		else
			this.loanDate = loanDate;

	}
  public void registerReturn(LocalDate date) throws InvalidLoanException {
  	if (date == null) {
  		throw new InvalidLoanException("Bad return date, could not be null");
  	}else if(date.compareTo(LocalDate.now()) > 0)
			throw new InvalidLoanException("Bad return date it is set before the loan date");
		else
			this.actualReturnDate = date;
  }
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;

	}

}
