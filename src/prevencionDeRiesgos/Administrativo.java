package prevencionDeRiesgos;

public class Administrativo extends Usuario{
	
	//Atributos
	private String area; //5-20 caracteres
	private String experienciaPrevia; //Máximo 100 caracteres
	
	//Constructor 
	public Administrativo() {
		
	}

	/**
	 * @param nombre
	 * @param fechaNacimiento
	 * @param run
	 */
	public Administrativo(String nombre, String fechaNacimiento, String run,
			String area, String experienciaPrevia) {
		super(nombre, fechaNacimiento, run);
		this.area = area;
		this.experienciaPrevia = experienciaPrevia;
	}

	//Getters y Setters
	
	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the experienciaPrevia
	 */
	public String getExperienciaPrevia() {
		return experienciaPrevia;
	}

	/**
	 * @param experienciaPrevia the experienciaPrevia to set
	 */
	public void setExperienciaPrevia(String experienciaPrevia) {
		this.experienciaPrevia = experienciaPrevia;
	}

	//Otros metodos
	@Override
	public void analizarUsuario() {
		/*el cual debe mostrar
		los datos del método del mismo nombre correspondiente a la clase
		padre, junto con el área a la que pertenece el administrativo y su
		experiencia previa
		 * */
	}

	@Override
	public String toString() {
		return "Administrativo [area=" + area + ", experienciaPrevia=" + experienciaPrevia + "]";
	}
	
	
}
