/**
 * @author Norma Armijo
 * @version 1.0
 */

package prevencionDeRiesgos;

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
     * Constructor con parámetros.
     * 
     * @param nombre Nombre del usuario (10-50 caracteres)
     * @param fechaNacimiento Fecha de nacimiento (formato DD/MM/AAAA)
     * @param run RUN del usuario (formato chileno válido y menor que 99.999.999)
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
     * @throws IllegalArgumentException si el nombre es null, vacío o no cumple longitud
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        try {
            this.nombre = Validacion.validarLargoString(nombre, 10, 50);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Nombre inválido: " + e.getMessage());
        }
    }

    /**
     * Establece la fecha de nacimiento del usuario.
     * 
     * Valida que el formato sea DD/MM/AAAA y que la fecha no sea futura.
     * 
     * @param fechaNacimiento la fecha de nacimiento a establecer
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        if (Validacion.validarFecha(fechaNacimiento) != null && 
            Validacion.calcularEdad(fechaNacimiento) >= 0) {
            this.fechaNacimiento = fechaNacimiento;         
        } else {           
            System.out.println("Fecha o formato incorrecto");
        }
    }

    /**
     * Establece el RUN del usuario.
     * 
     * Valida que el RUN cumpla con el formato chileno y tenga dígito verificador correcto.
     * 
     * @param run el RUN a establecer
     */
    public void setRun(String run) {
        try {
            String runValidado = Validacion.validarRut(run);
            if (runValidado != null && !runValidado.trim().isEmpty()) {
                this.run = run;
            } else {
                System.out.println("RUN ingresado no cumple con el formato.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("RUN inválido: " + e.getMessage());
        }
    }

	// ======================= MÉTODOS ESPECIALES =======================
	
    /**
     * Muestra la edad del usuario calculada a partir de su fecha de nacimiento.
     * 
     * @return mensaje con la edad del usuario en años
     */
    public String mostrarEdad() {
        return String.format("El usuario tiene %d años", Validacion.calcularEdad(fechaNacimiento)); 
    }
	
	@Override
	public String analizarUsuario() {
		return String.format("\nNombre: %s. Run %s\n", nombre, run);
	}
	
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", run=" + run + "]";
	}
	
}
