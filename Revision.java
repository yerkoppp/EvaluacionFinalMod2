package prevencionDeRiesgos;

import java.util.UUID; // Necesario para generar IDs únicos

/**
 * @author Luis Guevara
 */
public class Revision {
    // Atributos
    private String identificadorRevision;
    private int identificadorVisitaEnTerreno;
    private String nombreAlusivoRevision;
    private String detalleParaRevisar;
    private int estado;

    /**
     * Constructor vacío. Genera un ID de revisión único.
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
        // El identificador de revisión siempre se genera automáticamente para unicidad
        this.identificadorRevision = "RV" + UUID.randomUUID().toString();

        // Aplicamos las validaciones llamando a los setters. Esto asegura que la lógica de validación se reutilice.
        try {
            this.setIdentificadorVisitaEnTerreno(identificadorVisitaEnTerreno);
            this.setNombreAlusivoRevision(nombreAlusivoRevision);
            this.setDetalleParaRevisar(detalleParaRevisar);
            this.setEstado(estado);
        } catch (IllegalArgumentException e) {
            // Relanzamos la excepción con un mensaje más específico para fallos en el constructor
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

    /*
     * Se elimina el setter para identificadorRevision.
     * Si el ID siempre se genera con UUID, no debería ser modificable externamente
     * para mantener su unicidad y lógica de generación.
     */
    // public void setIdentificadorRevision(String id) {
    //     this.identificadorRevision = id;
    // }

    /**
     * Obtiene el ID de la visita en terreno asociada.
     * @return ID de la visita en terreno.
     */
    public int getIdentificadorVisitaEnTerreno() {
        return identificadorVisitaEnTerreno;
    }

    /**
     * Asigna el ID de la visita en terreno asociada.
     *
     * Según el enunciado: "obligatorio, número de la visita a la que se asóciala revisión."
     * Asumo que un ID válido debe ser un número positivo.
     *
     * @param id Nuevo ID de la visita en terreno.
     * @throws IllegalArgumentException si el ID no es un número positivo.
     */
    public void setIdentificadorVisitaEnTerreno(int id) {
        // Podrías crear Validacion.esNumeroPositivo(int) si lo usas en varios lugares.
        // Por ahora, una simple verificación es suficiente.
        if (id <= 0) {
            throw new IllegalArgumentException("El identificador de visita en terreno es obligatorio y debe ser un número positivo.");
        }
        this.identificadorVisitaEnTerreno = id;
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
     *
     * Según el enunciado: "obligatorio, mínimo 10 caracteres, máximo 50"
     *
     * @param nombre Nuevo nombre.
     * @throws IllegalArgumentException si el nombre no cumple los requisitos de longitud o es nulo/vacío.
     */
    public void setNombreAlusivoRevision(String nombre) {
        try {
            // Validacion.validarLargoString(String, int, int) validará la longitud y la obligatoriedad.
            this.nombreAlusivoRevision = Validacion.validarLargoString(nombre, 10, 50);
        } catch (IllegalArgumentException e) {
            // Se propaga la excepción con un mensaje más específico para este campo
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
        // Si el detalle es nulo o vacío, lo manejamos como un valor aceptable (no es obligatorio).
        if (detalle == null || detalle.trim().isEmpty()) {
            this.detalleParaRevisar = ""; // O podrías dejarlo como null si prefieres.
        } else {
            try {
                // Validacion.validarLargoString(String, int) verificará que no exceda los 100 caracteres.
                this.detalleParaRevisar = Validacion.validarLargoString(detalle, 100);
            } catch (IllegalArgumentException e) {
                // Se propaga la excepción con un mensaje más específico para este campo
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
     *
     * Según el enunciado: "solo se pueden ingresar los valores antes indicados."
     *
     * @param estado Nuevo estado.
     * @throws IllegalArgumentException si el estado no es 1, 2 o 3.
     */
    public void setEstado(int estado) {
        // Reutilizamos el método validarEstadoRevision de tu clase Validacion.
        if (!Validacion.validarEstadoRevision(estado)) {
            // Se propaga la excepción con un mensaje específico
            throw new IllegalArgumentException("Estado inválido. Debe ser 1 (sin problemas), 2 (con observaciones), o 3 (no aprueba).");
        }
        this.estado = estado;
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
            default -> "Desconocido (estado inválido)"; // Este default no debería alcanzarse con las validaciones
        };

        return "Revisión #" + identificadorRevision + "\n" +
               "ID Visita en Terreno: " + identificadorVisitaEnTerreno + "\n" +
               "Nombre Alusivo: " + nombreAlusivoRevision + "\n" +
               "Detalle a Revisar: " + detalleParaRevisar + "\n" +
               "Estado: " + estadoTexto;
    }
}