/**
 * @author Yerko Osorio
 */
package prevencionDeRiesgos;

import java.time.LocalTime;
import java.util.UUID;

/**
 * Representa una capacitación realizada por la compañía a uno de sus clientes.
 */
public class Capacitacion {

	//Atributos
	
	/**
	 * Identificador único para cada capacitación.
	 */
	private String identificador;
	
	/**
	 * RUT del cliente asociado a la capacitación.
	 */
	private String rutCliente;
	
	/**
	 * Día de la semana de la capacitación (Lunes a Domingo).
	 */
	private String dia;
	
	/**
	 * Hora de la capacitación en formato HH:MM.
	 */
	private LocalTime hora;
	
	/**
	 * Lugar donde se realizará la capacitación, con un largo entre 10 y 50 caracteres.
	 */
	private String lugar;
	
	/**
	 * Duración de la capacitación como texto (ej. "60 minutos"), con un máximo de 70 caracteres..
	 */
	private String duracion;
	
	/**
	 * Cantidad de asistentes esperados, debe ser menor a 1000.
	 */
	private int cantidadAsistentes;
	
	/**
     * Constructor vacío que genera un identificador único para la capacitación.
     */
	public Capacitacion() {
		// TODO Auto-generated constructor stub
		this.identificador = "CP" + UUID.randomUUID().toString();
	}


	/**
	 * Constructor con todos los atributos, excluyendo el identificador, que se
	 * genera automáticamente.
	 * @param rutCliente       RUT del cliente asociado a la capacitación.
	 * @param dia              Día de la semana de la capacitación.
	 * @param hora             Hora de la capacitación.
	 * @param lugar            Lugar donde se realizará la capacitación.
	 * @param duracion         Duración de la capacitación.
	 * @param cantidadAsistentes Cantidad de asistentes esperados.
	 */
	public Capacitacion(String rutCliente, String dia,
			String hora, String lugar, String duracion,
			int cantidadAsistentes) {
		
		this.identificador = "CP" + UUID.randomUUID().toString();
		this.setRutCliente(rutCliente);
	    this.setDia(dia);
	    this.setHora(hora);
	    this.setLugar(lugar);
	    this.setDuracion(duracion);
	    this.setCantidadAsistentes(cantidadAsistentes);
	}

	// --- Métodos Getters y Setters ---

	/**
	 * Retorna el identificador único de la capacitación.
	 * @return El identificador de la capacitación.
	 */
	public String getIdentificador() {
		return identificador;
	}
	
	/**
	 * Asigna el RUT del cliente asociado a la capacitación.
	 * @param rutCliente El RUT del cliente a asignar.
	 */
	public void setRutCliente(String rutCliente) {
		this.rutCliente = Validacion.validarRut(rutCliente);
		
	}
	
	/**
	 * Retorna el RUT del cliente asociado.
	 * @return El RUT del cliente.
	 */
	public String getRutCliente() {
		return rutCliente;
	}
	
	/**
	 * Asigna el día de la semana.
	 * @param dia El día de la semana a asignar.
	 */
	public void setDia(String dia) {
	
		this.dia = Validacion.validarDia(dia);
	}
	
	/**
	 * Retorna el día de la semana de la capacitación.
	 * @return El día de la capacitación.
	 */
	public String getDia() {
		return dia;
	}
	
	/**
	 * Asigna la hora de la capacitación.
	 * @param hora La hora a asignar en formato HH:MM.
	 */
	public void setHora(String hora) {
		this.hora = Validacion.validarHora(hora);
	}
	
	/**
	 * Retorna la hora de la capacitación en formato de texto.
	 * @return La hora de la capacitación como String.
	 */
	public String getHora() {
		
		return Validacion.transformarHoraAstring(hora);
	}
	
	/**
	 * Asigna el lugar de la capacitación.
	 * @param lugar El lugar a asignar.
	 */
	public void setLugar(String lugar) {
		this.lugar = Validacion.validarLargoString(lugar, 10, 50);
	}
	
	/**
	 * Retorna el lugar de la capacitación.
	 * @return El lugar de la capacitación.
	 */
	public String getLugar() {
		return lugar;
	}
	
	/**
	 * Asigna la duración de la capacitación.
	 * @param duracion La duración a asignar.
	 */
	public void setDuracion(String duracion) {
		this.duracion = Validacion.validarLargoString(duracion, 70);
	}
	
	
	/**
	 * Retorna la duración de la capacitación.
	 * @return La duración de la capacitación.
	 */
	public String getDuracion(){
		return duracion;
	}
	
	/**
	 * Asigna la cantidad de asistentes a la capacitación.
	 * @param cantidadAsistentes La cantidad de asistentes a asignar.
	 */
	public void setCantidadAsistentes(int cantidadAsistentes) {
		this.cantidadAsistentes = Validacion.validarNumeroMaximo(cantidadAsistentes, 1000);
	}

	/**
	 * Retorna la cantidad de asistentes de la capacitación.
	 * @return La cantidad de asistentes.
	 */
	public int getCantidadAsistentes() {
		return cantidadAsistentes;
	} 
	
	/**
	 * Retorna una representación en String del objeto Capacitacion.
	 * @return Una cadena con los detalles de la capacitación.
	 */
	public String toString() {
		
		return String.format("Capacitación [ ID: %s, RUT Cliente: %s, Día: %s, Hora: %s,"
				+ " Lugar: %s, Duración: %s, Cantidad Asistentes: %d ]", 
				identificador, rutCliente, dia, hora, lugar, duracion, cantidadAsistentes);
	} 

	/**
	 * Muestra los detalles de la capacitación en un formato específico.
	 * @return Una cadena con el detalle de la capacitación.
	 */
	public String mostrarDetalle(){
		return String.format("La capacitación será en %s a las %s del día %s,"
				+ " y durará %s minutos", lugar, hora, dia, duracion);

	}
}
