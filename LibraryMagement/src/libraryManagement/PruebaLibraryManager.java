package libraryManagement;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;

import exceptions.InvalidUserException;
import exceptions.RepeatedUserException;

public class PruebaLibraryManager {

	public static void main(String[] args) {
		LibraryManager libMan;
			libMan=new LibraryManager();
			try {
				libMan.registerUser(new User("Anacleta", "anacleta@tia.es", "SOC00001", LocalDate.now()));
				libMan.registerUser(new User("Bacterio", "bacterio@tia.es", "SOC00002", LocalDate.now()));
			
				libMan.getUserList().contains("SOC00002");
			
			} catch (RepeatedUserException | InvalidUserException e) {
				
				e.printStackTrace();
			}
			
		

	}

}
