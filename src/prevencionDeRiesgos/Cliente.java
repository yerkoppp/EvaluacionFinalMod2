package prevencionDeRiesgos;

public class Cliente extends Usuario{

	// Atributos

	private String rut;
	private String nombres;
	private String apellidos;
	private String telefono;
	private String afp;
	private int sistemaSalud;
	private String direccion;
	private String comuna;
	private int edad;

	// Constructores

	public Cliente() {
	}

	/**
	 * @param rut
	 * @param nombres
	 * @param apellidos
	 * @param telefono
	 * @param afp
	 * @param sistemaSalud
	 * @param direccion
	 * @param comuna
	 * @param edad
	 */
	public Cliente(String rut, String nombres, String apellidos,
			String telefono, String afp, int sistemaSalud, String direccion,
			String comuna, int edad) {

		super(rut);
		setNombres(nombres);
		setApellidos(apellidos);
		setTelefono(telefono);
		setAfp(afp);
		setSistemaSalud(sistemaSalud);
		setDireccion(direccion);
		setComuna(comuna);
		setEdad(edad);
	}

	// Getter

	public String getRut() {
		return rut;
	}

	public String getNombres() {
		return nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getAfp() {
		return afp;
	}

	public int getSistemaSalud() {
		return sistemaSalud;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getComuna() {
		return comuna;
	}

	public int getEdad() {
		return edad;
	}

	// Setter
	
	// Setter con validación para nombre
	public void setNombres(String nombres) {
		if (nombres == null || nombres.trim().isEmpty()) {
			throw new IllegalArgumentException("⚠️ Los nombres son obligatorio.");
		}
		if (nombres.length() < 5 || nombres.length() > 30) {
			throw new IllegalArgumentException(
					"⚠️ Nombres deben tener minimo 5 y máximo 30 caracteres.");
		}
		this.nombres = nombres;
	}

	// Setter con validación para apellidos
	public void setApellidos(String apellidos) {
		if (apellidos == null || apellidos.trim().isEmpty()) {
			throw new IllegalArgumentException("⚠️ Los apellidos son obligatorio.");
		}
		if (apellidos.length() < 5 || apellidos.length() > 30) {
			throw new IllegalArgumentException(
					"⚠️ Apellidos deben tener minimo 5 y máximo 30 caracteres.");
		}
		this.apellidos = apellidos;
	}
	
	// Setter con validación para telefono
	public void setTelefono(String telefono) {
		if (telefono == null || telefono.trim().isEmpty()) {
			throw new IllegalArgumentException("⚠️ El teléfono es obligatorio.");
		}

		this.telefono = telefono;
	}

	// Setter con validación para AFP
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
	
	// Setter con validación para sistemaSalud
	public void setSistemaSalud(int sistemaSalud) {
		if (sistemaSalud <= 0 || sistemaSalud > 2) {
			throw new IllegalArgumentException(
					"⚠️ Elija un ópcion correcta.");
		}
		this.sistemaSalud = sistemaSalud;
	}

	// Setter con validación para direccion
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

	// Setter con validación para comuna
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

	// Setter con validación para edad
	public void setEdad(int edad) {
		if (edad < 0 || edad > 150) {
			throw new IllegalArgumentException(
					"⚠️ Edad debe ser entre 0 y 150.");
		}
		this.edad = edad;
	}

	
	//Métodos especiales
	
	public String obtenerNombre() {
		return String.format("%s %s",nombres,apellidos);
	}
	
	public String obtenerSistemaSalud() {
		if(sistemaSalud == 1) {
			return "Fonasa";
		} else if (sistemaSalud == 2) {
			return "Isapre";
		}
		return null;
	}
	
	@Override
	public String analizarUsuario() {
	    // Llama al método de la clase padre (Usuario)
 
	    return super.analizarUsuario() + "\n"
	    		+ String.format("Dirección: %s\nComuna: %s", 
	        direccion != null ? direccion : "No informada",
	        comuna != null ? comuna : "No informada");
	}
	
	@Override
	public String toString() {
		return String.format("RUT: %s\n"
				+ "Nombres: %s\n"
				+ "Apellidos: %s\n"
				+ "Telefono: %s\n"
				+ "AFP: %s\n"
				+ "Sistema de salud: %s\n"
				+ "Direccion: %s\n"
				+ "Comuna: %s\n"
				+ "Edad: %d",
				rut, nombres, apellidos, telefono, 
				afp != null ? afp : "No informado", 
				(sistemaSalud == 1) ? "Fonasa" : (sistemaSalud == 2) ?"Isapre": "No informado", 
				direccion != null ? direccion : "No informado", 
				comuna != null ? comuna : "No informado", edad);

	}

}
