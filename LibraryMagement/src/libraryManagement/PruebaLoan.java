package libraryManagement;

import java.time.LocalDate;

import exceptions.InvalidLoanException;
import exceptions.InvalidUserException;

public class PruebaLoan {

	public static void main(String[] args) {
		try {
			User user=new User("anselmo","anselmo@tia.es","SOC00001",LocalDate.now());
			Loan loan= new Loan("LIB0001","title",user,LocalDate.of(2025,1,2));
			 int delay;
		
//			System.out.println(loan.toString());
//			System.out.println(loan.isOverdue());
//			System.out.println("Delay --> "+loan.calculateDelayDays());
			loan.registerReturn(LocalDate.of(2026,1,2));
			System.out.println(loan.toString());
			delay=loan.calculateDelayDays();
			System.out.println("Delay --> "+delay);
		} catch (InvalidUserException e) {
			e.printStackTrace();
		} catch (InvalidLoanException e) {
			e.printStackTrace();
		}

	}

}
