package libraryManagement;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Prueba {

	public static void main(String[] args) {

		LocalDate	fecha1	= LocalDate.of(2024, 3, 1);
		LocalDate	fecha2	= LocalDate.of(2024, 3, 10);

		long			dias		= ChronoUnit.DAYS.between(fecha1, fecha2);

		System.out.println("Diferencia en días: " + dias);
	}

}
