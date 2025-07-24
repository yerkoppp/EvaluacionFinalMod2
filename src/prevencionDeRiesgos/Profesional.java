/**
 * @author Norma Armijo
 * @version 1.0
 * 
 */
package prevencionDeRiesgos;



/**
 * Clase que representa a un profesional del sistema.
 * 
 * Extiende la clase Usuario añadiendo información específica 
 * de los profesionales como título y fecha de ingreso.
 * 
 * @see Usuario
 */
public class Profesional extends Usuario{


	
	// ======================= ATRIBUTOS =======================
	
	/** Título profesional. Debe tener entre 10 y 50 caracteres */
	private String titulo; 
	
	/** Fecha de ingreso del profesional. Formato DD/MM/AAAA */
	private String fechaIngreso;
	
    // ======================= CONSTRUCTORES =======================
    
    /**
     * Constructor por defecto.
     */
	
	public Profesional() {
		
	}


    /**
     * Constructor con parámetros.
     * 
     * @param nombre Nombre del profesional (10-50 caracteres)
     * @param fechaNacimiento Fecha de nacimiento (formato DD/MM/AAAA)
     * @param run RUN del profesional (menor a 99.999.999)
     * @param titulo Título profesional (10-50 caracteres)
     * @param fechaIngreso Fecha de ingreso (formato DD/MM/AAAA)
     */
	public Profesional(String nombre, String fechaNacimiento, String run, String titulo, String fechaIngreso) {
		super(nombre, fechaNacimiento, run);
		setTitulo(titulo);
		setFechaIngreso(fechaIngreso); 
	}

    // ======================= GETTERS =======================
	
    /**
     * Obtiene el título profesional.
     * 
     * @return el título profesional
     */
	public String getTitulo() {
		return titulo;
	}
	
    /**
     * Obtiene la fecha de ingreso del profesional.
     * 
     * @return la fecha de ingreso en formato DD/MM/AAAA
     */
	public String getFechaIngreso() {
		return fechaIngreso;
	}



    // ======================= SETTERS =======================

    /**
     * Establece el título profesional.
     * 
     * Valida que el título tenga entre 10 y 50 caracteres.
     * 
     * @param titulo el título a establecer (10-50 caracteres)
     * @throws IllegalArgumentException si el titulo es null, vacío o no cumple longitud
     */
	public void setTitulo(String titulo) {
		if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El titulo es obligatorio");
        }
        try {
            this.titulo = Validacion.validarLargoString(titulo, 10, 50);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Titulo inválido: " + e.getMessage());
        }
	}

    /**
     * Establece la fecha de ingreso del profesional.
     * 
     * Valida que la fecha tenga el formato DD/MM/AAAA.
     * 
     * @param fechaIngreso la fecha de ingreso a establecer (formato DD/MM/AAAA)
     */
	public void setFechaIngreso(String fechaIngreso) {
		if (fechaIngreso == null || fechaIngreso.trim().isEmpty()) {
			this.fechaIngreso = null;
			return;
		}
        if (Validacion.validarFecha(fechaIngreso) != null) {
        	this.fechaIngreso = fechaIngreso;         
            } else {           
            	throw new IllegalArgumentException(
    					"⚠️ Fecha o formato incorrecto.");
            }	
	}
	
  // ======================= MÉTODOS ESPECIALES =======================
  
	/**
  * Sobrescribe el método de la clase padre para desplegar 
	* el nombre y RUN del usuario, junto con el título y la fecha
	* de ingreso del profesional.
  */
	@Override
	public String mostrarDatos() {
		return String.format("RUT: %s\n"
				+ "Nombre: %s\n"
				+ "Fecha de Nacimiento: %s\n"
				+ "Titulo: %s\n"
				+ "Fecha de ingreso: %s", 
		        super.getNombre(), super.getRun(), super.getFechaNacimiento()
		        , titulo,
		        fechaIngreso != null ? fechaIngreso : "No informado");
	}
	
	@Override
	public String analizarUsuario() {
		return "Tipo Usuario: Profesional, "+super.analizarUsuario()+", "+toString();
	}

	@Override
	public String toString() {
		return "Título: " + titulo + ", Fecha Ingreso: " + fechaIngreso;
	}
	
	
}
