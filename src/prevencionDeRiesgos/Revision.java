/**
 * @author Luis Guevara
 * @version 1.0
*/

package prevencionDeRiesgos;

import java.util.UUID; // Necesario para generar IDs únicos

/**
 * resenta una revisión dentro del sistema de prevención de riesgos.
 * Contiene información detallada sobre una revisión específica realizada a un cliente.
 * Se encarga de validar los datos de sus atributos y generar un identificador único
 * para cada instancia.
 */
public class Revision {
	
	// ======================= ATRIBUTOS =======================

    /**
     * Identificador único de la revisión.
     * Se genera automáticamente al crear la instancia para asegurar unicidad.
     */
    private String identificadorRevision;

    /**
     * ID de la visita en terreno a la que está asociada esta revisión.
     * Debe ser un valor positivo.
     */
    private int identificadorVisitaEnTerreno;

    /**
     * Nombre alusivo de la revisión.
     * Obligatorio y con un largo entre 10 y 50 caracteres.
     */
    private String nombreAlusivoRevision;

    /**
     * Detalle específico para la revisión.
     * Opcional, pero si se ingresa, debe tener un largo máximo de 100 caracteres.
     */
    private String detalleParaRevisar;

    /**
     * Estado de la revisión, representado por un valor numérico.
     * Válido: 1 (Sin problemas), 2 (Con observaciones), 3 (No aprueba).
     */
    private int estado;

    // ======================= CONSTRUCTORES =======================

    /**
     * Constructor por defecto.
     * Genera un identificador de revisión único (RV + UUID).
     */
    public Revision() {
        this.identificadorRevision = "RV" + UUID.randomUUID().toString();
    }

    /**
     * Constructor que inicializa una revisión con sus atributos principales.
     * El identificador de revisión se genera automáticamente y se aplican
     * las validaciones correspondientes a cada atributo.
     *
     * @param identificadorVisitaEnTerreno ID de la visita asociada.
     * @param nombreAlusivoRevision Nombre alusivo de la revisión (10-50 caracteres).
     * @param detalleParaRevisar Detalles a revisar (máximo 100 caracteres).
     * @param estado Estado de la revisión (1, 2 o 3).
     * @throws IllegalArgumentException Si algún parámetro no cumple con las validaciones.
     */
    public Revision(int identificadorVisitaEnTerreno, String nombreAlusivoRevision,
                    String detalleParaRevisar, int estado) {
        this(); // Llama al constructor por defecto para generar el ID.
        try {
            setIdentificadorVisitaEnTerreno(identificadorVisitaEnTerreno);
            setNombreAlusivoRevision(nombreAlusivoRevision);
            setDetalleParaRevisar(detalleParaRevisar);
            setEstado(estado);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error al crear la Revisión: " + e.getMessage(), e);
        }
    }

    // ======================= GETTERS & SETTERS =======================

    /**
     * Obtiene el identificador único de la revisión.
     *
     * @return El identificador de la revisión.
     */
    public String getIdentificadorRevision() {
        return identificadorRevision;
    }
    
    /**
     * Obtiene el ID de la visita en terreno asociada.
     *
     * @return El ID de la visita en terreno.
     */
    public int getIdentificadorVisitaEnTerreno() {
        return identificadorVisitaEnTerreno;
    }

    /**
     * Establece el ID de la visita en terreno.
     *
     * @param identificadorVisitaEnTerreno El ID de la visita a asignar.
     * @throws IllegalArgumentException Si el ID es menor o igual a cero.
     */
    public void setIdentificadorVisitaEnTerreno(int identificadorVisitaEnTerreno) {
        if (identificadorVisitaEnTerreno <= 0) {
            throw new IllegalArgumentException("El ID de la visita en terreno debe ser un número positivo.");
        }
        this.identificadorVisitaEnTerreno = identificadorVisitaEnTerreno;
    }

    /**
     * Obtiene el nombre alusivo de la revisión.
     *
     * @return El nombre de la revisión.
     */
    public String getNombreAlusivoRevision() {
        return nombreAlusivoRevision;
    }

    /**
     * Establece el nombre alusivo de la revisión.
     * Valida que la longitud esté entre 10 y 50 caracteres.
     *
     * @param nombre El nombre a asignar.
     * @throws IllegalArgumentException Si el nombre es nulo, vacío o no cumple con la longitud requerida.
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
     *
     * @return El detalle a revisar.
     */
    public String getDetalleParaRevisar() {
        return detalleParaRevisar;
    }

    /**
     * Establece el detalle de lo que se debe revisar.
     * Si el detalle es nulo o vacío, se asigna una cadena vacía.
     * Si no, valida que no exceda los 100 caracteres.
     *
     * @param detalle El detalle a asignar.
     * @throws IllegalArgumentException Si el detalle excede los 100 caracteres.
     */
    public void setDetalleParaRevisar(String detalle) {
        if (detalle == null || detalle.trim().isEmpty()) {
            this.detalleParaRevisar = "";
            return;
        }
        try {
            this.detalleParaRevisar = Validacion.validarLargoString(detalle, 1, 100);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Detalle para revisar: " + e.getMessage(), e);
        }
    }

    /**
     * Obtiene el estado de la revisión.
     *
     * @return El estado de la revisión (1, 2 o 3).
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la revisión.
     * Valida que el estado sea un valor numérico entre 1 y 3.
     *
     * @param estado El estado a asignar.
     * @throws IllegalArgumentException Si el estado no es 1, 2 o 3.
     */
    public void setEstado(int estado) {
        if (estado < 1 || estado > 3) {
            throw new IllegalArgumentException("Estado inválido. Debe ser 1 (sin problemas), 2 (con observaciones), o 3 (no aprueba).");
        }
        this.estado = estado;
    }

    // ======================= MÉTODOS ESPECIALES =======================

    /**
     * Retorna una representación en String del objeto Revisión.
     * Proporciona una descripción completa de la revisión, incluyendo
     * su identificador, nombre, detalle y una descripción textual de su estado.
     *
     * @return Una cadena de texto con la información formateada de la revisión.
     */
    @Override
    public String toString() {
        String estadoTexto;
        switch (estado) {
            case 1:
                estadoTexto = "Sin problemas";
                break;
            case 2:
                estadoTexto = "Con observaciones";
                break;
            case 3:
                estadoTexto = "No aprueba";
                break;
            default:
                estadoTexto = "Desconocido (estado inválido)";
                break;
        }
        return "Revisión #" + identificadorRevision + "\n" +
               "ID Visita en Terreno: " + identificadorVisitaEnTerreno + "\n" +
               "Nombre Alusivo: " + nombreAlusivoRevision + "\n" +
               "Detalle a Revisar: " + detalleParaRevisar + "\n" +
               "Estado: " + estadoTexto;
    }
}