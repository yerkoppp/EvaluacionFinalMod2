package prevencionDeRiesgos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
