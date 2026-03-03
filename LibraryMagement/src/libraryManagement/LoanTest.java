package libraryManagement;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoanTest {
	User user;
	@BeforeEach
	void setUp() throws Exception {
		user=new User("anselmo","anselmo@tia.es","SOC00001",LocalDate.now());
	}

	@Test
	void test() {
		fail("Not yet implemented");

	}

}
