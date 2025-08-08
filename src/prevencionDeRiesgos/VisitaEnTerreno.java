/**
 * @author Luis Guevara
 */

package prevencionDeRiesgos;

import java.time.LocalDate; // Necesario para almacenar fechas validadas
import java.time.LocalTime; // Necesario para almacenar horas validadas
import java.util.UUID;

public class VisitaEnTerreno {
    // Atributos
	
	/** 
	 * Identificador único de la visita, generado automáticamente.
	 * 
	 */
    private String identificadorVisitaEnTerreno;
    
    /**
     *  RUT del cliente en formato chileno, validado.
     */
    private String rutCliente;
    
    /**
     * Día de la visita, almacenado como un objeto LocalDate.
     */
    private LocalDate dia;
    
    /**
     * Hora de la visita, almacenada como un objeto LocalTime.
     */
    private LocalTime hora; 
    
    /**
     * Lugar o dirección de la visita.
     */
    private String lugar;
    
    /**
     * Comentarios adicionales sobre la visita, con un largo máximo.
     */
    private String comentarios;

    /**
     * Constructor vacío. Genera un UUID para el identificador.
     */
    public VisitaEnTerreno() {
        this.identificadorVisitaEnTerreno = "VT" + UUID.randomUUID().toString();
    }

    /**
     * Constructor con atributos relevantes, aplicando validaciones.
     * El identificador de visita se genera automáticamente.
     *
     * @param rutCliente RUT del cliente (ej. "12.345.678-9").
     * @param dia Día de la visita en formato "DD/MM/AAAA".
     * @param hora Hora de la visita en formato "HH:MM".
     * @param lugar Lugar de la visita.
     * @param comentarios Comentarios de la visita (máximo 100 caracteres).
     * @throws IllegalArgumentException si algún parámetro no cumple las validaciones.
     */
    public VisitaEnTerreno(String rutCliente, String dia,
                           String hora, String lugar, String comentarios) {
        // El identificador siempre se genera automáticamente para asegurar la unicidad
        this.identificadorVisitaEnTerreno = "VT" + UUID.randomUUID().toString();

        try {
        	setRutCliente(rutCliente);
            setDia(dia);
            setHora(hora);
            setLugar(lugar);
            setComentarios(comentarios);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Error al crear VisitaEnTerreno: " + e.getMessage(), e);
		}
            
       
    }

    // Métodos

    /**
     * Obtiene el ID de la visita.
     * @return ID de la visita.
     */
    public String getIdentificadorVisitaEnTerreno() {
        return identificadorVisitaEnTerreno;
    }

    /**
     * Obtiene el RUT del cliente.
     * @return RUT del cliente.
     */
    public String getRutCliente() {
        return rutCliente;
    }

    /**
     * Asigna el RUT del cliente, aplicando la validación de RUT.
     * @param rutCliente Nuevo RUT en formato "99.999.999-9".
     * @throws IllegalArgumentException si el RUT no es válido.
     */
    public void setRutCliente(String rutCliente) {
        try {
            // Validacion.validarRut lanzará IllegalArgumentException si es inválido
            this.rutCliente = Validacion.validarRut(rutCliente);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("RUT de cliente: " + e.getMessage(), e);
        }
    }

    /**
     * Obtiene el día de la visita como un objeto LocalDate.
     * @return Día de la visita.
     */
    public LocalDate getDia() {
        return dia;
    }

    /**
     * Asigna el día de la visita, validando el formato "DD/MM/AAAA".
     * @param dia Nuevo día en formato String.
     * @throws IllegalArgumentException si el formato del día es inválido.
     */
    public void setDia(String dia) {
        try {
            // Validacion.validarFecha analizará y validará la fecha, y si es exitoso, la asignará a 'dia'.
            this.dia = Validacion.validarFecha(dia);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Día de la visita: " + e.getMessage(), e);
        }
    }

    /**
     * Obtiene la hora de la visita como un objeto LocalTime.
     * @return Hora de la visita.
     */
    public LocalTime getHora() {
        return hora;
    }

    /**
     * Asigna la hora de la visita, validando el formato "HH:MM".
     * @param hora Nueva hora en formato String.
     * @throws IllegalArgumentException si el formato de la hora es inválido.
     */
    public void setHora(String hora) {
        try {
            // Validacion.validarHora analizará y validará la hora, y si es exitoso, la asignará a 'hora'.
            this.hora = Validacion.validarHora(hora);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Hora de la visita: " + e.getMessage(), e);
        }
    }

    /**
     * Obtiene el lugar de la visita.
     * @return Lugar.
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * Asigna el lugar de la visita, asegurando que no sea nulo o vacío.
     * @param lugar Nuevo lugar.
     * @throws IllegalArgumentException si el lugar es nulo o vacío.
     */
    public void setLugar(String lugar) {
        // Asumimos que el lugar es obligatorio y no puede ser solo espacios en blanco.
        // Si necesitas una validación de longitud, puedes agregarla aquí usando Validacion.validarLargoString.
        if (lugar == null || lugar.trim().isEmpty()) {
            throw new IllegalArgumentException("El lugar de la visita es obligatorio y no puede estar vacío.");
        }
        
        this.lugar = Validacion.validarLargoString(lugar, 10, 50);
    }

    /**
     * Obtiene los comentarios de la visita.
     * @return Comentarios.
     */
    public String getComentarios() {
        return comentarios;
    }

    /**
     * Asigna los comentarios de la visita, validando su longitud (máximo 100 caracteres).
     * @param comentarios Nuevos comentarios.
     * @throws IllegalArgumentException si los comentarios exceden el largo permitido.
     */
    public void setComentarios(String comentarios) {
        // Los comentarios pueden ser nulos o vacíos si no son obligatorios,
        // pero si hay texto, Validacion.validarLargoString(String, int) verificará su longitud.
        if (comentarios != null && !comentarios.trim().isEmpty()) {
            try {
                this.comentarios = Validacion.validarLargoString(comentarios, 100);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Comentarios: " + e.getMessage(), e);
            }
        } else {
            this.comentarios = ""; // Se asigna una cadena vacía si los comentarios son nulos o solo espacios
        }
    }

    /**
     * Retorna una descripción completa de la visita.
     * Utiliza los métodos de transformación de fecha y hora de Validacion para la representación String.
     * @return Descripción de la visita.
     */
    @Override
    public String toString() {
        // Usamos los métodos de transformación de Validacion para formatear la salida
        // Manejamos posibles valores nulos para dia y hora si el objeto no se inicializó completamente
        String diaStr = (dia != null) ? Validacion.transformarFechaAstring(dia) : "N/A";
        String horaStr = (hora != null) ? Validacion.transformarHoraAstring(hora) : "N/A";

        return "Visita en Terreno #" + identificadorVisitaEnTerreno + "\n" +
               "RUT Cliente: " + rutCliente + "\n" +
               "Día: " + diaStr + "\n" +
               "Hora: " + horaStr + "\n" +
               "Lugar: " + lugar + "\n" +
               "Comentarios: " + comentarios;
    }
}




