package prevencionDeRiesgos;

public class Revision {
	//Atributos
	private int identificadorRevision;
    private int identificadorVisitaEnTerreno;
    private String nombreAlusivoRevision;
    private String detalleParaRevisar;
    private int estado;
    /**
	 * Constructor vacío.
	 */
    public Revision() {}

    /**
     * Constructor con todos los atributos.
     * @param identificadorRevision ID de la revisión.
     * @param identificadorVisitaEnTerreno ID de la visita asociada.
     * @param nombreAlusivoRevision Nombre de la revisión.
     * @param detalleParaRevisar Detalles a revisar.
     * @param estado Estado de la revisión (1, 2, o 3).
     */
    public Revision(int identificadorRevision, int identificadorVisitaEnTerreno,
                    String nombreAlusivoRevision, String detalleParaRevisar, int estado) {
        this.identificadorRevision = identificadorRevision;
        this.identificadorVisitaEnTerreno = identificadorVisitaEnTerreno;
        this.nombreAlusivoRevision = nombreAlusivoRevision;
        this.detalleParaRevisar = detalleParaRevisar;
        this.estado = estado;
    }

 // Métodos
    /**
     * Obtiene el ID de la revisión.
     * @return ID de la revisión.
     */
    public int getIdentificadorRevision() { return identificadorRevision; }
    /**
     * Asigna el ID de la revisión.
     * @param id Nuevo ID.
     */
    public void setIdentificadorRevision(int id) { this.identificadorRevision = id; }

    /**
     * Obtiene el ID de la visita en terreno asociada.
     * @return ID de la visita en terreno.
     */
    public int getIdentificadorVisitaEnTerreno() { return identificadorVisitaEnTerreno; }
    /**
     * Asigna el ID de la visita en terreno asociada.
     * @param id Nuevo ID de la visita en terreno.
     */
    public void setIdentificadorVisitaEnTerreno(int id) { this.identificadorVisitaEnTerreno = id; }

    /**
     * Obtiene el nombre alusivo de la revisión.
     * @return Nombre de la revisión.
     */
    public String getNombreAlusivoRevision() { return nombreAlusivoRevision; }
    /**
     * Asigna el nombre alusivo de la revisión.
     * @param nombre Nuevo nombre.
     */
    public void setNombreAlusivoRevision(String nombre) { this.nombreAlusivoRevision = nombre; }

    /**
     * Obtiene el detalle de lo que se debe revisar.
     * @return Detalle a revisar.
     */
    public String getDetalleParaRevisar() { return detalleParaRevisar; }
    /**
     * Asigna el detalle de lo que se debe revisar.
     * @param detalle Nuevo detalle.
     */
    public void setDetalleParaRevisar(String detalle) { this.detalleParaRevisar = detalle; }

    /**
     * Obtiene el estado de la revisión.
     * @return Estado de la revisión (1, 2, o 3).
     */
    public int getEstado() { return estado; }
    /**
     * Asigna el estado de la revisión.
     * Válido: 1 (sin problemas), 2 (con observaciones), 3 (no aprueba).
     * @param estado Nuevo estado.
     */
    public void setEstado(int estado) {
        if (estado >= 1 && estado <= 3) {
            this.estado = estado;
        } else {
            System.out.println("Estado inválido. Debe ser 1 (sin problemas), 2 (con observaciones), o 3 (no aprueba).");
        }
    }

    /**
     * Retorna una representación en String del objeto Revisión.
     * @return Descripción de la revisión.
     */
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
