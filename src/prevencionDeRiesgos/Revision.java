/**
 * @author Luis Guevara
 */

package prevencionDeRiesgos;

import java.util.UUID;

/**
 * La clase Revision representa una revisión de seguridad asociada a una visita en terreno.
 * Contiene información sobre el identificador de la visita, un nombre descriptivo,
 * detalles a revisar y el estado de la revisión.
 * 
 */
public class Revision {
    // Atributos
	
	/**
	 * Identificador único de la revisión.
	 */
    private String identificadorRevision;
    
    /**
     * ID de la visita en terreno a la que se asocia esta revisión.
     */
    private int identificadorVisitaEnTerreno;
    
    /**
     * Nombre alusivo a la revisión. Mínimo 10, máximo 50 caracteres.
     */
    private String nombreAlusivoRevision;
    
    /**
     * Detalles de lo que se debe revisar. Máximo 100 caracteres.
     */
    private String detalleParaRevisar;
    
    /**
     * Estado de la revisión: 1 (sin problemas), 2 (con observaciones), 3 (no aprueba).
     */
    private int estado;

    /**
     * Constructor vacío que genera un identificador único para la revisión.
     */
    public Revision() {
        this.identificadorRevision = "RV" + UUID.randomUUID().toString();
    }

    /**
     * Constructor con todos los atributos, aplicando validaciones.
     * El identificador de revisión se genera automáticamente.
     *
     * @param identificadorVisitaEnTerreno ID de la visita asociada.
     * @param nombreAlusivoRevision Nombre de la revisión.
     * @param detalleParaRevisar Detalles a revisar.
     * @param estado Estado de la revisión (1, 2, o 3).
     * @throws IllegalArgumentException si algún parámetro no cumple las validaciones.
     */
    public Revision(int identificadorVisitaEnTerreno, String nombreAlusivoRevision,
                    String detalleParaRevisar, int estado) {
        this.identificadorRevision = "RV" + UUID.randomUUID().toString();

        try {
            this.identificadorVisitaEnTerreno = identificadorVisitaEnTerreno;
            this.setNombreAlusivoRevision(nombreAlusivoRevision);
            this.setDetalleParaRevisar(detalleParaRevisar);
            this.setEstado(estado);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error al crear Revisión: " + e.getMessage(), e);
        }
    }

    // Métodos

    /**
     * Obtiene el ID de la revisión.
     * @return ID de la revisión.
     */
    public String getIdentificadorRevision() {
        return identificadorRevision;
    }

    /**
     * Obtiene el ID de la visita en terreno asociada.
     * @return ID de la visita en terreno.
     */
    public int getIdentificadorVisitaEnTerreno() {
        return identificadorVisitaEnTerreno;
    }
    
    /**
     * Asigna el ID de la visita en terreno.
     * @param identificadorVisitaEnTerreno El ID de la visita en terreno a asignar.
     */
    public void setIdentificadorVisitaEnTerreno(int identificadorVisitaEnTerreno) {
        this.identificadorVisitaEnTerreno = identificadorVisitaEnTerreno;
    }

    /**
     * Obtiene el nombre alusivo de la revisión.
     * @return Nombre de la revisión.
     */
    public String getNombreAlusivoRevision() {
        return nombreAlusivoRevision;
    }

    /**
     * Asigna el nombre alusivo de la revisión.
     * @param nombre Nuevo nombre.
     * @throws IllegalArgumentException si el nombre no cumple los requisitos de longitud o es nulo/vacío.
     */
    public void setNombreAlusivoRevision(String nombre) {
        try {
            this.nombreAlusivoRevision = Validacion.validarLargoString(nombre, 10, 50);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Nombre alusivo a la revisión: " + e.getMessage(), e);
        }
    }

    /**
     * Obtiene el detalle de lo que se debe revisar.
     * @return Detalle a revisar.
     */
    public String getDetalleParaRevisar() {
        return detalleParaRevisar;
    }

    /**
     * Asigna el detalle de lo que se debe revisar.
     *
     * Según el enunciado: "máximo 100 caracteres" (no es obligatorio, pero si existe, debe cumplir el largo)
     *
     * @param detalle Nuevo detalle.
     * @throws IllegalArgumentException si el detalle excede los 100 caracteres.
     */
    public void setDetalleParaRevisar(String detalle) {
        if (detalle == null || detalle.trim().isEmpty()) {
            this.detalleParaRevisar = "";
        } else {
            try {
                this.detalleParaRevisar = Validacion.validarLargoString(detalle, 100);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Detalle para revisar: " + e.getMessage(), e);
            }
        }
    }

    /**
     * Obtiene el estado de la revisión.
     * @return Estado de la revisión (1, 2, o 3).
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Asigna el estado de la revisión.
     * Válido: 1 (sin problemas), 2 (con observaciones), 3 (no aprueba).
     * @param estado Nuevo estado a asignar.
     * @throws IllegalArgumentException si el estado no es 1, 2 o 3.
     */
    public void setEstado(int estado) {
        if (!Validacion.validarEstadoRevision(estado)) {
            throw new IllegalArgumentException("Estado inválido. Debe ser 1 (sin problemas), 2 (con observaciones), o 3 (no aprueba).");
        }
        this.estado = estado;
    }

    /**
     * Retorna una representación en String del objeto Revisión.
     * @return Una cadena de texto con los detalles de la revisión.
     */
    @Override
    public String toString() {
        String estadoTexto = switch (estado) {
            case 1 -> "Sin problemas";
            case 2 -> "Con observaciones";
            case 3 -> "No aprueba";
            default -> "Desconocido (estado inválido)"; // Este default no debería alcanzarse con las validaciones
        };

        return "Revisión #" + identificadorRevision + "\n" +
               "ID Visita en Terreno: " + identificadorVisitaEnTerreno + "\n" +
               "Nombre Alusivo: " + nombreAlusivoRevision + "\n" +
               "Detalle a Revisar: " + detalleParaRevisar + "\n" +
               "Estado: " + estadoTexto;
    }
}
