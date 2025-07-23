/**
 * @author Yerko Osorio
 */
package prevencionDeRiesgos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Clase utilitaria para validaciones
 */
public final class Validacion {
	 // Definimos el formateador para el formato "DD/MM/AAAA"
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    // Definimos el formateador para el patrón "HH:MM"
    private static final DateTimeFormatter FORMATTER_HHMM = DateTimeFormatter.ofPattern("HH:mm");
	
    /**
     * No deja instanciar la clase -> Ejemplo como cuando usamos la clase Math.round(float a)
     */
	private Validacion() {
		
	}
	
	
	/**
	 * Valida el formato y transforma la fecha ingresada en formato DD/MM/AAAA en LocalDate.
	 * 
	 * @param fecha
	 * @return la fecha en tipo LocalDate. Retorna null si el formato no es válido.
	 */
	public static LocalDate validarFecha(String fecha) {
	        try {
	            return LocalDate.parse(fecha, FORMATTER);
	        } catch (DateTimeParseException e) {
	            return null; // O lanzar una excepción personalizada, dependiendo de la lógica de tu aplicación
	        }
	    }
	 
	/**
	 * Transforma la fecha LocalDate a String con formato DD/MM/AAAA.
	 * 
	 * @param fecha
	 * @return la fecha en tipo String. Retorna null si el formato no es válido.
	 */
	 public static String transformarFechaAstring(LocalDate fecha) {
	        if (fecha == null) {
	            return null; // O una cadena vacía, dependiendo de la lógica
	        }
	        return fecha.format(FORMATTER);
	    }
	 
	 /**
	  * Valida el formato y transforma la fecha ingresada en formato DD/MM/AAAA en LocalDate.
	  * 
	  * @param hora
	  * @return la hora en tipo LocalTime. Retorna null si el formato no es válido.
	  */
	 public static LocalTime validarHora(String hora) {

	        try {
	            return LocalTime.parse(hora, FORMATTER_HHMM);
	
	        } catch (DateTimeParseException e) {
	        	return null;
	        }
	    }
	 
	 /**
	  * Transforma la hora LocalTime a String con formato HH:MM.
	  * 
	  * @param hora
	  * @return la hora en tipo String. Retorna null si el formato no es válido.
	  */
	 public static String transformarHoraAstring(LocalTime hora) {

	    	if (hora == null) {
	            return null; // O una cadena vacía, dependiendo de la lógica
	        }
	        return hora.format(FORMATTER_HHMM);

	    }
	
	 /**
	     * Calcula la edad en años a partir de una fecha de nacimiento en formato String (dd/MM/yyyy).
	     *
	     * @param fechaNacimientoStr La fecha de nacimiento en formato String (ej. "25/12/1990").
	     * @return La edad en años. Retorna -1 si el formato de fecha es inválido, si la fecha es nula,
	     * o si la fecha de nacimiento es en el futuro.
	     */
	    public static int calcularEdad(String fechaNacimientoStr) {
	        LocalDate fechaNacimiento = validarFecha(fechaNacimientoStr); 

	        if (fechaNacimiento == null) {
	            System.err.println("Error: Formato de fecha de nacimiento inválido o fecha no válida.");
	            return -1; // O lanza una excepción, según tu estrategia de manejo de errores.
	        }

	        LocalDate fechaActual = LocalDate.now();

	        if (fechaNacimiento.isAfter(fechaActual)) {
	            System.err.println("Error: La fecha de nacimiento no puede ser en el futuro.");
	            return -1;
	        }

	        // Calcular la diferencia entre las dos fechas
	        Period periodo = Period.between(fechaNacimiento, fechaActual);

	        // Obtener los años de la diferencia
	        return periodo.getYears();
	    }
	
    /**
     * Valida un RUT chileno (número + dígito verificador).
     * El formato esperado del String es "XXXXXXXX-X" o "XXXXXXXXX" (sin guion).
     * El método es flexible y acepta el RUT como un int para el número.
     *
     * @param rut El RUT como un String (ej. "12345678-9" o "12345678").
     * @return true si el RUT es válido, false en caso contrario.
     */
    public static boolean validarRut(String rut) {
        // Eliminar puntos y guiones del RUT (si existen)
        rut = rut.replace(".", "").replace("-", "");

        // Validar que el RUT no esté vacío y tenga al menos 2 caracteres (número + DV)
        if (rut.length() < 2) {
            return false;
        }

        // Separar número y dígito verificador
        String rutSinDV = rut.substring(0, rut.length() - 1);
        char dvIngresado = rut.charAt(rut.length() - 1);

        // Intentar convertir el número del RUT a int. Si falla, no es un RUT válido.
        int rutNumerico;
        try {
            rutNumerico = Integer.parseInt(rutSinDV);
        } catch (NumberFormatException e) {
            return false; // Contiene caracteres no numéricos en la parte del número
        }

        // Validar el dígito verificador
        char dvCalculado = calcularDV(rutNumerico);

        // Comparar el dígito verificador ingresado con el calculado (ignorando mayúsculas/minúsculas para 'K')
        return Character.toUpperCase(dvIngresado) == Character.toUpperCase(dvCalculado);
    }

    /**
     * Calcula el dígito verificador de un RUT chileno.
     *
     * @param rutNumerico El número del RUT sin el dígito verificador.
     * @return El dígito verificador calculado ('0'-'9' o 'K').
     */
    private static char calcularDV(int rutNumerico) {
        int suma = 0;
        int multiplicador = 2;

        while (rutNumerico > 0) {
            suma += (rutNumerico % 10) * multiplicador;
            rutNumerico /= 10;
            multiplicador++;
            if (multiplicador == 8) {
                multiplicador = 2;
            }
        }

        int dv = 11 - (suma % 11);

        if (dv == 11) {
            return '0';
        } else if (dv == 10) {
            return 'K';
        } else {
            return (char) (dv + '0'); // Convierte el int a su caracter ASCII
        }
    }
    

}
