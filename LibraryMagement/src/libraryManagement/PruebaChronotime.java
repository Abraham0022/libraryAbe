package libraryManagement;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PruebaChronotime {

	public static void main(String[] args) {

		LocalDate	fecha1	= LocalDate.of(2024, 12, 30);
		LocalDate	fecha2	= LocalDate.of(2025, 1, 1);

		long			dias		= ChronoUnit.DAYS.between(fecha1, fecha2);

		System.out.println("Diferencia en días: " + dias);
	}

}
