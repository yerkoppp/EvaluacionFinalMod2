package prevencionDeRiesgos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class RevisionTest {

    private Revision revision;
    private static final int ID_VISITA_VALIDO = 12345;
    private static final String NOMBRE_VALIDO = "Revisión de Seguridad";
    private static final String DETALLE_VALIDO = "Revisar los extintores y salidas de emergencia.";
    private static final int ESTADO_VALIDO = 1;

    @BeforeEach
    void setUp() {
        // Inicializamos una revisión con datos válidos por defecto para los tests
        revision = new Revision(ID_VISITA_VALIDO, NOMBRE_VALIDO, DETALLE_VALIDO, ESTADO_VALIDO);
    }

    // --- Tests para el Constructor por Defecto ---
    @Test
    @DisplayName("Constructor por defecto: Debería crear un objeto Revision no nulo con un ID único")
    void constructorDefecto_creaObjetoNoNulo() {
        Revision revisionDefault = new Revision();
        assertNotNull(revisionDefault, "El objeto Revision creado no debería ser nulo.");
        assertTrue(revisionDefault.getIdentificadorRevision().startsWith("RV"), "El identificador debería comenzar con 'RV'.");
    }

    // --- Tests para el Constructor con Parámetros ---
    @Test
    @DisplayName("Constructor con parámetros: Debería inicializar una revisión con datos válidos")
    void constructorParametros_datosValidos_inicializaRevision() {
        int idVisita = 98765;
        String nombre = "Revisión de Protocolos";
        String detalle = "Revisar la documentación y procedimientos.";
        int estado = 2;

        Revision nuevaRevision = new Revision(idVisita, nombre, detalle, estado);

        assertNotNull(nuevaRevision);
        assertEquals(idVisita, nuevaRevision.getIdentificadorVisitaEnTerreno());
        assertEquals(nombre, nuevaRevision.getNombreAlusivoRevision());
        assertEquals(detalle, nuevaRevision.getDetalleParaRevisar());
        assertEquals(estado, nuevaRevision.getEstado());
    }

    @Test
    @DisplayName("Constructor: Debería lanzar IllegalArgumentException para nombre alusivo corto")
    void constructor_nombreCorto_lanzaException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Revision(ID_VISITA_VALIDO, "Corto", DETALLE_VALIDO, ESTADO_VALIDO)
        );
    }
    
    @Test
    @DisplayName("Constructor: Debería lanzar IllegalArgumentException para nombre alusivo largo")
    void constructor_nombreLargo_lanzaException() {
        String nombreLargo = "A".repeat(51);
        assertThrows(IllegalArgumentException.class, () ->
                new Revision(ID_VISITA_VALIDO, nombreLargo, DETALLE_VALIDO, ESTADO_VALIDO)
        );
    }

    @Test
    @DisplayName("Constructor: Debería lanzar IllegalArgumentException para detalle largo (>100)")
    void constructor_detalleLargo_lanzaException() {
        String detalleLargo = "B".repeat(101);
        assertThrows(IllegalArgumentException.class, () ->
                new Revision(ID_VISITA_VALIDO, NOMBRE_VALIDO, detalleLargo, ESTADO_VALIDO)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 4, -1})
    @DisplayName("Constructor: Debería lanzar IllegalArgumentException para estado inválido")
    void constructor_estadoInvalido_lanzaException(int estadoInvalido) {
        assertThrows(IllegalArgumentException.class, () ->
                new Revision(ID_VISITA_VALIDO, NOMBRE_VALIDO, DETALLE_VALIDO, estadoInvalido)
        );
    }

    // --- Tests para Getters y Setters ---
    @Test
    @DisplayName("setNombreAlusivoRevision: Debería establecer un nombre válido")
    void setNombreAlusivoRevision_nombreValido_estableceCorrectamente() {
        String nuevoNombre = "Nuevo Nombre de Revisión";
        revision.setNombreAlusivoRevision(nuevoNombre);
        assertEquals(nuevoNombre, revision.getNombreAlusivoRevision());
    }

    @Test
    @DisplayName("setDetalleParaRevisar: Debería establecer un detalle válido")
    void setDetalleParaRevisar_detalleValido_estableceCorrectamente() {
        String nuevoDetalle = "Revisar los documentos de seguridad.";
        revision.setDetalleParaRevisar(nuevoDetalle);
        assertEquals(nuevoDetalle, revision.getDetalleParaRevisar());
    }

    @Test
    @DisplayName("setDetalleParaRevisar: Debería permitir un detalle nulo o vacío")
    void setDetalleParaRevisar_detalleNuloOVacío_estableceCorrectamente() {
        revision.setDetalleParaRevisar(null);
        assertEquals("", revision.getDetalleParaRevisar());
        
        revision.setDetalleParaRevisar("   ");
        assertEquals("", revision.getDetalleParaRevisar());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("setEstado: Debería establecer un estado válido")
    void setEstado_estadoValido_estableceCorrectamente(int estado) {
        revision.setEstado(estado);
        assertEquals(estado, revision.getEstado());
    }
    
    @ParameterizedTest
    @ValueSource(ints = {0, 4, 99})
    @DisplayName("setEstado: Debería lanzar IllegalArgumentException para estado inválido")
    void setEstado_estadoInvalido_lanzaException(int estadoInvalido) {
        assertThrows(IllegalArgumentException.class, () ->
                revision.setEstado(estadoInvalido)
        );
    }
    
    // --- Tests para Métodos Especiales ---
    @Test
    @DisplayName("toString: Debería retornar un String formateado correctamente")
    void toString_retornaStringCorrecto() {
        String expected = "Revisión #" + revision.getIdentificadorRevision() + "\n" +
                          "ID Visita en Terreno: " + ID_VISITA_VALIDO + "\n" +
                          "Nombre Alusivo: " + NOMBRE_VALIDO + "\n" +
                          "Detalle a Revisar: " + DETALLE_VALIDO + "\n" +
                          "Estado: Sin problemas";
        assertEquals(expected, revision.toString());
    }
    
    @Test
    @DisplayName("toString: Debería retornar el estado 'Con observaciones' para estado 2")
    void toString_estado2_retornaObservaciones() {
        revision.setEstado(2);
        String expectedSubstring = "Estado: Con observaciones";
        assertTrue(revision.toString().contains(expectedSubstring));
    }

    @Test
    @DisplayName("toString: Debería retornar el estado 'No aprueba' para estado 3")
    void toString_estado3_retornaNoAprueba() {
        revision.setEstado(3);
        String expectedSubstring = "Estado: No aprueba";
        assertTrue(revision.toString().contains(expectedSubstring));
    }
}