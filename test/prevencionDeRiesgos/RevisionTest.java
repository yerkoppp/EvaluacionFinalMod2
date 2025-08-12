package prevencionDeRiesgos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prevencionDeRiesgos.Revision;

class RevisionTest {

    private Revision revision;
    private final int idVisitaValido = 101;
    private final String nombreValido = "Revisión Anual de Seguridad";
    private final String detalleValido = "Verificación de extintores y señalética.";
    private final int estadoValido = 2; // Con observaciones

    @BeforeEach
    void setUp() {
        // Se crea un objeto Revisión con datos válidos para cada test
        revision = new Revision(idVisitaValido, nombreValido, detalleValido, estadoValido);
    }

    // ======================= Tests de Constructores =======================
    @Test
    void testConstructorVacio_GeneraIDUnico() {
        Revision rev = new Revision();
        assertNotNull(rev, "El constructor vacío no debe retornar null.");
        assertNotNull(rev.getIdentificadorRevision(), "El ID de revisión no debe ser nulo.");
        assertTrue(rev.getIdentificadorRevision().startsWith("RV"), "El ID debe empezar con 'RV'.");
    }

    @Test
    void testConstructorConParametros_InicializaCorrectamente() {
        assertNotNull(revision, "El constructor con parámetros no debe retornar null.");
        assertEquals(idVisitaValido, revision.getIdentificadorVisitaEnTerreno(), "El ID de visita no coincide.");
        assertEquals(nombreValido, revision.getNombreAlusivoRevision(), "El nombre no coincide.");
        assertEquals(detalleValido, revision.getDetalleParaRevisar(), "El detalle no coincide.");
        assertEquals(estadoValido, revision.getEstado(), "El estado no coincide.");
    }

    @Test
    void testConstructorConParametros_LanzaExcepcionConDatosInvalidos() {
        // ID de visita inválido (menor o igual a cero)
        assertThrows(IllegalArgumentException.class, () -> new Revision(0, nombreValido, detalleValido, estadoValido));
        // Nombre alusivo inválido (muy corto)
        assertThrows(IllegalArgumentException.class, () -> new Revision(idVisitaValido, "Corto", detalleValido, estadoValido));
        // Estado inválido (fuera del rango 1-3)
        assertThrows(IllegalArgumentException.class, () -> new Revision(idVisitaValido, nombreValido, detalleValido, 5));
    }

    // ======================= Tests de Getters y Setters =======================
    @Test
    void testSetIdentificadorVisitaEnTerrenoValido() {
        int nuevoId = 200;
        revision.setIdentificadorVisitaEnTerreno(nuevoId);
        assertEquals(nuevoId, revision.getIdentificadorVisitaEnTerreno(), "El setter debe asignar un ID de visita válido.");
    }
    
    @Test
    void testSetIdentificadorVisitaEnTerrenoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> revision.setIdentificadorVisitaEnTerreno(0),
                     "El setter debe lanzar excepción si el ID de visita es cero.");
        assertThrows(IllegalArgumentException.class, () -> revision.setIdentificadorVisitaEnTerreno(-1),
                     "El setter debe lanzar excepción si el ID de visita es negativo.");
    }

    @Test
    void testSetNombreAlusivoValido() {
        String nuevoNombre = "Revisión de Protocolo COVID-19";
        revision.setNombreAlusivoRevision(nuevoNombre);
        assertEquals(nuevoNombre, revision.getNombreAlusivoRevision(), "El setter debe asignar un nombre válido.");
    }

    @Test
    void testSetNombreAlusivoInvalido() {
        String nombreInvalido = "Corto"; // Menos de 10 caracteres
        assertThrows(IllegalArgumentException.class, () -> revision.setNombreAlusivoRevision(nombreInvalido),
                     "El setter debe lanzar excepción si el nombre es muy corto.");
    }

    @Test
    void testSetDetalleValido() {
        String nuevoDetalle = "Revisión de cables y conexiones eléctricas.";
        revision.setDetalleParaRevisar(nuevoDetalle);
        assertEquals(nuevoDetalle, revision.getDetalleParaRevisar(), "El setter debe asignar un detalle válido.");
    }
    
    @Test
    void testSetDetalleNulo_AsignaCadenaVacia() {
        revision.setDetalleParaRevisar(null);
        assertEquals("", revision.getDetalleParaRevisar(), "El setter debe asignar una cadena vacía si el detalle es nulo.");
    }

    @Test
    void testSetDetalleVacio_AsignaCadenaVacia() {
        revision.setDetalleParaRevisar("   "); // Espacios en blanco
        assertEquals("", revision.getDetalleParaRevisar(), "El setter debe asignar una cadena vacía si el detalle es solo espacios.");
    }

    @Test
    void testSetDetalleLargo_LanzaExcepcion() {
        String detalleLargo = "Este es un detalle extremadamente largo para probar que la validación de la longitud máxima funciona correctamente. Superando los 100 caracteres para asegurar el fallo.";
        assertThrows(IllegalArgumentException.class, () -> revision.setDetalleParaRevisar(detalleLargo),
                     "El setter debe lanzar excepción si el detalle es muy largo.");
    }

    @Test
    void testSetEstadoValido() {
        int nuevoEstado = 3; // No aprueba
        revision.setEstado(nuevoEstado);
        assertEquals(nuevoEstado, revision.getEstado(), "El setter debe asignar un estado válido.");
    }

    @Test
    void testSetEstadoInvalido_LanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> revision.setEstado(0),
                     "El setter debe lanzar excepción si el estado es 0.");
        assertThrows(IllegalArgumentException.class, () -> revision.setEstado(4),
                     "El setter debe lanzar excepción si el estado es 4.");
    }

    // ======================= Tests de Métodos Especiales =======================
    @Test
    void testToString_FormatoCompleto() {
        String idGenerado = revision.getIdentificadorRevision();
        String expected = "Revisión #" + idGenerado + "\n" +
                          "ID Visita en Terreno: " + idVisitaValido + "\n" +
                          "Nombre Alusivo: " + nombreValido + "\n" +
                          "Detalle a Revisar: " + detalleValido + "\n" +
                          "Estado: Con observaciones";
        assertEquals(expected, revision.toString(), "El método toString debe retornar la cadena formateada correcta.");
    }
    
    @Test
    void testToString_SinDetalle() {
        Revision rev = new Revision(idVisitaValido, nombreValido, null, estadoValido);
        String idGenerado = rev.getIdentificadorRevision();
        String expected = "Revisión #" + idGenerado + "\n" +
                          "ID Visita en Terreno: " + idVisitaValido + "\n" +
                          "Nombre Alusivo: " + nombreValido + "\n" +
                          "Detalle a Revisar: " + "" + "\n" +
                          "Estado: Con observaciones";
        assertEquals(expected, rev.toString(), "El método toString debe manejar detalles nulos correctamente.");
    }
}