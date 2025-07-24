/**
 * @author Norma Armijo
 * @version 1.0
 */
package prevencionDeRiesgos;

/**
 * Clase que representa a un administrativo del sistema.
 * 
 * Extiende la clase Usuario añadiendo información específica 
 * de los administrativos como área y experiencia previa.
 * 
 * @see Usuario
 */
public class Administrativo extends Usuario{
	
	// ======================= ATRIBUTOS =======================
	
	/** Área del administrativo. Debe tener entre 5 y 20 caracteres */
	private String area; 
	
	/** Experiencia previa del administrativo. Máximo 100 caracteres */
	private String experienciaPrevia; 
	
    // ======================= CONSTRUCTORES =======================
    
    /**
     * Constructor por defecto.
     */
	public Administrativo() {
		
	}

    /**
     * Constructor con parámetros.
     * 
     * @param nombre Nombre del administrativo (10-50 caracteres)
     * @param fechaNacimiento Fecha de nacimiento (formato DD/MM/AAAA)
     * @param run RUN del administrativo (menor a 99.999.999)
     * @param area Área del administrativo (5-20 caracteres)
     * @param experienciaPrevia Experiencia previa (máximo 100 caracteres)
     */
	public Administrativo(String nombre, String fechaNacimiento, String run,
			String area, String experienciaPrevia) {
		super(nombre, fechaNacimiento, run);
		setArea(area);
		setExperienciaPrevia(experienciaPrevia);
	}

	
	// ======================= GETTERS =======================
	
    /**
     * Obtiene el área del administrativo.
     * 
     * @return el área del administrativo
     */
	public String getArea() {
		return area;
	}
	
    /**
     * Obtiene la experiencia previa del administrativo.
     * 
     * @return la experiencia previa del administrativo
     */
	public String getExperienciaPrevia() {
		return experienciaPrevia;
	}
	
	// ======================= SETTERS =======================
	
    /**
     * Establece el area del administrativo.
     * 
     * Valida que el area tenga entre 5 y 20 caracteres.
     * 
     * @param area el area a establecer (5-20 caracteres)
     * @throws IllegalArgumentException si el area es null, vacío o no cumple longitud
     */
    public void setArea(String area) {
		if (area == null || area.trim().isEmpty()) {
            throw new IllegalArgumentException("El area es obligatoria");
        }
        try {
            this.area = Validacion.validarLargoString(area, 5, 20);
        } catch (IllegalArgumentException e) {
        	System.out.println("Error en area: " + e.getMessage());
        	throw new IllegalArgumentException("Area ingresada no es inválido: " + e.getMessage());
        }
    }

    /**
     * Establece la experiencia previa del administrativo.
     * 
     * Valida que la experienciaPrevia tenga entre 0y 100 caracteres.
     * 
     * @param experienciaPrevia la experiencia previa a establecer (máximo 100 caracteres)
     */
	public void setExperienciaPrevia(String experienciaPrevia) {
        try {
            this.experienciaPrevia = Validacion.validarLargoString(experienciaPrevia, 0, 100);
        } catch (IllegalArgumentException e) {
            System.out.println("Error en ingreso experiencia previa: " + e.getMessage());
        }
    }

	// ======================= MÉTODOS ESPECIALES =======================
	
  /**
  * Muestra los datos del método del mismo nombre correspondiente a la clase
	* padre, junto con el área a la que pertenece el administrativo y su
	* experiencia previa.
	*/
	@Override
	public String analizarUsuario() {
		return "Tipo Usuario: Administrativo, "+super.analizarUsuario()+", "+toString();
	}

	@Override
	public String toString() {
		return "Área:" + area + ", Experiencia Previa: " + experienciaPrevia;
	}
	
}
