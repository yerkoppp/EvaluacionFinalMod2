/**
 * 
 * @author Yerko Osorio
 * @author Luis Guevara
 * @author Jhoseph Quiroga
 * @author Norma Armijo
 * @version 1.0
 */
package prevencionDeRiesgos;

/**
 * Clase que representa un Cliente dentro del sistema de prevención de riesgos.
 * Hereda de la clase Usuario e implementa atributos adicionales propios de un
 * cliente, como apellidos, teléfono, AFP, sistema de salud, dirección, comuna y
 * edad. Incluye validaciones en todos sus setters.
 */
public class Cliente extends Usuario {

	// ======================= ATRIBUTOS =======================

	/** Apellidos del cliente */
	private String apellidos;
	/** Teléfono de contacto del cliente */
	private String telefono;
	/** AFP del cliente */
	private String afp;
	/** Sistema de salud (1 para Fonasa, 2 para Isapre) */
	private int sistemaSalud;
	/** Dirección del cliente */
	private String direccion;
	/** Comuna del cliente */
	private String comuna;
	/** Edad del cliente */
	private int edad;

	// ======================= CONSTRUCTORES =======================

	/**
	 * Constructor vacío de Cliente
	 */
	public Cliente() {
	}

	/**
	 * Constructor con todos los campos necesarios para crear un cliente.
	 *
	 * @param run             RUT del cliente (validado)
	 * @param nombres         Nombres del cliente
	 * @param fechaNacimiento Fecha de nacimiento en formato ISO (aaaa-mm-dd)
	 * @param apellidos       Apellidos del cliente
	 * @param telefono        Teléfono de contacto
	 * @param afp             AFP del cliente
	 * @param sistemaSalud    1 para Fonasa, 2 para Isapre
	 * @param direccion       Dirección del cliente
	 * @param comuna          Comuna del cliente
	 * @param edad            Edad del cliente
	 * @throws IllegalArgumentException si alguna validación falla
	 */
	public Cliente(String run, String nombres, String fechaNacimiento,
			String apellidos, String telefono, String afp, int sistemaSalud,
			String direccion, String comuna, int edad) {

		super(nombres, fechaNacimiento, Validacion.validarRut(run));
		setNombres(nombres);
		setApellidos(apellidos);
		setTelefono(telefono);
		setAfp(afp);
		setSistemaSalud(sistemaSalud);
		setDireccion(direccion);
		setComuna(comuna);
		setEdad(edad);
	}

	// ======================= GETTER =======================

	/**
	 * @return Apellidos del cliente
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @return Teléfono del cliente
	 */
	public String getTelefono() {
		return telefono;
	}

	/** 
     * @return AFP del cliente 
     */
	public String getAfp() {
		return afp;
	}

	/** 
     * @return Sistema de salud (1 o 2) 
     */
	public int getSistemaSalud() {
		return sistemaSalud;
	}

	 /** 
     * @return Dirección del cliente 
     */
	public String getDireccion() {
		return direccion;
	}

	/** 
     * @return Comuna del cliente 
     */
	public String getComuna() {
		return comuna;
	}

	/** 
     * @return Edad del cliente 
     */
	public int getEdad() {
		return edad;
	}

	// ======================= SETTER =======================

	/**
	 * Establece y valida el RUT del cliente.
	 * @param run RUT a validar y asignar
	 */
	public void setRun(String run) {
		// this.rut = Validacion.validarRut(rut);
		super.setRun(Validacion.validarRut(run));
	}

	/**
	 * Establece y valida los nombres del cliente.
	 * @param nombres Nombres a asignar
	 */
	public void setNombres(String nombres) {
		if (nombres == null || nombres.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"⚠️ Los nombres son obligatorio.");
		}
		if (nombres.length() < 5 || nombres.length() > 30) {
			throw new IllegalArgumentException(
					"⚠️ Nombres deben tener minimo 5 y máximo 30 caracteres.");
		}
		// this.nombres = nombres;
		super.setNombre(nombres);
	}

	/**
	 * Establece la fecha de nacimiento en formato DD/MM/AAAA.
	 * @param fechaNacimiento Fecha a asignar
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		if (fechaNacimiento == null || fechaNacimiento.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"⚠️ La fecha de nacimiento es obligatoria.");
		}
		if (!fechaNacimiento.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
			throw new IllegalArgumentException(
					"⚠️ Fecha de nacimiento debe ser en formato DD/MM/AAAA.");
		}
		super.setFechaNacimiento(fechaNacimiento);
	}

	/**
	 * Establece los apellidos del cliente con validación.
	 * @param apellidos Apellidos a asignar
	 */
	public void setApellidos(String apellidos) {
		if (apellidos == null || apellidos.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"⚠️ Los apellidos son obligatorio.");
		}
		if (apellidos.length() < 5 || apellidos.length() > 30) {
			throw new IllegalArgumentException(
					"⚠️ Apellidos deben tener minimo 5 y máximo 30 caracteres.");
		}
		this.apellidos = apellidos;
	}

	/**
	 * Establece el teléfono del cliente.
	 * @param telefono Número telefónico
	 */
	public void setTelefono(String telefono) {
		if (telefono == null || telefono.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"⚠️ El teléfono es obligatorio.");
		}

		this.telefono = telefono;
	}

	/**
	 * Establece la AFP del cliente (opcional).
	 * @param afp Nombre de la AFP
	 */
	public void setAfp(String afp) {
		if (afp == null || afp.trim().isEmpty()) {
			this.afp = null;
			return;
		}
		if (afp.length() < 4 || afp.length() > 30) {
			throw new IllegalArgumentException(
					"⚠️ AFP debe tener minimo 4 y máximo 30 caracteres.");
		}
		this.afp = afp;
	}

	/**
	 * Establece el sistema de salud.
	 * @param sistemaSalud 1 para Fonasa, 2 para Isapre
	 */
	public void setSistemaSalud(int sistemaSalud) {
		if (sistemaSalud <= 0 || sistemaSalud > 2) {
			throw new IllegalArgumentException("⚠️ Elija un ópcion correcta.");
		}
		this.sistemaSalud = sistemaSalud;
	}

	/**
	 * Establece la dirección (opcional).
	 * @param direccion Dirección del cliente
	 */
	public void setDireccion(String direccion) {
		if (direccion == null || direccion.trim().isEmpty()) {
			this.direccion = null;
			return;
		}
		if (direccion.length() > 70) {
			throw new IllegalArgumentException(
					"⚠️ Direccion debe tener máximo 70 caracteres.");
		}
		this.direccion = direccion;
	}

	/**
	 * Establece la comuna (opcional).
	 * @param comuna Comuna del cliente
	 */
	public void setComuna(String comuna) {
		if (comuna == null || comuna.trim().isEmpty()) {
			this.comuna = null;
			return;
		}
		if (comuna.length() > 50) {
			throw new IllegalArgumentException(
					"⚠️ Comuna debe tener máximo 50 caracteres.");
		}
		this.comuna = comuna;
	}

	/**
	 * Establece la edad del cliente.
	 * @param edad Edad entre 0 y 150
	 */
	public void setEdad(int edad) {
		if (edad < 0 || edad > 150) {
			throw new IllegalArgumentException(
					"⚠️ Edad debe ser entre 0 y 150.");
		}
		this.edad = edad;
	}

	// ======================= MÉTODOS ESPECIALES =======================

	/**
	 * Obtiene el nombre completo del cliente (nombre + apellidos).
	 * @return Nombre completo
	 */
	public String obtenerNombre() {
		String nombreCompleto = String.format("%s %s", super.getNombre(), apellidos);
		super.setNombre(nombreCompleto);
		return nombreCompleto;
	}

	/**
	 * Devuelve el sistema de salud como texto legible.
	 * @return "Fonasa", "Isapre" o null si no está definido
	 */
	public String obtenerSistemaSalud() {
		if (sistemaSalud == 1) {
			return "Fonasa";
		} else if (sistemaSalud == 2) {
			return "Isapre";
		}
		return null;
	}

	/**
	 * Devuelve un análisis básico del cliente (heredado y extendido).
	 * @return Análisis de datos del usuario
	 */
	@Override
	public String analizarUsuario() {
		// Llama al método de la clase padre (Usuario)
		return "Tipo Usuario: Cliente, "+super.analizarUsuario()+", "+toString();
	}

	/**
	 * Retorna los datos del cliente en un formato legible.
	 * @return Representación textual del cliente
	 */
	@Override
	public String toString() {
		return String.format(
				"%s, Fecha de nacimiento: %s, " + "Telefono: %s, "
						+ "AFP: %s, " + "Sistema de salud: %s, "
						+ "Direccion: %s, " + "Comuna: %s, " + "Edad: %d",
				obtenerNombre(),
				getFechaNacimiento(), telefono,
				afp != null ? afp : "No informado",
				(sistemaSalud == 1) ? "Fonasa"
						: (sistemaSalud == 2) ? "Isapre" : "No informado",
				direccion != null ? direccion : "No informado",
				comuna != null ? comuna : "No informado", edad);

	}
	

}
