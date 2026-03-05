package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InvalidLoanException;
import exceptions.InvalidUserException;
import libraryManagement.Loan;
import libraryManagement.User;

class LoanTest {
	User user;
	LocalDate today;
	Loan loan;

	@BeforeEach
	void setUp() throws Exception {
		today = LocalDate.now();
		user = new User("anselmo", "anselmo@tia.es", "SOC00001", LocalDate.now());
		loan = new Loan("LIB1234", "Title", user, today.minusDays(5));
	}

	@Test
	void shouldCreateLoanCorrectly() throws InvalidLoanException {
		assertEquals("LIB1234", loan.getBookCode());
		assertEquals("Clean Code", loan.getBookTitle());
		assertEquals(today.minusDays(1), loan.getLoanDate());
		assertEquals(today.minusDays(1).plusDays(14), loan.getDueDate());

	}
  // ❌ Título vacío
  @Test
  void shouldThrowExceptionIfBookTitleEmpty() {

     Exception ex= assertThrows(InvalidLoanException.class, () ->
          new Loan("LIB1234", "", user, today)
      );
      assertTrue(ex.getMessage().contains("empty"));
  }

  // ❌ Usuario nulo
  @Test
  void shouldThrowExceptionIfUserNull() {

      assertThrows(InvalidLoanException.class, () ->
          new Loan("LIB1234", "Title", null, today)
      );
  }

  
  @Test
  void shouldThrowExceptionIfLoanDateInFuture() {

  	Exception ex= assertThrows(InvalidLoanException.class, () ->
          new Loan("LIB0001", "Title", user, today.plusDays(1))
      );
  	System.err.println(ex.getMessage());
  }

	@Test
	void testSetBookCodeOK() {
		try {
		
			loan.setBookCode("LIB0001");
		} catch (InvalidLoanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals("LIB0001", loan.getBookCode());

	}

	@Test
	void testSetBookCodeBadLetters() {
		Exception ex = assertThrows(InvalidLoanException.class, () -> {
			loan.setBookCode("L0001");
		});
		// System.err.println(ex.getMessage());
		assertTrue(ex.getMessage().contains("Code"));

	}

	@Test
	void testSetBookCodeBadNumber() {
		Exception ex = assertThrows(InvalidLoanException.class, () -> {
			loan.setBookCode("LIB1");
		});
		// System.err.println(ex.getMessage());
		assertTrue(ex.getMessage().contains("Code"));
	}
	 // NOT OVERDUE
  @Test
  void shouldNotBeOverdue() throws InvalidLoanException {

       assertFalse(loan.isOverdue());
  }

  // IS OVERDUE
  @Test
  void shouldBeOverdue() throws InvalidLoanException {
      loan = new Loan("LIB0001", "Title", user, today.minusDays(20));

      assertTrue(loan.isOverdue());
  }

  // ✅ calculateDelayDays() cuando no hay retraso
  @Test
  void shouldReturnZeroDelayIfNotOverdue() throws InvalidLoanException {

       assertEquals(0, loan.calculateDelayDays());
  }

  // ✅ calculateDelayDays() cuando hay retraso
  @Test
  void shouldCalculateDelayCorrectly() throws InvalidLoanException {

      loan = new Loan("LIB0001", "Title", user, today.minusDays(20));

      int delay = loan.calculateDelayDays();

      assertTrue(delay > 0);
      assertEquals(6, loan.calculateDelayDays());
  }
  // ❌ registerReturn con null
  @Test
  void shouldThrowExceptionIfReturnDateNull() throws InvalidLoanException {

      assertThrows(InvalidLoanException.class, () ->
          loan.registerReturn(null)
      );
  }

  // ❌ registerReturn con fecha futura
  @Test
  void shouldThrowExceptionIfReturnDateInFuture() throws InvalidLoanException {

      assertThrows(InvalidLoanException.class, () ->
          loan.registerReturn(today.plusDays(1))
      );
  }

  // ✅ registerReturn correcto
  @Test
  void shouldRegisterReturnCorrectly() throws InvalidLoanException {

      loan.registerReturn(today.minusDays(1));

      assertTrue(loan.calculateDelayDays() >= 0);
  }
}
