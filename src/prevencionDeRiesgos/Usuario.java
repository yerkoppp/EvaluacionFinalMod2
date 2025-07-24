package prevencionDeRiesgos;

public class Usuario implements Asesoria {
	//Atributos
	private String nombre; // Obligatorio, 10-50 caracteres
	private String fechaNacimiento; //formato DD/MM/AAAA
	private String run; // Número menor a 99.999.999
	
	//Constructor
	public Usuario() {
		
	}
	
	/**
	 * @param nombre
	 * @param fechaNacimiento
	 * @param run
	 */
	public Usuario(String nombre, String fechaNacimiento, String run) {
		setNombre(nombre);
		setFechaNacimiento(fechaNacimiento);
		setRun(run);
	}
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		if (nombre == null || nombre.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"⚠️ Los nombres son obligatorio.");
		}
		this.nombre = Validacion.validarLargoString(nombre, 10, 50);
	}

	/**
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
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
		if(Validacion.validarFecha(fechaNacimiento) !=null) {
			this.fechaNacimiento = fechaNacimiento;			
		} else {
			throw new IllegalArgumentException(
					"⚠️ Fecha o formato incorrecto.");
		}
	}

	/**
	 * @return the run
	 */
	public String getRun() {
		return run;
	}

	/**
	 * @param run the run to set
	 */
	public void setRun(String run) {
		this.run = Validacion.validarRut(run);
	}

	//Otros metodos
	public String mostrarEdad() {
		//TODO
		//return "Retorna un mensaje con la edad del usuario (ej. \"El usuario tiene %d años\")"; 
		return String.format("El usuario tiene %d años", Validacion.calcularEdad(fechaNacimiento)); 
	}
	
	
	public String mostrarDatos() {
		return String.format("RUT: %s\n"
				+ "Nombre: %s\n"
				+ "Fecha de Nacimiento: %s", 
		        nombre, run, fechaNacimiento);
	}
	
	@Override

	public String analizarUsuario() {
		return String.format("Nombre: %s, RUT: %s", 
	        nombre, run);

	}
	
	@Override
	public String toString() {
		return "Usuario: " + nombre + ", Fecha Nacimiento: " + fechaNacimiento + ", RUN:" + run;
	}
	
	
}
