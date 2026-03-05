package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.BookNotAvailableException;
import exceptions.InvalidLoanException;
import exceptions.InvalidUserException;
import exceptions.RepeatedUserException;
import exceptions.SanctionedUserException;
import libraryManagement.LibraryManager;
import libraryManagement.Loan;
import libraryManagement.User;

class LibraryManagerTest {
	LibraryManager libMan;
	LocalDate today;
	User anacleta;
	
	@BeforeEach
	void setUp() throws Exception {
		today = LocalDate.now();
		libMan=new LibraryManager();
		anacleta=new User("Anacleta", "anacleta@tia.es", "SOC00001", today.minusWeeks(4));
		libMan.registerUser(anacleta);
		libMan.registerUser(new User("Bacterio", "bacterio@tia.es", "SOC00002", LocalDate.now()));
	}
	
	@Test
	void shouldFindUser() {
		assertEquals(anacleta,libMan.findUser("SOC00001"));
	}
	@Test
	void shouldNOTFindUser() {
		assertEquals(null,libMan.findUser("SOC00003"));
	}
	@Test
	void shouldFindLoan() {
		  try {
			  
			libMan.registerLoan("LIB0001", "Mortadelo y Filemon", anacleta, LocalDate.now());
			assertEquals(libMan.findLoan("LIB0001").getBookCode(),"LIB0001");
		} catch (InvalidLoanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SanctionedUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@Test
	void shouldNOTFindLoan() {
		assertEquals(null,libMan.findLoan("LIB0003"));
	}
	
	@Test
	void shouldReturnFalseIfBookReturnedOnTime() throws Exception {


	    LocalDate loanDate = today.minusDays(5);

	    libMan.registerLoan("ABC1234", "Clean Code", anacleta, loanDate);

	    boolean result = libMan.returnBook("ABC1234", today);

	    assertFalse(result);
	    assertFalse(anacleta.isSantioned());
	}
	@Test
	void shouldSanctionUserIfBookReturnedLate() throws Exception {
		 LocalDate loanDate = LocalDate.now().minusDays(20);
		    libMan.registerLoan("ABC1234", "Clean Code", anacleta, loanDate);

		    boolean result = libMan.returnBook("ABC1234", today);


	    assertTrue(result);
	    assertTrue(anacleta.isSantioned());
	}
	
	@Test
	void returnBookShouldThrowBooknotAvailable(){
		try {
			libMan.registerLoan("LIB0001", "Mortadelo y Filemon", anacleta, LocalDate.now());
			Exception ex= assertThrows(BookNotAvailableException.class, ()->{
				libMan.returnBook("LIB9999",today);
			});
		} catch (InvalidLoanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SanctionedUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@Test
	void returnBookShouldThrowInvalidLoan(){
		try {
			libMan.registerLoan("LIB0001", "Mortadelo y Filemon", anacleta, today);
			Exception ex= assertThrows(InvalidLoanException.class, ()->{
				libMan.returnBook("LIB0001",today.minusDays(1));
			});
		} catch (InvalidLoanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SanctionedUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@Test
	void testRegisterUser() {
		try {
			libMan.registerUser(new User("Clemencio", "bacterio@tia.es", "SOC00003", LocalDate.now()));
		} catch (RepeatedUserException | InvalidUserException e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
		assert libMan.getUserList().size()==3;
	}
	
	@Test
	void testRegisterUserRepeated() {
		
		Exception ex=assertThrows(RepeatedUserException.class, () -> {
				libMan.registerUser(new User("Bacterio", "bacterio@tia.es", "SOC00002", today));
    });
	}
	
	@Test
	void testRegisterLoanSanctioned() {
		User u;
		try {
			u = new User("Clemencio", "clemencio@tia.es", "SOC00003", today.minusWeeks(4));
			u.sanction(15);			
			libMan.registerUser(u);
//			libMan.registerLoan("LIB0001","Title",u,today.minusDays(20));
//			libMan.returnBook("LIB0001", today);
			Exception ex=	assertThrows(SanctionedUserException.class, () -> {
				libMan.registerLoan("LIB0001","Title",u,today.minusDays(20));
			});
		} catch (InvalidUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepeatedUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testRegisterLoanNotUser() {
		User u;
		try {
			u = new User("Clemencio", "clemencio@tia.es", "SOC00003", today.minusWeeks(4));
						
			
			Exception ex=assertThrows(InvalidLoanException.class, () -> {
				libMan.registerLoan("LIB0001","Title",u,today.minusDays(20));
			});
		} catch (InvalidUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	@Test
	void shouldRegisterLoanCorrectly() throws Exception {

	   

	    libMan.registerLoan("ABC1234", "Clean Code", anacleta, LocalDate.now());

	    assertEquals(1, libMan.getLoanList().size());
	}
	
	
	
	
	//------- MOVE TO USERTEST
	@Test
	void testBadEmailUser1() {
		try {
			libMan.registerUser(new User("Crescencia", "crescentia.es", "SOC00004", LocalDate.now()));
		} catch (RepeatedUserException | InvalidUserException e) {
			System.err.println(e.getMessage());
		}
		assert libMan.getUserList().size()==2;
	}
	@Test
	void testBadEmailUser2() {
		try {
			libMan.registerUser(new User("Crescencia", "crescen@tiaes", "SOC00004", LocalDate.now()));
		} catch (RepeatedUserException | InvalidUserException e) {
			System.err.println(e.getMessage());
		}
		assert libMan.getUserList().size()==2;
	}
	@Test
	void testBadEmailUser3() {
		try {
			libMan.registerUser(new User("Crescencia", "crescen@tia.este", "SOC00004", LocalDate.now()));
		} catch (RepeatedUserException | InvalidUserException e) {
			System.err.println(e.getMessage());
		}
		assert libMan.getUserList().size()==2;
	}
	@Test
	void testBadEmailUser4() {
		try {
			libMan.registerUser(new User("Crescencia", "crescen@tia.e", "SOC00004", LocalDate.now()));
		} catch (RepeatedUserException | InvalidUserException e) {
			System.err.println(e.getMessage());
		}
		assert libMan.getUserList().size()==2;
	}
	@Test
	void testBadMemberNumber() {
		try {
			libMan.registerUser(new User("Crescencia", "crescen@tia.es", "SOC01", LocalDate.now()));
		} catch (RepeatedUserException | InvalidUserException e) {
			//System.err.println(e.getMessage());
			assertTrue(e.getMessage().contains("number format"));
		}
		assert libMan.getUserList().size()==2;
	}
	
	@Test
	void testBadMemberNumber1() {
		try {
			libMan.registerUser(new User("Crescencia", "crescen@tia.es", "SO00001", LocalDate.now()));
		} catch (InvalidUserException e) {
			assertTrue(e.getMessage().contains("number format"));
			//System.err.println(e.getMessage());
		} catch (RepeatedUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assert libMan.getUserList().size()==2;
	}
	
	
	@Test
	void testRegisterLoanInvalidDate() {
		User crescen;
		try {
			crescen = new User("Crescencia", "crescen@tia.es", "SOC00001", today.plusDays(1));
			Exception ex = assertThrows(InvalidLoanException.class,   () -> {
						libMan.registerLoan("LIB0001","BookTitle",crescen,today);
					}
			);
			System.err.println(ex.getMessage());
			assertTrue(ex.getMessage().contains("Date"));
		} catch (InvalidUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	

	}

	
}
