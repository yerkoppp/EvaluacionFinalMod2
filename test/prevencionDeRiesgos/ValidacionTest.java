//package prevencionDeRiesgos;

//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;

//Comentado para pruebas
/*
class ValidacionTest {

    @Test
    public void testValidarFechaFormato() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Validacion.validarFecha("23-05-25");
        });
        assertEquals("⚠️ El formato de la fecha no es válido. Utilice DD/MM/AAAA.", exception.getMessage());
    }

    @Test
    public void testValidarFechaVacio() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Validacion.validarFecha("");
        });
        assertEquals("⚠️ La fecha no puede ser nula o vacía. Utilice DD/MM/AAAA.", exception.getMessage());
    }

    @Test
    public void testValidarFechaNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Validacion.validarFecha(null);
        });
        assertEquals("⚠️ La fecha no puede ser nula o vacía. Utilice DD/MM/AAAA.", exception.getMessage());
    }

    @Test
    public void testValidarFechaCorrecta() {
        assertDoesNotThrow(() -> {
            Validacion.validarFecha("23/05/2025");
        });
    }

    @Test
    public void testValidarFechaDiaMesInvalido() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Validacion.validarFecha("32/13/2025");
        });
        assertEquals("⚠️ El formato de la fecha no es válido. Utilice DD/MM/AAAA.", exception.getMessage());
    }
    
    @Test
    void testValidarHoraVacio() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Validacion.validarHora("");
        });
        assertEquals("⚠️ La hora no puede ser nula o vacía. Utilice HH:MM.", exception.getMessage());
    }

    @Test
    void testValidarHoraNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Validacion.validarHora(null);
        });
        assertEquals("⚠️ La hora no puede ser nula o vacía. Utilice HH:MM.", exception.getMessage());
    }

    @Test
    void testValidarHoraFormatoInvalido() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Validacion.validarHora("8:05"); // Formato incorrecto
        });
        assertEquals("⚠️ El formato de la hora no es válido. Utilice HH:MM.", exception.getMessage());
    }

    @Test
    void testValidarHoraFormatoIncorrectoNumerico() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Validacion.validarHora("25:99"); // Horas y minutos fuera de rango
        });
        assertEquals("⚠️ El formato de la hora no es válido. Utilice HH:MM.", exception.getMessage());
    }

    @Test
    void testValidarHoraCorrecta() {
    	 assertDoesNotThrow(() -> {
             Validacion.validarHora("08:30");
         });
    }

}
*/
//fin comentario

package prevencionDeRiesgos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class ValidacionTest {

    // --- Pruebas para validarFecha ---
    @Test
    @DisplayName("validarFecha: Debería devolver LocalDate para un formato de fecha válido")
    void validarFecha_formatoValido_retornaLocalDate() {
        String fechaString = "25/12/2023";
        LocalDate fechaEsperada = LocalDate.of(2023, 12, 25);
        assertEquals(fechaEsperada, Validacion.validarFecha(fechaString));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    @DisplayName("validarFecha: Debería lanzar IllegalArgumentException para cadena de fecha nula o vacía")
    void validarFecha_nuloOVacio_lanzaIllegalArgumentException(String fechaString) {
        IllegalArgumentException excepcionLanzada = assertThrows(IllegalArgumentException.class, () ->
                Validacion.validarFecha(fechaString)
        );
        assertTrue(excepcionLanzada.getMessage().contains("La fecha no puede ser nula o vacía."));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2023-12-25", "25/12/23", "25-12-2023", "25/12/202", "32/12/2023", "25/13/2023"})
    @DisplayName("validarFecha: Debería lanzar IllegalArgumentException para un formato de fecha inválido")
    void validarFecha_formatoInvalido_lanzaIllegalArgumentException(String fechaString) {
        IllegalArgumentException excepcionLanzada = assertThrows(IllegalArgumentException.class, () ->
                Validacion.validarFecha(fechaString)
        );
        assertTrue(excepcionLanzada.getMessage().contains("El formato de la fecha no es válido."));
        assertTrue(excepcionLanzada.getCause() instanceof DateTimeParseException);
    }

    // --- Pruebas para transformarFechaAstring ---
    @Test
    @DisplayName("transformarFechaAstring: Debería devolver la cadena de fecha formateada para LocalDate válido")
    void transformarFechaAstring_localDateValido_retornaStringFormateado() {
        LocalDate fecha = LocalDate.of(2023, 1, 15);
        assertEquals("15/01/2023", Validacion.transformarFechaAstring(fecha));
    }

    @Test
    @DisplayName("transformarFechaAstring: Debería devolver null para LocalDate nulo")
    void transformarFechaAstring_localDateNulo_retornaNull() {
        assertNull(Validacion.transformarFechaAstring(null));
    }

    // --- Pruebas para validarHora ---
    @Test
    @DisplayName("validarHora: Debería devolver LocalTime para un formato de hora válido")
    void validarHora_formatoValido_retornaLocalTime() {
        String horaString = "14:30";
        LocalTime horaEsperada = LocalTime.of(14, 30);
        assertEquals(horaEsperada, Validacion.validarHora(horaString));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    @DisplayName("validarHora: Debería lanzar IllegalArgumentException para cadena de hora nula o vacía")
    void validarHora_nuloOVacio_lanzaIllegalArgumentException(String horaString) {
        IllegalArgumentException excepcionLanzada = assertThrows(IllegalArgumentException.class, () ->
                Validacion.validarHora(horaString)
        );
        assertTrue(excepcionLanzada.getMessage().contains("La hora no puede ser nula o vacía."));
    }

    @ParameterizedTest
    @ValueSource(strings = {"14:3", "1430", "25:00", "12:65", "12-30"})
    @DisplayName("validarHora: Debería lanzar IllegalArgumentException para un formato de hora inválido")
    void validarHora_formatoInvalido_lanzaIllegalArgumentException(String horaString) {
        IllegalArgumentException excepcionLanzada = assertThrows(IllegalArgumentException.class, () ->
                Validacion.validarHora(horaString)
        );
        assertTrue(excepcionLanzada.getMessage().contains("El formato de la hora no es válido."));
        assertTrue(excepcionLanzada.getCause() instanceof DateTimeParseException);
    }

    // --- Pruebas para transformarHoraAstring ---
    @Test
    @DisplayName("transformarHoraAstring: Debería devolver la cadena de hora formateada para LocalTime válido")
    void transformarHoraAstring_localTimeValido_retornaStringFormateado() {
        LocalTime hora = LocalTime.of(9, 5);
        assertEquals("09:05", Validacion.transformarHoraAstring(hora));
    }

    @Test
    @DisplayName("transformarHoraAstring: Debería devolver null para LocalTime nulo")
    void transformarHoraAstring_localTimeNulo_retornaNull() {
        assertNull(Validacion.transformarHoraAstring(null));
    }

    // --- Pruebas para validarDia ---
    @ParameterizedTest
    @ValueSource(strings = {"lunes", "MARTES", "Miércoles", "jueves", "VIERNES", "Sábado", "domingo"})
    @DisplayName("validarDia: Debería devolver el día en mayúsculas para un día válido (insensible a mayúsculas/minúsculas)")
    void validarDia_diaValido_retornaMayusculas(String dia) {
        assertEquals(dia.toUpperCase(), Validacion.validarDia(dia));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "dia invalido", "Lun", "Mart"})
    @NullAndEmptySource
    @DisplayName("validarDia: Debería lanzar IllegalArgumentException para un día inválido o nulo/vacío")
    void validarDia_diaInvalido_lanzaIllegalArgumentException(String dia) {
        IllegalArgumentException excepcionLanzada = assertThrows(IllegalArgumentException.class, () ->
                Validacion.validarDia(dia)
        );
        assertTrue(excepcionLanzada.getMessage().contains("⚠️ El día no es válido. Inténtelo de nuevo"));
    }

    // --- Pruebas para validarLargoString (min, max) ---
    @Test
    @DisplayName("validarLargoString(min, max): Debería devolver la cadena si la longitud está dentro del rango")
    void validarLargoStringMinMax_dentroDeRango_retornaString() {
        String texto = "Hola";
        assertEquals(texto, Validacion.validarLargoString(texto, 3, 7));
    }

    @Test
    @DisplayName("validarLargoString(min, max): Debería devolver la cadena si la longitud está en el límite mínimo")
    void validarLargoStringMinMax_limiteMinimo_retornaString() {
        String texto = "abc";
        assertEquals(texto, Validacion.validarLargoString(texto, 3, 5));
    }

    @Test
    @DisplayName("validarLargoString(min, max): Debería devolver la cadena si la longitud está en el límite máximo")
    void validarLargoStringMinMax_limiteMaximo_retornaString() {
        String texto = "abcde";
        assertEquals(texto, Validacion.validarLargoString(texto, 3, 5));
    }

    @ParameterizedTest
    @CsvSource({
            "cat, 5, 10",
            "esta es una cadena muy larga, 5, 10"
    })
    @DisplayName("validarLargoString(min, max): Debería lanzar IllegalArgumentException si la longitud está fuera de rango")
    void validarLargoStringMinMax_fueraDeRango_lanzaIllegalArgumentException(String texto, int min, int max) {
        IllegalArgumentException excepcionLanzada = assertThrows(IllegalArgumentException.class, () ->
                Validacion.validarLargoString(texto, min, max)
        );
        assertTrue(excepcionLanzada.getMessage().contains("El largo del texto no es permitido."));
    }

    // --- Pruebas para validarLargoString (max) ---
    @Test
    @DisplayName("validarLargoString(max): Debería devolver la cadena si la longitud está dentro del máximo")
    void validarLargoStringMax_dentroDeMax_retornaString() {
        String texto = "Hola";
        assertEquals(texto, Validacion.validarLargoString(texto, 10));
    }

    @Test
    @DisplayName("validarLargoString(max): Debería devolver la cadena si la longitud está en el límite máximo")
    void validarLargoStringMax_limiteMaximo_retornaString() {
        String texto = "abcdefghij"; // 10 caracteres
        assertEquals(texto, Validacion.validarLargoString(texto, 10));
    }

    @Test
    @DisplayName("validarLargoString(max): Debería lanzar IllegalArgumentException si la longitud excede el máximo")
    void validarLargoStringMax_excedeMax_lanzaIllegalArgumentException() {
        String texto = "Esto es demasiado largo";
        IllegalArgumentException excepcionLanzada = assertThrows(IllegalArgumentException.class, () ->
                Validacion.validarLargoString(texto, 5)
        );
        assertTrue(excepcionLanzada.getMessage().contains("El largo del texto no es permitido."));
    }

    // --- Pruebas para validarNumeroMaximo ---
    @Test
    @DisplayName("validarNumeroMaximo: Debería devolver el número si es menor que el máximo")
    void validarNumeroMaximo_menorQueMax_retornaNumero() {
        assertEquals(5, Validacion.validarNumeroMaximo(5, 10));
    }

    @Test
    @DisplayName("validarNumeroMaximo: Debería lanzar IllegalArgumentException si el número es igual al máximo")
    void validarNumeroMaximo_igualAMax_lanzaIllegalArgumentException() {
        IllegalArgumentException excepcionLanzada = assertThrows(IllegalArgumentException.class, () ->
                Validacion.validarNumeroMaximo(10, 10)
        );
        assertTrue(excepcionLanzada.getMessage().contains("El número ingresado supera el máximo permitido."));
    }

    @Test
    @DisplayName("validarNumeroMaximo: Debería lanzar IllegalArgumentException si el número es mayor que el máximo")
    void validarNumeroMaximo_mayorQueMax_lanzaIllegalArgumentException() {
        IllegalArgumentException excepcionLanzada = assertThrows(IllegalArgumentException.class, () ->
                Validacion.validarNumeroMaximo(15, 10)
        );
        assertTrue(excepcionLanzada.getMessage().contains("El número ingresado supera el máximo permitido."));
    }

    /* Cambio en pruebas para Calcular Edad por cambio en tipo de parámetro, pasa de tipo String a LocalDate
    // --- Pruebas para calcularEdad ---
    @Test
    @DisplayName("calcularEdad: Debería devolver la edad correcta para una fecha de nacimiento pasada")
    void calcularEdad_fechaPasada_retornaEdadCorrecta() {
        // Simular una fecha de nacimiento que haga a la persona 30 años hoy (asumiendo LocalDate.now() es actual)
        String dobString = LocalDate.now().minusYears(30).format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        assertEquals(30, Validacion.calcularEdad(dobString));
    }

    @Test
    @DisplayName("calcularEdad: Debería devolver la edad correcta para una fecha de nacimiento pasada (caso borde - cumpleaños exacto)")
    void calcularEdad_cumpleaniosExacto_retornaEdadCorrecta() {
        // Simular una fecha de nacimiento que haga a la persona 25 años exactos hoy
        String dobString = LocalDate.now().minusYears(25).format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        assertEquals(25, Validacion.calcularEdad(dobString));
    }

    @Test
    @DisplayName("calcularEdad: Debería devolver la edad correcta para una fecha de nacimiento pasada (caso borde - casi cumpleaños)")
    void calcularEdad_casiCumpleanios_retornaEdadCorrecta() {
        // Simular una fecha de nacimiento que haga a la persona 29 años, casi 30
        String dobString = LocalDate.now().minusYears(30).plusDays(1).format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        assertEquals(29, Validacion.calcularEdad(dobString));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "fecha invalida", "12-25-1990"})
    @DisplayName("calcularEdad: Debería lanzar IllegalArgumentException para formato de fecha inválido o nulo/vacío")
    void calcularEdad_formatoFechaInvalido_lanzaIllegalArgumentException(String dobString) {
        IllegalArgumentException excepcionLanzada = assertThrows(IllegalArgumentException.class, () ->
                Validacion.calcularEdad(dobString)
        );
        assertTrue(excepcionLanzada.getMessage().contains("El formato de la fecha no es válido") ||
                   excepcionLanzada.getMessage().contains("La fecha no puede ser nula o vacía"));
    }

    @Test
    @DisplayName("calcularEdad: Debería lanzar IllegalArgumentException si la fecha de nacimiento es en el futuro")
    void calcularEdad_fechaFutura_lanzaIllegalArgumentException() {
        // Simular una fecha de nacimiento en el futuro
        String futureDobString = LocalDate.now().plusDays(1).format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        IllegalArgumentException excepcionLanzada = assertThrows(IllegalArgumentException.class, () ->
                Validacion.calcularEdad(futureDobString)
        );
        assertTrue(excepcionLanzada.getMessage().contains("La fecha de nacimiento no puede ser en el futuro."));
    }
    */
    
 // --- Pruebas para calcularEdad ---
    @Test
    @DisplayName("calcularEdad: Debería devolver la edad correcta para una fecha de nacimiento pasada")
    void calcularEdad_fechaPasada_retornaEdadCorrecta() {
        LocalDate fechaNacimiento = LocalDate.now().minusYears(30);
        assertEquals(30, Validacion.calcularEdad(fechaNacimiento));
    }

    @Test
    @DisplayName("calcularEdad: Debería devolver la edad correcta para una fecha de nacimiento pasada (caso borde - cumpleaños exacto)")
    void calcularEdad_cumpleaniosExacto_retornaEdadCorrecta() {
        LocalDate fechaNacimiento = LocalDate.now().minusYears(25);
        assertEquals(25, Validacion.calcularEdad(fechaNacimiento));
    }

    @Test
    @DisplayName("calcularEdad: Debería devolver la edad correcta para una fecha de nacimiento pasada (caso borde - casi cumpleaños)")
    void calcularEdad_casiCumpleanios_retornaEdadCorrecta() {
        LocalDate fechaNacimiento = LocalDate.now().minusYears(30).plusDays(1);
        assertEquals(29, Validacion.calcularEdad(fechaNacimiento));
    }

    @Test
    @DisplayName("calcularEdad: Debería lanzar IllegalArgumentException si la fecha de nacimiento es en el futuro")
    void calcularEdad_fechaFutura_lanzaIllegalArgumentException() {
        LocalDate fechaFutura = LocalDate.now().plusDays(1);
        IllegalArgumentException excepcionLanzada = assertThrows(IllegalArgumentException.class, () ->
                Validacion.calcularEdad(fechaFutura)
        );
        assertTrue(excepcionLanzada.getMessage().contains("La fecha de nacimiento no puede ser en el futuro."));
    }

    // --- Pruebas para validarRut ---
    @Test
    @DisplayName("validarRut: Debería devolver RUT válido para formato correcto y dígito '9'")
    void validarRut_rutValidoConDigito9_retornaRut() {
        // RUT válido de ejemplo: 12.345.693-9
        assertEquals("12.345.693-9", Validacion.validarRut("12.345.693-9"));
    }

    @Test
    @DisplayName("validarRut: Debería devolver RUT válido para formato correcto y dígito 'K'")
    void validarRut_rutValidoConDigitoK_retornaRut() {
        // RUT válido de ejemplo: 17.213.825-k
        assertEquals("17.213.825-k", Validacion.validarRut("17.213.825-k"));
    }

    @Test
    @DisplayName("validarRut: Debería devolver RUT válido para formato correcto y dígito 'k' minúscula")
    void validarRut_rutValidoConkMinuscula_retornaRut() {
        // RUT válido de ejemplo: 17.213.825-k
        assertEquals("17.213.825-k", Validacion.validarRut("17.213.825-k"));
    }

    @Test
    @DisplayName("validarRut: Debería devolver RUT válido para formato correcto y dígito '0'")
    void validarRut_rutValidoConDigito0_retornaRut() {
        // RUT válido de ejemplo: 15.678.907-0
        assertEquals("15.678.907-0", Validacion.validarRut("15.678.907-0"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    @DisplayName("validarRut: Debería lanzar IllegalArgumentException para cadena de RUT nula o vacía")
    void validarRut_nuloOVacio_lanzaIllegalArgumentException(String rut) {
        IllegalArgumentException excepcionLanzada = assertThrows(IllegalArgumentException.class, () ->
                Validacion.validarRut(rut)
        );
        assertTrue(excepcionLanzada.getMessage().contains("El RUT es obligatorio."));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "12345678-9",       // Faltan puntos
            "12.345.6789",      // Falta guion
            "12.345.67-89",     // Formato incorrecto
            "12.345.678-L",     // DV inválido
            "1.23.456.789-9",   // Demasiados puntos/dígitos
            "12.345.678-",      // Falta DV
            "a2.345.678-9"      // Carácter no dígito en la parte numérica
    })
    @DisplayName("validarRut: Debería lanzar IllegalArgumentException para formato de RUT inválido")
    void validarRut_formatoInvalido_lanzaIllegalArgumentException(String rut) {
        IllegalArgumentException excepcionLanzada = assertThrows(IllegalArgumentException.class, () ->
                Validacion.validarRut(rut)
        );
        assertTrue(excepcionLanzada.getMessage().contains("El formato de RUT debe ser 99.999.999-9."));
    }

    @Test
    @DisplayName("validarRut: Debería lanzar IllegalArgumentException para dígito verificador de RUT inválido (Módulo 11)")
    void validarRut_checksumInvalido_lanzaIllegalArgumentException() {
        // Este RUT 12.345.678-3 es intencionalmente incorrecto (el DV correcto para 12345678 es 9)
        IllegalArgumentException excepcionLanzada = assertThrows(IllegalArgumentException.class, () ->
                Validacion.validarRut("12.345.678-3")
        );
        assertTrue(excepcionLanzada.getMessage().contains("RUT ingresado es invalido."));
    }

    @Test
    @DisplayName("validarRut: Debería manejar RUT con 'K' correctamente (Módulo 11)")
    void validarRut_rutConK_manejaKCorrectamente() {
        // RUT 11.111.112-k es un RUT conocido válido
        assertEquals("11.111.112-k", Validacion.validarRut("11.111.112-k"));
    }

    @Test
    @DisplayName("validarRut: Debería manejar RUT con '0' correctamente (Módulo 11)")
    void validarRut_rutCon0_maneja0Correctamente() {
        // RUT 12.345.692-0 es un RUT conocido válido
        assertEquals("12.345.692-0", Validacion.validarRut("12.345.692-0"));
    }
}