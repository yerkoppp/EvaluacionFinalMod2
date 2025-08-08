package prevencionDeRiesgos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CapacitacionTest {

    private Capacitacion capacitacion;

    @BeforeEach
    void setUp() {
        // Inicializamos una capacitación con datos válidos por defecto para los tests
        capacitacion = new Capacitacion("12.345.678-5", "Lunes", "10:00",
                "Calle Falsa 123", "60 minutos", 25);
    }

    // --- Tests para el Constructor por Defecto ---
    @Test
    @DisplayName("Constructor por defecto: Debería crear un objeto "
    		+ "Capacitacion no nulo con un ID único")
    void constructorDefecto_creaObjetoNoNulo() {
        Capacitacion capacitacionDefault = new Capacitacion();
        assertNotNull(capacitacionDefault, 
        		"El objeto Capacitacion creado no debería ser nulo.");
        assertTrue(capacitacionDefault.getIdentificador().startsWith("CP"),
        		"El identificador debería comenzar con 'CP'.");
    }

    // --- Tests para el Constructor con Parámetros ---
    @Test
    @DisplayName("Constructor con parámetros: "
    		+ "Debería inicializar una capacitación con datos válidos")
    void constructorParametros_datosValidos_inicializaCapacitacion() {
        String rut = "14.790.717-6";
        String dia = "Martes";
        String hora = "15:30";
        String lugar = "Avenida Siempre Viva 742";
        String duracion = "90 minutos";
        int asistentes = 50;

        Capacitacion nuevaCapacitacion = new Capacitacion(
        		rut, dia, hora, lugar, duracion, asistentes);

        assertNotNull(nuevaCapacitacion);
        assertEquals(rut, nuevaCapacitacion.getRutCliente());
        assertEquals(dia.toUpperCase(), nuevaCapacitacion.getDia()); // Se almacena en mayúsculas
        assertEquals(hora, nuevaCapacitacion.getHora()); // El getter formatea la hora
        assertEquals(lugar, nuevaCapacitacion.getLugar());
        assertEquals(duracion, nuevaCapacitacion.getDuracion());
        assertEquals(asistentes, nuevaCapacitacion.getCantidadAsistentes());
    }

    @ParameterizedTest
    @CsvSource({
            "1.234.567-K, Lunes, 10:00, Lugar Valido Largo, 60 minutos, 50", // RUN con dígito verificador incorrecto
            "12.345678-9, Lunes, 10:00, Lugar Valido Largo, 60 minutos, 50"  // RUN con formato inválido
    })
    @DisplayName("Constructor con parámetros: Debería lanzar "
    		+ "IllegalArgumentException para RUT inválido")
    void constructorParametros_rutInvalido_lanzaIllegalArgumentException(
    		String rut, String dia, String hora, String lugar, String duracion, int asistentes) {
        assertThrows(IllegalArgumentException.class, () ->
                new Capacitacion(rut, dia, hora, lugar, duracion, asistentes)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"InvalidDay", "Dia Incorrecto"})
    @DisplayName("Constructor con parámetros: Debería lanzar "
    		+ "IllegalArgumentException para día inválido")
    void constructorParametros_diaInvalido_lanzaIllegalArgumentException(String dia) {
        assertThrows(IllegalArgumentException.class, () ->
                new Capacitacion("12.345.678-9", dia, "10:00", "Lugar Valido", "60 minutos", 50)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"30:00", "10:65", "10-00", "10:5"})
    @DisplayName("Constructor con parámetros: Debería lanzar "
    		+ "IllegalArgumentException para hora inválida")
    void constructorParametros_horaInvalida_lanzaIllegalArgumentException(String hora) {
        assertThrows(IllegalArgumentException.class, () ->
                new Capacitacion("12.345.678-9", "Lunes", hora, "Lugar Valido", "60 minutos", 50)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"Corto", "NombreDemasiadoLargoParaSerValidoPor"
    		+ "LaValidacionDelSistemaQueDebeTenerUnMaximoDeCincuentaCaracteresExactamente"})
    @DisplayName("Constructor con parámetros: Debería lanzar "
    		+ "IllegalArgumentException para lugar con longitud inválida")
    void constructorParametros_lugarLongitudInvalida_lanzaIllegalArgumentException(String lugar) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new Capacitacion("12.345.678-5", "Lunes", "10:00", lugar, "60 minutos", 50)
        );
        assertTrue(thrown.getMessage().contains("El largo del texto no es permitido."));
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 1001, 2000})
    @DisplayName("Constructor con parámetros: Debería lanzar "
    		+ "IllegalArgumentException para cantidad de asistentes mayor o igual a 1000")
    void constructorParametros_asistentesMayoresOIgual1000_lanzaIllegalArgumentException(int asistentes) {
        assertThrows(IllegalArgumentException.class, () ->
                new Capacitacion("12.345.678-5", "Lunes", "10:00",
                		"Lugar Valido", "60 minutos", asistentes)
        );
    }

    // --- Tests para Getters ---
    @Test
    @DisplayName("getIdentificador: Debería retornar un ID válido")
    void getIdentificador_retornaIdCorrecto() {
        assertNotNull(capacitacion.getIdentificador());
        assertTrue(capacitacion.getIdentificador().startsWith("CP"));
    }

    @Test
    @DisplayName("getRutCliente: Debería retornar el RUT correcto")
    void getRutCliente_retornaRutCorrecto() {
        assertEquals("12.345.678-5", capacitacion.getRutCliente());
    }

    // --- Tests para Setters ---
    @Test
    @DisplayName("setRutCliente: Debería establecer un RUT válido")
    void setRutCliente_rutValido_estableceCorrectamente() {
        String nuevoRut = "14.790.717-6";
        capacitacion.setRutCliente(nuevoRut);
        assertEquals(nuevoRut, capacitacion.getRutCliente());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "123", "12.345.678-A"})
    @DisplayName("setRutCliente: Debería lanzar IllegalArgumentException para RUT inválido")
    void setRutCliente_rutInvalido_lanzaIllegalArgumentException(String rutInvalido) {
        assertThrows(IllegalArgumentException.class, () ->
                capacitacion.setRutCliente(rutInvalido)
        );
    }

    @Test
    @DisplayName("setHora: Debería establecer una hora válida")
    void setHora_horaValida_estableceCorrectamente() {
        capacitacion.setHora("12:30");
        assertEquals("12:30", capacitacion.getHora());
    }

    @ParameterizedTest
    @ValueSource(strings = {"25:00", "10:99", "5-30"})
    @DisplayName("setHora: Debería lanzar IllegalArgumentException para hora inválida")
    void setHora_horaInvalida_lanzaIllegalArgumentException(String horaInvalida) {
        assertThrows(IllegalArgumentException.class, () ->
                capacitacion.setHora(horaInvalida)
        );
    }

    // --- Tests para Métodos Especiales ---
    @Test
    @DisplayName("toString: Debería retornar un String formateado con los datos de la capacitación")
    void toString_retornaStringCorrecto() {
        String expected = String.format("Capacitación [ ID: %s, RUT Cliente: %s, Día: %s, Hora: %s, "
                + "Lugar: %s, Duración: %s, Cantidad Asistentes: %d ]",
                capacitacion.getIdentificador(), capacitacion.getRutCliente(), capacitacion.getDia(),
                capacitacion.getHora(), capacitacion.getLugar(), capacitacion.getDuracion(),
                capacitacion.getCantidadAsistentes());

        assertEquals(expected, capacitacion.toString());
    }

    @Test
    @DisplayName("mostrarDetalle: Debería retornar un String con los detalles de la capacitación")
    void mostrarDetalle_retornaDetallesCorrectos() {
        String expected = String.format("La capacitación será en %s a las %s del día %s,"
                + " y durará %s minutos", capacitacion.getLugar(), capacitacion.getHora(),
                capacitacion.getDia(), capacitacion.getDuracion());

        assertEquals(expected, capacitacion.mostrarDetalle());
    }
}