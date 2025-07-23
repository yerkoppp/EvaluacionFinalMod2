/**
 * 
 */
package prevencionDeRiesgos;

import java.util.Date;

/**
 * 
 */
public class Profesional extends Usuario{
	//Atributos
	private String titulo; //10-50 caracteres
	private Date fechaIngreso; //o String -> Formato DD/MM/AAAA
	
	//Constructores
	public Profesional() {
		
	}

	/**
	 * @param nombre
	 * @param fechaNacimiento
	 * @param run
	 */
	public Profesional(String nombre, String fechaNacimiento, String run, String titulo, Date fechaIngreso) {
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
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @param fechaIngreso the fechaIngreso to set
	 */
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	//Otros metodos
	@Override
	public String analizarUsuario() {
		//  Sobrescribe el método de la clase padre para desplegar 
		//el nombre y RUN del usuario, junto con el título y la fecha
		//de ingreso del profesional
		
		return "";
	}

	@Override
	public String toString() {
		return "Profesional [titulo=" + titulo + ", fechaIngreso=" + fechaIngreso + "]";
	}
	
	
}
