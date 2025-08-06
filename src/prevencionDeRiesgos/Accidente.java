/**
 * @author Yerko Osorio
 */
package prevencionDeRiesgos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/**
 * Representa un accidente registrado, detallando la fecha, hora, lugar, origen
 * y consecuencias del mismo.
 */
public class Accidente {
	
	/**
	 * Identificador único para cada accidente, generado automáticamente.
	 */
	private String identificadorAccidente;
	
	/**
	 * RUT del cliente asociado al accidente.
	 */
	private String rutCliente;
	
	/**
	 * Fecha del accidente en formato DD/MM/AAAA.
	 */
	private LocalDate dia;
	
	/**
	 * Hora del accidente en formato HH:MM.
	 */
	private LocalTime hora;
	
	/**
	 * Lugar donde ocurrió el accidente.
	 */
	private String lugar;
	
	/**
	 * Origen o causa del accidente.
	 */
	private String origen;
	
	/**
	 * Consecuencias del accidente.
	 */
	private String consecuencias;

	/**
	 * Constructor vacío que genera un identificador único para el accidente.
	 */
	public Accidente() {	
		this.identificadorAccidente = "AC" + UUID.randomUUID().toString();
	}
	
	/**
	 * Constructor con todos los atributos, excluyendo el identificador, que se
	 * genera automáticamente.
	 * @param rutCliente    RUT del cliente involucrado.
	 * @param dia           Fecha del accidente en formato String "DD/MM/AAAA".
	 * @param hora          Hora del accidente en formato String "HH:MM".
	 * @param lugar         Lugar donde ocurrió el accidente.
	 * @param origen        Origen o causa del accidente.
	 * @param consecuencias Consecuencias del accidente.
	 */
	public Accidente(String rutCliente, String dia,
			String hora, String lugar, String origen,
			String consecuencias) {
		super();

		this.identificadorAccidente = "AC" + UUID.randomUUID().toString();
		this.rutCliente = Validacion.validarRut(rutCliente);
		this.dia = Validacion.validarFecha(dia);
		this.hora = Validacion.validarHora(hora);
		this.lugar = Validacion.validarLargoString(lugar, 10, 50);
		this.origen = Validacion.validarLargoString(origen, 100);
		this.consecuencias = Validacion.validarLargoString(consecuencias, 100);
	}
	
	
	//Métodos:
	/**
	 * Retorna el identificador único del accidente.
	 * @return El identificador del accidente.
	 */
	public String getIdentificadorAccidente() {
		return identificadorAccidente;
	}
	
	/**
	 * Retorna el RUT del cliente accidentado.
	 * @return El RUT del cliente.
	 */
	public String getRutCliente() {
		return rutCliente;
	}
	
	/**
	 * Asigna el RUT del cliente asociado.
	 * @param rutCliente El RUT del cliente a asignar.
	 */
	public void setRutCliente(String rutCliente) {
		this.rutCliente = Validacion.validarRut(rutCliente);
	}


	/**
	 * Retorna la fecha del accidente en formato String "DD/MM/AAAA".
	 * @return El día del accidente como String.
	 */
	public String getDia() {
		return Validacion.transformarFechaAstring(dia);
	}
	
	/**
	 * Asigna la fecha del accidente.
	 * @param dia La fecha del accidente en formato String "DD/MM/AAAA".
	 */
	public void setDia(String dia) {
		this.dia = Validacion.validarFecha(dia);
	}



	/**
	 * Retorna la hora del accidente en formato String "HH:MM".
	 * @return La hora del accidente como String.
	 */
	public String getHora() {
		return Validacion.transformarHoraAstring(hora);
	}
	
	/**
	 * Asigna la hora del accidente.
	 * @param hora La hora a asignar en formato String "HH:MM".
	 */
	public void setHora(String hora) {
		
		this.hora = Validacion.validarHora(hora);
	} 


	/**
	 * Retorna el lugar del accidente.
	 * @return El lugar del accidente.
	 */
	public String getLugar() {
		return lugar;
	}
	
	/**
	 * Asigna el lugar del accidente.
	 * @param lugar El lugar a asignar.
	 */
	public void setLugar(String lugar) {
		this.lugar = Validacion.validarLargoString(lugar, 10, 50);
	}


	/**
	 * Retorna el origen del accidente.
	 * @return El origen del accidente.
	 */
	public String getOrigen() {
		return origen;
	}

	/**
	 * Asigna el origen del accidente.
	 * @param origen El origen a asignar.
	 */
	public void setOrigen(String origen){
		this.origen = Validacion.validarLargoString(origen, 100);
	}
	
	
	/**
	 * Retorna las consecuencias del accidente.
	 * @return Las consecuencias del accidente.
	 */
	public String getConsecuencias(){
		return consecuencias;
	} 
	
	/**
	 * Asigna las consecuencias del accidente.
	 * @param consecuencias Las consecuencias a asignar.
	 */
	public void setConsecuencias(String consecuencias) {
		this.consecuencias = Validacion.validarLargoString(consecuencias, 100);
	}
	
	/**
	 * Retorna una representación en String del objeto Accidente.
	 * @return Una cadena con los detalles del accidente.
	 */
	@Override
	public String toString(){
				
		return String.format("Accidente [ ID: %s, RUT Cliente: %s, Día: %s, Hora: %s, "
				+ "Lugar: %s, Origen: %s, Consecuencias: %s ]",
				identificadorAccidente, rutCliente, dia, hora, lugar, origen, consecuencias);
	} 

}
