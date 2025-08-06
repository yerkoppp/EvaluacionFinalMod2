package prevencionDeRiesgos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class AccidenteTest {

    private Accidente accidente;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @BeforeEach
    void setUp() {
        // Inicializamos un accidente con datos válidos por defecto para los tests
        accidente = new Accidente("12.345.678-9", "20/08/2023", "10:30",
                "Fábrica Central de Producción", "Falla de maquinaria", "Fractura de brazo");
    }

    // --- Tests para el Constructor por Defecto ---
    @Test
    @DisplayName("Constructor por defecto: Debería crear un objeto Accidente no nulo con un ID único")
    void constructorDefecto_creaObjetoNoNulo() {
        Accidente accidenteDefault = new Accidente();
        assertNotNull(accidenteDefault, "El objeto Accidente creado no debería ser nulo.");
        assertTrue(accidenteDefault.getIdentificadorAccidente().startsWith("AC-"), "El identificador debería comenzar con 'AC-'.");
    }

    // --- Tests para el Constructor con Parámetros ---
    @Test
    @DisplayName("Constructor con parámetros: Debería inicializar un accidente con datos válidos")
    void constructorParametros_datosValidos_inicializaAccidente() {
        String rut = "14.790.717-6";
        String dia = "15/05/2024";
        String hora = "08:15";
        String lugar = "Área de Montaje 5";
        String origen = "Caída de herramienta desde altura";
        String consecuencias = "Contusión leve";

        Accidente nuevoAccidente = new Accidente(rut, dia, hora, lugar, origen, consecuencias);

        assertNotNull(nuevoAccidente);
        assertEquals(rut, nuevoAccidente.getRutCliente());
        assertEquals(dia, nuevoAccidente.getDia());
        assertEquals(hora, nuevoAccidente.getHora());
        assertEquals(lugar, nuevoAccidente.getLugar());
        assertEquals(origen, nuevoAccidente.getOrigen());
        assertEquals(consecuencias, nuevoAccidente.getConsecuencias());
    }

    @ParameterizedTest
    @CsvSource({
            "1.234.567-K, 01/01/2020, 10:00, Lugar Valido Largo, Origen, Consecuencias", // RUT con dígito verificador incorrecto
            "12.345678-9, 01/01/2020, 10:00, Lugar Valido Largo, Origen, Consecuencias"  // RUT con formato inválido
    })
    @DisplayName("Constructor con parámetros: Debería lanzar IllegalArgumentException para RUT inválido")
    void constructorParametros_rutInvalido_lanzaIllegalArgumentException(String rut, String dia, String hora, String lugar, String origen, String consecuencias) {
        assertThrows(IllegalArgumentException.class, () ->
                new Accidente(rut, dia, hora, lugar, origen, consecuencias)
        );
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"32/12/2020", "2020-01-01", "01/01/2050"}) // Fecha inválida (día, formato, futuro)
    @DisplayName("Constructor con parámetros: Debería lanzar IllegalArgumentException para fecha inválida")
    void constructorParametros_diaInvalido_lanzaIllegalArgumentException(String dia) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new Accidente("12.345.678-9", dia, "10:00", "Lugar Valido", "Origen", "Consecuencias")
        );
        String actualMessage = thrown.getMessage();
        assertTrue(
            actualMessage.contains("El formato de la fecha no es válido.") ||
            actualMessage.contains("La fecha de nacimiento no puede ser en el futuro."),
            "El mensaje de la excepción no coincide con los errores esperados."
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"30:00", "10:65", "10-00", "10:5"})
    @DisplayName("Constructor con parámetros: Debería lanzar IllegalArgumentException para hora inválida")
    void constructorParametros_horaInvalida_lanzaIllegalArgumentException(String hora) {
        assertThrows(IllegalArgumentException.class, () ->
                new Accidente("12.345.678-9", "01/01/2020", hora, "Lugar Valido", "Origen", "Consecuencias")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"Corto", "NombreDemasiadoLargoParaSerValidoPorLaValidacion"
    		+ "DelSistemaQueDebeTenerUnMaximoDeCincuentaCaracteresExactamente"})
    @DisplayName("Constructor con parámetros: Debería lanzar IllegalArgumentException para lugar con longitud inválida")
    void constructorParametros_lugarLongitudInvalida_lanzaIllegalArgumentException(String lugar) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new Accidente("12.345.678-9", "01/01/2020", "10:00", lugar, "Origen", "Consecuencias")
        );
        assertTrue(thrown.getMessage().contains("El largo del texto no es permitido."));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"NombreDemasiadoLargoParaSerValidoPorLaValidacion"
    		+ "DelSistemaQueDebeTenerUnMaximoDeCincuentaCaracteresExactamente"})
    @DisplayName("Constructor con parámetros: Debería lanzar IllegalArgumentException para origen con longitud > 100")
    void constructorParametros_origenLongitudInvalida_lanzaIllegalArgumentException(String origen) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new Accidente("12.345.678-9", "01/01/2020", "10:00", "Lugar Valido Largo", origen, "Consecuencias")
        );
        assertTrue(thrown.getMessage().contains("El largo del texto no es permitido."));
    }

    @ParameterizedTest
    @ValueSource(strings = {"NombreDemasiadoLargoParaSerValidoPorLaValidacion"
    		+ "DelSistemaQueDebeTenerUnMaximoDeCincuentaCaracteresExactamente"}) 
    @DisplayName("Constructor con parámetros: Debería lanzar IllegalArgumentException para consecuencias con longitud > 100")
    void constructorParametros_consecuenciasLongitudInvalida_lanzaIllegalArgumentException(String consecuencias) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new Accidente("12.345.678-9", "01/01/2020", "10:00", "Lugar Valido Largo", "Origen", consecuencias)
        );
        assertTrue(thrown.getMessage().contains("El largo del texto no es permitido."));
    }

    // --- Tests para Getters y Setters ---
    @Test
    @DisplayName("setRutCliente: Debería establecer un RUT válido")
    void setRutCliente_rutValido_estableceCorrectamente() {
        String nuevoRut = "14.790.717-6";
        accidente.setRutCliente(nuevoRut);
        assertEquals(nuevoRut, accidente.getRutCliente());
    }

    @ParameterizedTest
    @ValueSource(strings = {"25/12/2024", "01/01/2020"})
    @DisplayName("setDia: Debería establecer una fecha válida")
    void setDia_diaValido_estableceCorrectamente(String nuevaFecha) {
        accidente.setDia(nuevaFecha);
        assertEquals(nuevaFecha, accidente.getDia());
    }

    @Test
    @DisplayName("setHora: Debería establecer una hora válida")
    void setHora_horaValida_estableceCorrectamente() {
        accidente.setHora("12:30");
        assertEquals("12:30", accidente.getHora());
    }

    @Test
    @DisplayName("setLugar: Debería establecer un lugar válido")
    void setLugar_lugarValido_estableceCorrectamente() {
        String nuevoLugar = "Nuevo Lugar de Trabajo";
        accidente.setLugar(nuevoLugar);
        assertEquals(nuevoLugar, accidente.getLugar());
    }

    @Test
    @DisplayName("setOrigen: Debería establecer un origen válido")
    void setOrigen_origenValido_estableceCorrectamente() {
        String nuevoOrigen = "Causa del accidente";
        accidente.setOrigen(nuevoOrigen);
        assertEquals(nuevoOrigen, accidente.getOrigen());
    }
    
    @Test
    @DisplayName("setConsecuencias: Debería establecer consecuencias válidas")
    void setConsecuencias_consecuenciasValidas_estableceCorrectamente() {
        String nuevasConsecuencias = "Lesiones menores";
        accidente.setConsecuencias(nuevasConsecuencias);
        assertEquals(nuevasConsecuencias, accidente.getConsecuencias());
    }

    // --- Tests para Métodos Especiales ---
    @Test
    @DisplayName("toString: Debería retornar un String formateado con los datos del accidente")
    void toString_retornaStringCorrecto() {
        String expected = String.format("Accidente [ ID: %s, RUT Cliente: %s, Día: %s, Hora: %s, "
                + "Lugar: %s, Origen: %s, Consecuencias: %s ]",
                accidente.getIdentificadorAccidente(), accidente.getRutCliente(),
                accidente.getDia(), accidente.getHora(), accidente.getLugar(),
                accidente.getOrigen(), accidente.getConsecuencias());

        assertEquals(expected, accidente.toString());
    }
}