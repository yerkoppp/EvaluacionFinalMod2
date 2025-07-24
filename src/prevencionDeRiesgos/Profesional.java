/**
 * 
 */
package prevencionDeRiesgos;

import java.time.LocalDate;

/**
 * 
 */
public class Profesional extends Usuario{
	//Atributos
	private String titulo; //10-50 caracteres
	private LocalDate fechaIngreso; //o String -> Formato DD/MM/AAAA
	
	//Constructores
	public Profesional() {
		
	}

	/**
	 * @param nombre
	 * @param fechaNacimiento
	 * @param run
	 */
	public Profesional(String nombre, String fechaNacimiento, String run, String titulo, LocalDate fechaIngreso) {
		super(nombre, fechaNacimiento, run);
		this.titulo = titulo;
		this.fechaIngreso = fechaIngreso;
	}

	//Getters y Setters
	
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the fechaIngreso
	 */
	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @param fechaIngreso the fechaIngreso to set
	 */
	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	//Otros metodos
	@Override
	public String mostrarDatos() {
		return String.format("RUT: %s\n"
				+ "Nombre: %s\n"
				+ "Fecha de Nacimiento: %s\n"
				+ "Titulo: %s\n"
				+ "Fecha de ingreso: %s", 
		        super.getNombre(), super.getRun(), super.getFechaNacimiento()
		        , titulo, Validacion.transformarFechaAstring(fechaIngreso));
	}
	
	@Override
	public String analizarUsuario() {
		//  Sobrescribe el método de la clase padre para desplegar 
		//el nombre y RUN del usuario, junto con el título y la fecha
		//de ingreso del profesional
		
		return "Tipo Usuario: Profesional, "+super.analizarUsuario()+", "+toString();
	}

	@Override
	public String toString() {
		return "Título: " + titulo + ", Fecha Ingreso: " + fechaIngreso;
	}
	
	
}
