/**
 * @author Yerko Osorio
 */
package prevencionDeRiesgos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Representa una capacitación realizada por la compañía.
 */
public class Capacitacion {

	//Atributos
	private String identificador;
	private String rutCliente;
	private String dia;
	private LocalTime hora;
	private String lugar;
	private String duracion;
	private int cantidadAsistentes;
	
	/**
	 * Constructor vacío.
	 */
	public Capacitacion() {
		// TODO Auto-generated constructor stub
		this.identificador = "CP" + UUID.randomUUID().toString();
	}


	/**
	 * Constructor con todos los atributos.
	 * @param identificador
	 * @param rutCliente
	 * @param dia
	 * @param hora
	 * @param lugar
	 * @param duracion
	 * @param cantidadAsistentes
	 */
	public Capacitacion(int identificador, String rutCliente, String dia,
			String hora, String lugar, String duracion,
			int cantidadAsistentes) {
		super();
		
		this.identificador = "CP" + UUID.randomUUID().toString();
		this.rutCliente = Validacion.validarRut(rutCliente);
		this.dia = Validacion.validarDia(dia);
		this.hora = Validacion.validarHora(hora);
		this.lugar = Validacion.validarLargoString(lugar, 10, 50);
		this.duracion = Validacion.validarLargoString(duracion, 70);
		this.cantidadAsistentes = Validacion.validarNumeroMaximo(cantidadAsistentes, 1000);
	}

	//Métodos
	/**
	 * Asigna el identificador. Privado. No puede modificarse el identificador una vez creado el objeto. 
	 * @param identificador
	 */
	private void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	

	/**
	 * Retorna el identificador.
	 * @return
	 */
	public String getIdentificador() {
		return identificador;
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
	 * Retorna el RUT del cliente.
	 * @return
	 */
	public String getRutCliente() {
		return rutCliente;
	}
	
	/**
	 * Asigna el día de la semana.
	 * Validación: Debe ser un valor permitido entre "lunes" y "domingo" (en ese formato).
	 * @param dia
	 */
	public void setDia(String dia) {
	
		this.dia = Validacion.validarDia(dia);
	}
	
	/**
	 * Retorna el día.
	 */
	public String getDia() {
		return dia;
	}
	
	/**
	 * Asigna la hora.
	 * Validación: Debe ser una hora válida del día, en formato HH:MM (hora desde 0 a 23, minutos entre 0 y 59).
	 * @param hora
	 */
	public void setHora(String hora) {
		this.hora = Validacion.validarHora(hora);
	}
	
	/**
	 * 
	 * @return Retorna la hora de la capacitación.
	 */
	public String getHora() {
		
		return Validacion.transformarHoraAstring(hora);
	}
	
	/**
	 * Asigna el lugar.
	 * Validación: Obligatorio, mínimo 10 caracteres, máximo 50.
	 * @param lugar
	 */
	public void setLugar(String lugar) {
		this.lugar = Validacion.validarLargoString(lugar, 10, 50);
	}
	
	/**
	 * 
	 * @return Retorna el lugar.
	 */
	public String getLugar() {
		return lugar;
	}
	
	/**
	 * Asigna la duración.
	 * Validación: Máximo 70 caracteres.
	 * @param duracion
	 */
	public void setDuracion(String duracion) {
		this.duracion = Validacion.validarLargoString(duracion, 70);
	}
	
	
	/**
	 * 
	 * @return Retorna la duración.
	 */
	public String getDuracion(){
		return duracion;
	}
	
	/**
	 * Asigna la cantidad de asistentes.
	 * Validación: Obligatorio, número entero menor que 1000.
	 * @param cantidadAsistentes
	 */
	public void setCantidadAsistentes(int cantidadAsistentes) {
		this.cantidadAsistentes = Validacion.validarNumeroMaximo(cantidadAsistentes, 1000);
	}

	/**
	 * @return Retorna la cantidad de asistentes.
	 */
	public int getCantidadAsistentes() {
		return cantidadAsistentes;
	} 
	
	/**
	 * 	Retorna una representación en String del objeto Capacitacion.
	 */
	public String toString() {
		
		return String.format("Capacitación [ ID: %d, RUT Cliente: %s, Día: %s, Hora: %s,"
				+ " Lugar: %s, Duración: %s, Cantidad Asistentes: %d ]", 
				identificador, rutCliente, dia, hora, lugar, duracion, cantidadAsistentes);
	} 

	/**
	 * Retorna un String con el texto.
	 */
	public String mostrarDetalle(){
		return String.format("La capacitación será en %s a las %s del día %s,"
				+ " y durará %s minutos", lugar, hora, dia, duracion);

	}
}
