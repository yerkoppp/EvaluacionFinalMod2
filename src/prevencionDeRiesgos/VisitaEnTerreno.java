package prevencionDeRiesgos;

import java.util.UUID;

/**
 * @author Luis Guevara
 */
public class VisitaEnTerreno {
	//Atributos
    private String identificadorVisitaEnTerreno;
    private String rutCliente;
    private String dia;
    private String hora;
    private String lugar;
    private String comentarios;

    /**
	 * Constructor vacío.
	 */
    public VisitaEnTerreno() {
    	this.identificadorVisitaEnTerreno = "VT" + UUID.randomUUID().toString();
    }
    
    /**
     * Constructor
     * @param identificadorVisitaEnTerreno
     * @param rutCliente 
     * @param dia 
     * @param hora
     * @param lugar
     * @param comentarios
     */
    public VisitaEnTerreno(String rutCliente, String dia,
                           String hora, String lugar, String comentarios) {
        
    	this.identificadorVisitaEnTerreno = "VT" + UUID.randomUUID().toString();
        this.rutCliente = Validacion.validarRut(rutCliente);
        this.dia = dia;
        this.hora = hora;
        this.lugar = lugar;
        this.comentarios = comentarios;
    }
    

  //Métodos
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
     * Asigna el RUT del cliente.
     * @param rutCliente Nuevo RUT.
     */
    public void setRutCliente(String rutCliente) {
        this.rutCliente = Validacion.validarRut(rutCliente);
    }

    /**
     * Obtiene el día de la visita.
     * @return Día.
     */
    public String getDia() {
        return dia;
    }

    /**
     * Asigna el día de la visita.
     * @param dia Nuevo día.
     */
    public void setDia(String dia) {
        this.dia = dia;
    }

    /**
     * Obtiene la hora de la visita.
     * @return Hora.
     */
    public String getHora() {
        return hora;
    }

    /**
     * Asigna la hora de la visita.
     * @param hora Nueva hora.
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * Obtiene el lugar de la visita.
     * @return Lugar.
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * Asigna el lugar de la visita.
     * @param lugar Nuevo lugar.
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * Obtiene los comentarios de la visita.
     * @return Comentarios.
     */
    public String getComentarios() {
        return comentarios;
    }

    /**
     * Asigna los comentarios de la visita.
     * @param comentarios Nuevos comentarios.
     */
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * Retorna una descripción completa de la visita.
     * @return Descripción de la visita.
     */
    @Override
    public String toString() {
        return "Visita en Terreno #" + identificadorVisitaEnTerreno + "\n" +
               "RUT Cliente: " + rutCliente + "\n" +
               "Día: " + dia + "\n" +
               "Hora: " + hora + "\n" +
               "Lugar: " + lugar + "\n" +
               "Comentarios: " + comentarios;
    }
}





