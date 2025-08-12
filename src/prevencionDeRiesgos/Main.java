package prevencionDeRiesgos;

import java.time.LocalDate;


public class Main {
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalDate fechaHoraActual = LocalDate.now();
		String momentoActual = fechaHoraActual.toString();
		
		
		String rutCliente = "16715932-K";
		String dia = "Lunes";
		String hora = "13:54";
		String lugar = "Avenida Accidente";
		String origen = "Por tonto";
		String consecuencias = "Se murió";
		Accidente accidente = new Accidente(rutCliente, dia, 
				hora, lugar, origen, consecuencias);
		
		System.out.println(accidente.toString());
		
		
		//int identificador = momentoActual.hashCode();
		String diaCapacitacion = "Martes";
		String horaCapacitacion = "08:35" ;
		String lugarCapacitacion = "Calle Capacitación";
		String duracion = "60 minutos";
		int cantidadAsistentes = 20;
		
		
		Capacitacion capacitacion = new Capacitacion(rutCliente, diaCapacitacion,  
				horaCapacitacion,lugarCapacitacion,duracion,cantidadAsistentes);
		
		
		System.out.println(capacitacion.toString());

	}
	
}
