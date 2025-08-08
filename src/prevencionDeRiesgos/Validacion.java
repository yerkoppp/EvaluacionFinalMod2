/**
 * @author Yerko Osorio
 * @author Luis Guevara
 * @author Jhoseph Quiroga
 * @author Norma Armijo
 * @version 1.0
 */
package prevencionDeRiesgos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Clase utilitaria para validaciones
 */
public final class Validacion {
	/**
	 * Definimos el formateador para el formato "DD/MM/AAAA"
	 */
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter
			.ofPattern("dd/MM/yyyy");
	/**
	 * Definimos el formateador para el patrón "HH:MM"
	 */
	private static final DateTimeFormatter FORMATTER_HHMM = DateTimeFormatter
			.ofPattern("HH:mm");

	/**
	 * Definimos un array con los días de la semana.
	 */
	private static ArrayList<String> dias = new ArrayList<String>();

	/**
	 * Inicialización del ArrayList dias.
	 */
	static {
		dias.add("LUNES");
		dias.add("MARTES");
		dias.add("MIÉRCOLES");
		dias.add("JUEVES");
		dias.add("VIERNES");
		dias.add("SÁBADO");
		dias.add("DOMINGO");
	}

	/**
	 * Constructor privado para evitar la instanciación de la clase utilitaria.
	 */
	private Validacion() {
		// Constructor vacío para prevenir instanciación

	}

	/**
	 * Valida el formato de la fecha ingresada (DD/MM/AAAA) y la transforma en
	 * LocalDate.
	 *
	 * @param fecha La fecha en formato String "DD/MM/AAAA".
	 * @return La fecha en tipo LocalDate.
	 * @throws IllegalArgumentException si la fecha es nula o si el formato de
	 *                                  la fecha no es válido.
	 */
	public static LocalDate validarFecha(String fecha) {
		// Primero, valida si la fecha es nula o vacía
		if (fecha == null || fecha.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"⚠️ La fecha no puede ser nula o vacía. Utilice DD/MM/AAAA.");
		}

		// Luego, intenta parsear la fecha
		try {
			return LocalDate.parse(fecha, FORMATTER);
		} catch (DateTimeParseException e) {
			// Si el formato no es correcto, lanza una excepción
			throw new IllegalArgumentException(
					"⚠️ El formato de la fecha no es válido. Utilice DD/MM/AAAA.",
					e);
		}
	}

	/**
	 * Transforma la fecha LocalDate a String con formato DD/MM/AAAA.
	 * 
	 * @param fecha La fecha en formato LocalDate.
	 * @return la fecha en tipo String. Retorna null si el formato no es válido.
	 */
	public static String transformarFechaAstring(LocalDate fecha) {
		if (fecha == null) {
			return null;
		}
		return fecha.format(FORMATTER);
	}
	
	/**
     * Valida que la fecha de un evento no sea anterior a la fecha actual.
     * @param fecha La fecha a validar.
     * @return La misma fecha si la validación es exitosa.
     * @throws IllegalArgumentException si la fecha es anterior a la fecha actual.
     */
    public static LocalDate validarFechaPasada(LocalDate fecha) {
        if (fecha.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser en el futuro.");
        }
        return fecha;
    }


	/**
     * Valida el formato de la hora ingresada (HH:MM) y la transforma en LocalTime.
     *
     * @param hora La hora en formato String "HH:MM".
     * @return La hora en tipo LocalTime.
     * @throws IllegalArgumentException si el formato de la hora no es válido.
     */
	public static LocalTime validarHora(String hora) {
		// Primero, valida si la fecha es nula o vacía
		if (hora == null || hora.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"⚠️ La hora no puede ser nula o vacía. Utilice HH:MM.");
		}
		// Luego, intenta parsear la fecha
		try {
			return LocalTime.parse(hora, FORMATTER_HHMM);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException(
					"⚠️ El formato de la hora no es válido. Utilice HH:MM.", e);
		}
	}

	/**
	 * Transforma la hora LocalTime a String con formato HH:MM.
	 * 
	 * @param hora La hora en formato LocalTime.
	 * @return la hora en tipo String. Retorna null si el formato no es válido.
	 */
	public static String transformarHoraAstring(LocalTime hora) {

		if (hora == null) {
			return null; // O una cadena vacía, dependiendo de la lógica
		}
		return hora.format(FORMATTER_HHMM);
	}

	/**
	 * Valida que la cadena de texto de un día de la semana sea válida
	 * (es decir, que sea uno de los siete días de la semana en español,
	 * insensible a mayúsculas/minúsculas) y la retorna en mayúsculas.
	 *
	 * Este método es insensible a mayúsculas y minúsculas para la entrada,
	 * pero siempre retornará el día en mayúsculas (ej: "LUNES").
	 *
	 * @param diaEvaluado La cadena de texto que representa el día a validar (ej: "Lunes", "MARTES").
	 * @return La cadena del día validado y convertida a mayúsculas.
	 * @throws IllegalArgumentException si {@code diaEvaluado} es {@code null}, está vacío
	 * (incluyendo solo espacios en blanco), o no corresponde
	 * a un día de la semana válido en español.
	 */
	public static String validarDia(String diaEvaluado) {
	     if (diaEvaluado == null || diaEvaluado.trim().isEmpty()) {
	        throw new IllegalArgumentException(
	                "⚠️ El día no es válido. Inténtelo de nuevo");
	    }

	     if (!dias.contains(diaEvaluado.toUpperCase())) {
	        throw new IllegalArgumentException(
	                "⚠️ El día no es válido. Inténtelo de nuevo");
	    }

	    // Si todas las validaciones pasan, retorna el día en mayúsculas.
	    return diaEvaluado.toUpperCase();
	}

	/**
	 * Valida que la longitud de un texto esté dentro del rango especificado.
	 * 
	 * @param stringEvaluado Texto a validar (no puede ser null)
	 * @param min            Longitud mínima permitida (inclusive)
	 * @param max            Longitud máxima permitida (inclusive)
	 * @return stringEvaluado, corresponde a el texto validado
	 * @throws IllegalArgumentException si el texto es null o su longitud no
	 *                                  está en el rango.
	 */
	public static String validarLargoString(String stringEvaluado, int minimo,
			int maximo) {

		if(stringEvaluado == null || stringEvaluado.isEmpty()) {
			throw new IllegalArgumentException(
					"⚠️ El texto no puede ser nulo o vacío.");
		}
		
		if (stringEvaluado.length() < minimo
				|| stringEvaluado.length() > maximo) {
			throw new IllegalArgumentException(
					String.format("⚠️ El largo del texto no es permitido. Debe estar entre %d y %d carácteres", minimo, maximo));
		}

		return stringEvaluado;
	}

	/**
	 * Valida que la longitud de un texto no exceda un máximo especificado.
	 *
	 * @param stringEvaluado Texto a validar.
	 * @param maximo         Longitud máxima permitida (inclusive).
	 * @return El texto validado.
	 * @throws IllegalArgumentException si el texto es null o su longitud supera el máximo.
	 */
	public static String validarLargoString(String stringEvaluado, int maximo) {

		if(stringEvaluado == null || stringEvaluado.isEmpty()) {
			throw new IllegalArgumentException(
					"⚠️ El texto no puede ser nulo o vacío.");
		}
		
		if (stringEvaluado.length() > maximo) {
			throw new IllegalArgumentException(
					String.format("⚠️ El largo del texto no es permitido. Debe ser menor o igual a %d carácteres", maximo));
		}

		return stringEvaluado;
	}

	/**
	 * Valida que un número entero no exceda un máximo especificado (no inclusivo).
	 *
	 * @param numeroEvaluado Número a validar.
	 * @param maximo         Valor máximo permitido (no inclusivo).
	 * @return El número validado.
	 * @throws IllegalArgumentException si el número es mayor o igual al máximo.
	 */
	public static int validarNumeroMaximo(int numeroEvaluado, int maximo) {

		if (numeroEvaluado >= maximo) {
			throw new IllegalArgumentException(
					"⚠️ El número ingresado supera el máximo permitido. "
					+ "Debe ser menor a" + maximo + " carácteres");
		}

		return numeroEvaluado;
	}

	/**
	 * Calcula la edad en años a partir de una fecha de nacimiento en formato
	 * LocalDate (dd/MM/yyyy).
	 *
	 * @param fechaNacimiento La fecha de nacimiento en formato LocalDate (ej.
	 *                           "25/12/1990").
	 * @return La edad en años.
	 * @throws IllegalArgumentException si el formato de fecha es inválido,
	 * la fecha es nula, o si la fecha de nacimiento es en el futuro.
	 */
	public static int calcularEdad(LocalDate fechaNacimiento) {
		if (fechaNacimiento == null) {
			throw new IllegalArgumentException(
					"Error: Formato de fecha de nacimiento inválido o fecha no válida.");
		}

		LocalDate fechaActual = LocalDate.now();

		if (fechaNacimiento.isAfter(fechaActual)) {
			throw new IllegalArgumentException(
					"Error: La fecha de nacimiento no puede ser en el futuro.");
		}

		// Calcular la diferencia entre las dos fechas
		Period periodo = Period.between(fechaNacimiento, fechaActual);

		// Obtener los años de la diferencia
		return periodo.getYears();
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

		if (rutIngresado == null || rutIngresado.trim().isEmpty()) {
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

	/**
	 * Valida que el estado de la revisión sea un valor permitido.
	 *
	 * @param estado El número entero que representa el estado de la revisión.
	 * @return true si el estado es 1, 2 o 3; de lo contrario, false.
	 */
	public static boolean validarEstadoRevision(int estado) {
	    return estado == 1 || estado == 2 || estado == 3;
	}
}
