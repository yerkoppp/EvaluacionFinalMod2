/**
 * @author Yerko Osorio
 */
package prevencionDeRiesgos;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Representa un accidente registrado.
 */
public class Accidente {
	
	private int identificadorAccidente;
	private String rutCliente;
	private String dia; //para manejar el formato DD/MM/AAAA.
	private LocalTime hora;
	private String lugar;
	private String origen;
	private String consecuencias;

	/**
	 * Constructor vacío.
	 */
	public Accidente() {
		// TODO Auto-generated constructor stub
		LocalDate fechaHoraActual = LocalDate.now();
		String momentoActual = fechaHoraActual.toString();
		this.identificadorAccidente = momentoActual.hashCode();
	}
	
	/**
	 * Constructor con todos los atributos.
	 * @param identificadorAccidente
	 * @param rutCliente
	 * @param dia
	 * @param hora
	 * @param lugar
	 * @param origen
	 * @param consecuencias
	 */
	public Accidente(String rutCliente, String dia,
			String hora, String lugar, String origen,
			String consecuencias) {
		super();
		LocalDate fechaHoraActual = LocalDate.now();
		String momentoActual = fechaHoraActual.toString();
		this.identificadorAccidente = momentoActual.hashCode();
		this.rutCliente = Validacion.validarRut(rutCliente);
		this.dia = Validacion.validarDia(dia);
		this.hora = Validacion.validarHora(hora);
		this.lugar = Validacion.validarLargoString(lugar, 10, 50);
		this.origen = Validacion.validarLargoString(origen, 100);
		this.consecuencias = Validacion.validarLargoString(consecuencias, 1000);
	}
	
	
	//Métodos:
	/**
	 * @return Retorna el identificador del accidente.
	 */
	public int getIdentificadorAccidente() {
		return identificadorAccidente;
	}
	
	/**
	 * Asigna el identificador. Privado. No puede modificarse el identificador una vez creado el objeto. 
	 * @param identificador
	 */
	private void setIdentificadorAccidente(int identificador) {
		this.identificadorAccidente = identificador;
	}
	
	

	/**
	 * @return Retorna el RUT del cliente accidentado.
	 */
	public String getRutCliente() {
		return rutCliente;
	}
	
	/**
	 * Asigna el RUT del cliente asociado.
	 * Validación: Obligatorio.
	 * @param rutCliente
	 */
	public void setRutCliente(String rutCliente) {
		this.rutCliente = Validacion.validarRut(rutCliente);
	}


	/**
	 * @return Retorna el día del accidente.
	 */
	public String getDia() {
		return dia;
	}
	
	/**
	 * Asigna la fecha del accidente.
	 * Validación: Se debe asegurar que al ser desplegado esté en formato DD/MM/AAAA.
	 * @param dia
	 */
	public void setDia(String dia) {
		this.dia = Validacion.validarDia(dia);
	}



	/**
	 * @return Returna la hora del accidente.
	 */
	public String getHora() {
		return Validacion.transformarHoraAstring(hora);
	}
	
	/**
	 * Asigna la hora del accidente.
	 * Validación: Debe ser una hora válida del día, en formato HH:MM (hora desde 0 a 23, minutos entre 0 y 59).
	 * @param hora
	 */
	public void setHora(String hora) {
		
		this.hora = Validacion.validarHora(hora);
	} 


	/**
	 * @return Retorna el lugar del accidente.
	 */
	public String getLugar() {
		return lugar;
	}
	
	/**
	 * Asigna el lugar del accidente. 
	 * Validación: Obligatorio, mínimo 10 caracteres, máximo 50.
	 * @param lugar
	 */
	public void setLugar(String lugar) {
		this.lugar = Validacion.validarLargoString(lugar, 10, 50);
	}


	/**
	 * @return Retorna el origen del accidente.
	 */
	public String getOrigen() {
		return origen;
	}

	/**
	 * Asigna el origen. 
	 * Validación: Máximo 100 caracteres.
	 * @param origen
	 */
	public void setOrigen(String origen){
		this.origen = Validacion.validarLargoString(origen, 100);
	}
	
	
	/**
	 * @return Retorna las consecuencias.
	 */
	public String getConsecuencias(){
		return consecuencias;
	} 
	
	/**
	 * Asigna las consecuencias.
	 * Validación: Máximo 100 caracteres.
	 * @param consecuencias
	 */
	public void setConsecuencias(String consecuencias) {
		this.consecuencias = Validacion.validarLargoString(consecuencias, 1000);
	}
	
	/**
	 * Retorna una representación en String del objeto Accidente.
	 */
	public String toString(){
				
		return String.format("Accidente [ ID: %d, RUT Cliente: %s, Día: %s, Hora: %s, "
				+ "Lugar: %s, Origen: %s, Consecuencias: %s ]",
				identificadorAccidente, rutCliente, dia, hora, lugar, origen, consecuencias);
	} 

}
