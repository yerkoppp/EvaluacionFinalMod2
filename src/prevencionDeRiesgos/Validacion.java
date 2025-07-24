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
	 * Valida el formato y transforma la fecha ingresada en formato DD/MM/AAAA
	 * en LocalDate.
	 * 
	 * @param fecha
	 * @return la fecha en tipo LocalDate. Retorna null si el formato no es
	 *         válido.
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
	 * Valida el formato y transforma la fecha ingresada en formato DD/MM/AAAA
	 * en LocalDate.
	 * 
	 * @param hora
	 * @return la hora en tipo LocalTime. Retorna null si el formato no es
	 *         válido.
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
     * Valida que la longitud de un texto esté dentro del rango especificado.
     * 
     * @param texto Texto a validar (no puede ser null)
     * @param min Longitud mínima permitida (inclusive)
     * @param max Longitud máxima permitida (inclusive)
     * @return El texto validado
     * @throws IllegalArgumentException si el texto es null o su longitud no está en el rango
     */
    public static String validarLargoString(String texto, int min, int max) throws IllegalArgumentException {
        if (texto == null) {
            throw new IllegalArgumentException("El texto no puede ser null");
        }
        String textoLimpio = texto.trim();
        int longitud = textoLimpio.length();
        if (longitud < min || longitud > max) {
            throw new IllegalArgumentException(
                String.format("Longitud inválida: %d. Debe estar entre %d y %d caracteres", 
                             longitud, min, max));
        }
        return texto;
    }

	/**
	 * Valida que un RUT chileno esté en el formato correcto y que su dígito
	 * verificador sea válido según el cálculo del Módulo 11.
	 *
	 * <p>
	 * Formato válido: 99.999.999-9 o 99.999.999-K
	 * </p>
	 *
	 * @param rutIngresado el RUT ingresado por el usuario (incluyendo puntos y
	 *                     guión)
	 * @return el mismo RUT ingresado si es válido
	 * @throws IllegalArgumentException si el RUT está vacío, no tiene el
	 *                                  formato correcto, o su dígito
	 *                                  verificador es incorrecto
	 */
	public static String validarRut(String rutIngresado) {

		if (rutIngresado == null || rutIngresado.isEmpty()) {
			throw new IllegalArgumentException(
					"⚠️ El RUT es obligatorio. Ingrese un RUT en formato 99.999.999-9.");
		}

		if (!rutIngresado.matches("\\d{1,2}\\.\\d{3}\\.\\d{3}-[0-9Kk]")) {
			throw new IllegalArgumentException(
					"⚠️ El formato de RUT debe ser 99.999.999-9.");
		}

		// Limpia los punto y pasa letra a mayúscula
		String rut = rutIngresado.replace(".", "").toUpperCase();

		// =====================================================
		// PASO 1: MULTIPLICACIÓN CON SECUENCIA 2,3,4,5,6,7
		// =====================================================

		int[] multiplicadores = { 2, 3, 4, 5, 6, 7 };
		int suma = 0;
		int j = 0;
		String digitoVerificadorUsuario = null;

		// Recorremos el RUT de derecha a izquierda
		for (int i = rut.length() - 1; i >= 0; i--) {

			if (i == rut.length() - 1) {
				digitoVerificadorUsuario = String.valueOf(rut.charAt(i));

				// Aca se salta el digito verificador y el guión
			} else if (i < rut.length() - 2) {

				int digito = Character.getNumericValue(rut.charAt(i));
				int producto = digito * multiplicadores[j];
				suma += producto;

				// Avanzar al siguiente multiplicador
				j++;
				if (j >= multiplicadores.length) {
					j = 0; // Reiniciar secuencia
				}
			}
		}

		// =====================================================
		// PASO 2: CÁLCULO DEL MÓDULO 11
		// =====================================================

		int resto = suma % 11;
		int resultado = 11 - resto;

		// =====================================================
		// PASO 3: APLICACIÓN DE REGLAS ESPECIALES
		// =====================================================

		String digitoVerificador;

		if (resultado == 11) {
			digitoVerificador = "0";
//				System.out.println("Caso especial: 11 → 0");
		} else if (resultado == 10) {
			digitoVerificador = "K";
//				System.out.println("Caso especial: 10 → K");
		} else {
			digitoVerificador = String.valueOf(resultado);
//				System.out.println("Caso normal: " + resultado);
		}

		if (!digitoVerificadorUsuario.equalsIgnoreCase(digitoVerificador)) {
			throw new IllegalArgumentException("⚠️ RUT ingresado es invalido.");
		} else {
			return rutIngresado;
		}
	}
}
