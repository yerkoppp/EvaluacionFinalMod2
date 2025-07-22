package prevencionDeRiesgos;
/**
 * @author Luis Guevara
 */
public class VisitaEnTerreno {
	private int identificadorVisitaEnTerreno;
    private int rutCliente;
    private String dia;
    private String hora;
    private String lugar;
    private String comentarios;

    public VisitaEnTerreno() {}

    public VisitaEnTerreno(int identificadorVisitaEnTerreno, int rutCliente, String dia,
                           String hora, String lugar, String comentarios) {
        this.identificadorVisitaEnTerreno = identificadorVisitaEnTerreno;
        this.rutCliente = rutCliente;
        this.dia = dia;
        this.hora = hora;
        this.lugar = lugar;
        this.comentarios = comentarios;
    }

    public int getIdentificadorVisitaEnTerreno() { return identificadorVisitaEnTerreno; }
    public void setIdentificadorVisitaEnTerreno(int id) { this.identificadorVisitaEnTerreno = id; }

    public int getRutCliente() { return rutCliente; }
    public void setRutCliente(int rutCliente) { this.rutCliente = rutCliente; }

    public String getDia() { return dia; }
    public void setDia(String dia) { this.dia = dia; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public String getLugar() { return lugar; }
    public void setLugar(String lugar) { this.lugar = lugar; }

    public String getComentarios() { return comentarios; }
    public void setComentarios(String comentarios) { this.comentarios = comentarios; }

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



