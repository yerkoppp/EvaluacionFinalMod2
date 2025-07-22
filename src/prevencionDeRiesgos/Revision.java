package prevencionDeRiesgos;
/**
 * @author Luis Guevara
 */
public class Revision {
	private int identificadorRevision;
    private int identificadorVisitaEnTerreno;
    private String nombreAlusivoRevision;
    private String detalleParaRevisar;
    private int estado;

    public Revision() {}

    public Revision(int identificadorRevision, int identificadorVisitaEnTerreno,
                    String nombreAlusivoRevision, String detalleParaRevisar, int estado) {
        this.identificadorRevision = identificadorRevision;
        this.identificadorVisitaEnTerreno = identificadorVisitaEnTerreno;
        this.nombreAlusivoRevision = nombreAlusivoRevision;
        this.detalleParaRevisar = detalleParaRevisar;
        this.estado = estado;
    }

    public int getIdentificadorRevision() { return identificadorRevision; }
    public void setIdentificadorRevision(int id) { this.identificadorRevision = id; }

    public int getIdentificadorVisitaEnTerreno() { return identificadorVisitaEnTerreno; }
    public void setIdentificadorVisitaEnTerreno(int id) { this.identificadorVisitaEnTerreno = id; }

    public String getNombreAlusivoRevision() { return nombreAlusivoRevision; }
    public void setNombreAlusivoRevision(String nombre) { this.nombreAlusivoRevision = nombre; }

    public String getDetalleParaRevisar() { return detalleParaRevisar; }
    public void setDetalleParaRevisar(String detalle) { this.detalleParaRevisar = detalle; }

    public int getEstado() { return estado; }
    public void setEstado(int estado) {
        if (estado >= 1 && estado <= 3) {
            this.estado = estado;
        } else {
            System.out.println("Estado inválido. Debe ser 1 (sin problemas), 2 (con observaciones), o 3 (no aprueba).");
        }
    }

    @Override
    public String toString() {
        String estadoTexto = switch (estado) {
            case 1 -> "Sin problemas";
            case 2 -> "Con observaciones";
            case 3 -> "No aprueba";
            default -> "Desconocido";
        };

        return "Revisión #" + identificadorRevision + "\n" +
               "ID Visita en Terreno: " + identificadorVisitaEnTerreno + "\n" +
               "Nombre Alusivo: " + nombreAlusivoRevision + "\n" +
               "Detalle a Revisar: " + detalleParaRevisar + "\n" +
               "Estado: " + estadoTexto;
    }
}


