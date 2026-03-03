package libraryManagement;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InvalidUserException;
import exceptions.RepeatedUserException;

class LibraryManagerTest {
	LibraryManager libMan;
	@BeforeEach
	void setUp() throws Exception {
		libMan=new LibraryManager();
		libMan.registerUser(new User("Anacleta", "anacleta@tia.es", "SOC00001", LocalDate.now()));
		libMan.registerUser(new User("Bacterio", "bacterio@tia.es", "SOC00002", LocalDate.now()));
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
			System.err.println(e.getMessage());
		}
		assert libMan.getUserList().size()==2;
	}
	@Test
	void testBadMemberNumber1() {
		try {
			libMan.registerUser(new User("Crescencia", "crescen@tia.es", "SO00001", LocalDate.now()));
		} catch (RepeatedUserException | InvalidUserException e) {
			System.err.println(e.getMessage());
		}
		assert libMan.getUserList().size()==2;
	}
	
	
	@Test
	void testRegisterLoan() {
		fail("Not yet implemented");

	}

	@Test
	void testReturnBook() {
		fail("Not yet implemented");

	}

}
