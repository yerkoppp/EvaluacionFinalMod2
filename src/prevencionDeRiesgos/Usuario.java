/**
 * @author Norma Armijo
 * @version 1.0
 */

package prevencionDeRiesgos;

import java.time.LocalDate;

/**
 * Clase que representa a un usuario del sistema.
 * 
 * Esta clase almacena la información básica de un usuario y realiza
 * validaciones para garantizar la integridad de los datos.
 * 
 */
public class Usuario implements Asesoria {
	// ======================= ATRIBUTOS =======================

	/** Nombre del usuario. Obligatorio, 10-50 caracteres */
	private String nombre;

	/** Fecha de nacimiento del usuario. Formato DD/MM/AAAA */
	private String fechaNacimiento;

	/** RUN del usuario. Debe ser menor a 99.999.999 */
	private String run;

// ======================= CONSTRUCTORES =======================

	/**
	 * Constructor por defecto.
	 */
	public Usuario() {

	}

	/**
	 * 
	 * 
	 * Constructor con parámetros.
	 * 
	 * @param nombre          Nombre del usuario (10-50 caracteres)
	 * @param fechaNacimiento Fecha de nacimiento (formato DD/MM/AAAA)
	 * @param run             RUN del usuario (formato chileno válido y menor
	 *                        que 99.999.999)
	 */
	public Usuario(String nombre, String fechaNacimiento, String run) {
		setNombre(nombre);
		setFechaNacimiento(fechaNacimiento);
		setRun(run);
	}

	// ======================= GETTER =======================

	/**
	 * Obtiene el nombre del usuario.
	 * 
	 * @return el nombre del usuario
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Obtiene la fecha de nacimiento del usuario.
	 * 
	 * @return la fecha de nacimiento en formato DD/MM/AAAA
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Obtiene el RUN del usuario.
	 * 
	 * @return el RUN del usuario
	 */
	public String getRun() {
		return run;
	}

	// ======================= SETTER =======================

	/**
	 * Establece el nombre del usuario.
	 * 
	 * Valida que el nombre tenga entre 10 y 50 caracteres.
	 * 
	 * @param nombre el nombre a establecer
	 * @throws IllegalArgumentException si el nombre es null, vacío o no cumple
	 *                                  longitud
	 */
	public void setNombre(String nombre) {
		if (nombre == null || nombre.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"⚠️ Los nombres son obligatorio.");
		}
		try {
			this.nombre = Validacion.validarLargoString(nombre, 10, 50);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(
					"Nombre inválido: " + e.getMessage());
		}
	}

	/**
	 * Establece la fecha de nacimiento en formato DD/MM/AAAA.
	 * 
	 * @param fechaNacimiento Fecha a asignar
	 */
	/**
	 * Establece la fecha de nacimiento en formato DD/MM/AAAA.
	 *
	 * @param fechaNacimiento Fecha a asignar
	 * @throws IllegalArgumentException si la fecha es null, vacía, tiene un formato incorrecto o es en el futuro.
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
	    // 1. Validar que la fecha no sea nula o vacía directamente en Usuario.
	    // Esto asegura que el mensaje de "obligatoria" provenga de Usuario.
	    if (fechaNacimiento == null || fechaNacimiento.trim().isEmpty()) {
	        throw new IllegalArgumentException(
	                "⚠️ La fecha de nacimiento es obligatoria.");
	    }
	    // 2. Delegar la validación completa (formato y no futuro) a Validacion.calcularEdad.
	    // Validacion.calcularEdad ya llama a Validacion.validarFecha internamente.
	    // Si la fecha tiene formato inválido o es futura, Validacion.calcularEdad lanzará una excepción.
	    Validacion.calcularEdad(fechaNacimiento);

	    this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Establece el RUN del usuario.
	 * 
	 * Valida que el RUN cumpla con el formato chileno y tenga dígito
	 * verificador correcto.
	 * 
	 * @param run el RUN a establecer
	 */
	public void setRun(String run) {
		this.run = Validacion.validarRut(run);
	}

	// ======================= MÉTODOS ESPECIALES =======================

	/**
	 * Retorna una representación formateada con los datos del usuario con sus
	 * datos principales: RUT, nombre completo y fecha de nacimiento. Este
	 * método puede ser sobrescrito por las clases hijas para agregar
	 * información adicional específica.
	 *
	 * @return Cadena de texto con el RUT, nombre y fecha de nacimiento del
	 *         usuario.
	 */
	public String mostrarDatos() {
		return String.format(
				"RUT: %s\n" + "Nombre: %s\n" + "Fecha de Nacimiento: %s",
				nombre, run, fechaNacimiento);
	}

	/**
	 * Muestra la edad del usuario calculada a partir de su fecha de nacimiento.
	 * 
	 * @return mensaje con la edad del usuario en años
	 */
	public String mostrarEdad() {
		return String.format("El usuario tiene %d años",
				Validacion.calcularEdad(fechaNacimiento));
	}

	
	@Override
	public String analizarUsuario() {
		return String.format("Nombre: %s. RUT: %s", nombre, run);
	}

	@Override
	public String toString() {
		return "Usuario: " + nombre + ", Fecha Nacimiento: " + fechaNacimiento
				+ ", RUN:" + run;
	}

}
