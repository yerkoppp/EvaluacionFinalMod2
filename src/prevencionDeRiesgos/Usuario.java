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
		super();
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.run = run;
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
		this.nombre = nombre;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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
		this.run = run;
	}

	//Otros metodos
	public String mostrarEdad() {
		//TODO
		return "Retorna un mensaje con la edad del usuario (ej. \"El usuario tiene X años\")"; 
	}
	@Override
	public String analizarUsuario() {
		return String.format("Nombre: %s\nRUT: %s", 
	        nombre, run);

	}
	
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", run=" + run + "]";
	}
	
	
}
