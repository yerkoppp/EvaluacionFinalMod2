package prevencionDeRiesgos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class VisitaEnTerrenoTest {

    private VisitaEnTerreno visita;
    private final String RUT_VALIDO = "12.345.678-5";
    private final String DIA_VALIDO = "10/06/2025";
    private final String HORA_VALIDA = "15:30";
    private final String LUGAR_VALIDO = "Calle Falsa 123";
    private final String COMENTARIOS_VALIDOS = "Revisión de seguridad general.";

    @BeforeEach
    void setUp() {
        visita = new VisitaEnTerreno(RUT_VALIDO, DIA_VALIDO, HORA_VALIDA, LUGAR_VALIDO, COMENTARIOS_VALIDOS);
    }

    // --- Pruebas para el Constructor por Defecto ---
    @Test
    @DisplayName("Constructor por defecto: Debería inicializar el ID de visita.")
    void constructorDefecto_inicializaId_noNulo() {
        VisitaEnTerreno visitaDefault = new VisitaEnTerreno();
        assertNotNull(visitaDefault.getIdentificadorVisitaEnTerreno());
        assertTrue(visitaDefault.getIdentificadorVisitaEnTerreno().startsWith("VT"));
    }

    // --- Pruebas para el Constructor con Parámetros ---
    @Test
    @DisplayName("Constructor con parámetros: Debería inicializar todos los campos con datos válidos.")
    void constructorParametros_datosValidos_inicializaCorrectamente() {
        assertNotNull(visita);
        assertEquals(RUT_VALIDO, visita.getRutCliente());
        // Comprobamos que se hayan asignado los objetos LocalDate y LocalTime
        assertEquals(LocalDate.of(2025, 6, 10), visita.getDia());
        assertEquals(LocalTime.of(15, 30), visita.getHora());
        assertEquals(LUGAR_VALIDO, visita.getLugar());
        assertEquals(COMENTARIOS_VALIDOS, visita.getComentarios());
    }

    @ParameterizedTest
    @CsvSource({
            "RUT_INVALIDO, 10/06/2025, 15:30, Lugar Valido, Comentarios Validos",
            "12.345.678-5, 32/06/2025, 15:30, Lugar Valido, Comentarios Validos",
            "12.345.678-9, 10/06/2025, 25:30, Lugar Valido, Comentarios Validos",
            "12.345.678-9, 10/06/2025, 15:30, Corto, Comentarios Validos", // Lugar muy corto
            "12.345.678-9, 10/06/2025, 15:30, Lugar Valido, Comentarios mas largos que los 100 caracteres permitidos para el test de longitud de comentarios en esta clase."
    })
    @DisplayName("Constructor: Debería lanzar IllegalArgumentException para datos inválidos.")
    void constructorParametros_datosInvalidos_lanzaIllegalArgumentException(String rut, String dia, String hora, String lugar, String comentarios) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new VisitaEnTerreno(rut, dia, hora, lugar, comentarios)
        );
        assertTrue(thrown.getMessage().startsWith("Error al crear VisitaEnTerreno: "));
    }

    // --- Pruebas para Getters y Setters con validaciones ---
    @Test
    @DisplayName("setRutCliente: Debería asignar un RUT válido.")
    void setRutCliente_rutValido_asignaCorrectamente() {
        String nuevoRut = "11.111.111-1";
        visita.setRutCliente(nuevoRut);
        assertEquals(nuevoRut, visita.getRutCliente());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1.111.111-1", "12345678-9", "11.111.111-K"})
    @DisplayName("setRutCliente: Debería lanzar IllegalArgumentException para un RUT inválido.")
    void setRutCliente_rutInvalido_lanzaExcepcion(String rutInvalido) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                visita.setRutCliente(rutInvalido)
        );
        assertTrue(thrown.getMessage().startsWith("RUT de cliente: "));
    }

    @Test
    @DisplayName("setDia: Debería asignar un día válido.")
    void setDia_diaValido_asignaCorrectamente() {
        String nuevoDia = "20/07/2025";
        visita.setDia(nuevoDia);
        assertEquals(LocalDate.of(2025, 7, 20), visita.getDia());
    }

    @ParameterizedTest
    @ValueSource(strings = {"32/01/2025", "20-07-2025", "01/25/1000"})
    @DisplayName("setDia: Debería lanzar IllegalArgumentException para un día inválido.")
    void setDia_diaInvalido_lanzaExcepcion(String diaInvalido) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                visita.setDia(diaInvalido)
        );
        assertTrue(thrown.getMessage().startsWith("Día de la visita: "));
    }

    @Test
    @DisplayName("setHora: Debería asignar una hora válida.")
    void setHora_horaValida_asignaCorrectamente() {
        String nuevaHora = "09:00";
        visita.setHora(nuevaHora);
        assertEquals(LocalTime.of(9, 0), visita.getHora());
    }

    @ParameterizedTest
    @ValueSource(strings = {"25:00", "09:65", "9-00"})
    @DisplayName("setHora: Debería lanzar IllegalArgumentException para una hora inválida.")
    void setHora_horaInvalida_lanzaExcepcion(String horaInvalida) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                visita.setHora(horaInvalida)
        );
        assertTrue(thrown.getMessage().startsWith("Hora de la visita: "));
    }

    @Test
    @DisplayName("setLugar: Debería asignar un lugar válido (largo 10-50).")
    void setLugar_lugarValido_asignaCorrectamente() {
        String nuevoLugar = "Plaza de Armas, Santiago";
        visita.setLugar(nuevoLugar);
        assertEquals(nuevoLugar, visita.getLugar());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "Corto", "Lugar demasiado largo para ser valido en el sistema de prevencion de riesgos."})
    @DisplayName("setLugar: Debería lanzar IllegalArgumentException para un lugar inválido.")
    void setLugar_lugarInvalido_lanzaExcepcion(String lugarInvalido) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                visita.setLugar(lugarInvalido)
        );
        assertNotNull(thrown.getMessage()); // Verificamos que se lance una excepción
    }

    @Test
    @DisplayName("setComentarios: Debería asignar comentarios válidos (máx 100).")
    void setComentarios_comentariosValidos_asignaCorrectamente() {
        String nuevosComentarios = "Todo en orden.";
        visita.setComentarios(nuevosComentarios);
        assertEquals(nuevosComentarios, visita.getComentarios());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    @DisplayName("setComentarios: Debería asignar una cadena vacía para comentarios nulos o vacíos.")
    void setComentarios_comentariosNulosVacios_asignaCadenaVacia(String comentariosNulos) {
        visita.setComentarios(comentariosNulos);
        assertEquals("", visita.getComentarios());
    }

    @Test
    @DisplayName("setComentarios: Debería lanzar IllegalArgumentException si los comentarios exceden los 100 caracteres.")
    void setComentarios_comentariosLargos_lanzaExcepcion() {
        String comentariosLargos = "Este comentario es demasiado largo para ser aceptado, debe tener mas de 100 caracteres. Este es un test para validar la longitud del campo. No hay problema si el test es largo.";
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                visita.setComentarios(comentariosLargos)
        );
        assertTrue(thrown.getMessage().startsWith("Comentarios: "));
    }

    // --- Prueba para el método toString ---
    @Test
    @DisplayName("toString: Debería retornar una cadena formateada correctamente.")
    void toString_retornaStringCorrecto() {
        // Obtenemos los valores formateados de los getters
        String diaStr = "10/06/2025";
        String horaStr = "15:30";

        String expected = "Visita en Terreno #" + visita.getIdentificadorVisitaEnTerreno() + "\n" +
                          "RUT Cliente: " + RUT_VALIDO + "\n" +
                          "Día: " + diaStr + "\n" +
                          "Hora: " + horaStr + "\n" +
                          "Lugar: " + LUGAR_VALIDO + "\n" +
                          "Comentarios: " + COMENTARIOS_VALIDOS;

        assertEquals(expected, visita.toString());
    }
}