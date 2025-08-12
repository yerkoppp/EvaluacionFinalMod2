package prevencionDeRiesgos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prevencionDeRiesgos.Profesional;

class ProfesionalTest {

    private Profesional profesional;
    private final String nombreValido = "Juan Carlos Bodoque";
    private final String fechaNacimientoValida = "21/04/1980";
    private final String runValido = "4.252.845-5";
    private final String tituloValido = "Ingeniero en Prevención de Riesgos";
    private final String fechaIngresoValida = "15/03/2021";

    @BeforeEach
    void setUp() {
        // Inicializar un objeto Profesional para cada test con datos válidos
        profesional = new Profesional(nombreValido, fechaNacimientoValida, runValido, tituloValido, fechaIngresoValida);
    }

    @Test
    void testConstructorVacio_CreaObjetoProfesional() {
        Profesional p = new Profesional();
        assertNotNull(p, "El constructor vacío no debe retornar null.");
    }

    @Test
    void testConstructorConParametros_InicializaCorrectamente() {
        assertNotNull(profesional, "El constructor con parámetros no debe retornar null.");
        assertEquals(nombreValido, profesional.getNombre(), "El nombre no coincide.");
        assertEquals(fechaNacimientoValida, profesional.getFechaNacimiento(), "La fecha de nacimiento no coincide.");
        assertEquals(runValido, profesional.getRun(), "El RUN no coincide.");
        assertEquals(tituloValido, profesional.getTitulo(), "El título no coincide.");
        assertEquals(fechaIngresoValida, profesional.getFechaIngreso(), "La fecha de ingreso no coincide.");
    }

    // --- Tests para el atributo 'titulo' y sus validaciones ---
    @Test
    void testSetTituloValido_AsignaCorrectamente() {
        String nuevoTitulo = "Especialista en Seguridad Ocupacional";
        profesional.setTitulo(nuevoTitulo);
        assertEquals(nuevoTitulo, profesional.getTitulo(), "El setter de título debe asignar un valor válido.");
    }

    @Test
    void testSetTituloConMenosDe10Caracteres_LanzaExcepcion() {
        String tituloInvalido = "Corto";
        assertThrows(IllegalArgumentException.class, () -> profesional.setTitulo(tituloInvalido),
                     "El setter de título debe lanzar excepción si es muy corto.");
    }

    @Test
    void testSetTituloConMasDe50Caracteres_LanzaExcepcion() {
        String tituloInvalido = "Este es un título de profesional demasiado largo para la validación de la longitud máxima.";
        assertThrows(IllegalArgumentException.class, () -> profesional.setTitulo(tituloInvalido),
                     "El setter de título debe lanzar excepción si es muy largo.");
    }

    @Test
    void testSetTituloNulo_LanzaExcepcion() {
        String tituloInvalido = null;
        assertThrows(IllegalArgumentException.class, () -> profesional.setTitulo(tituloInvalido),
                     "El setter de título debe lanzar excepción si es null.");
    }

    // --- Tests para el atributo 'fechaIngreso' y sus validaciones ---
    @Test
    void testSetFechaIngresoValida_AsignaCorrectamente() {
        String nuevaFecha = "20/10/2022";
        profesional.setFechaIngreso(nuevaFecha);
        assertEquals(nuevaFecha, profesional.getFechaIngreso(), "El setter de fecha de ingreso debe asignar un valor válido.");
    }

    @Test
    void testSetFechaIngresoFormatoInvalido_LanzaExcepcion() {
        String fechaInvalida = "2022-10-20";
        assertThrows(IllegalArgumentException.class, () -> profesional.setFechaIngreso(fechaInvalida),
                     "El setter debe lanzar excepción para formatos de fecha inválidos.");
    }
    
    @Test
    void testSetFechaIngresoNula_AsignaNulo() {
        String fechaInvalida = null;
        profesional.setFechaIngreso(fechaInvalida);
        assertNull(profesional.getFechaIngreso(), "El setter de fecha de ingreso debe asignar null si la entrada es nula.");
    }
    
    @Test
    void testSetFechaIngresoVacia_AsignaNulo() {
        String fechaInvalida = "   "; // String con espacios en blanco
        profesional.setFechaIngreso(fechaInvalida);
        assertNull(profesional.getFechaIngreso(), "El setter de fecha de ingreso debe asignar null si la entrada es una cadena vacía o con espacios.");
    }

    // --- Tests para el método 'mostrarDatos' ---
    @Test
    void testMostrarDatosCompleto_RetornaStringCorrecto() {
        String expected = String.format("RUT: %s\nNombre: %s\nFecha de Nacimiento: %s\nTitulo: %s\nFecha de ingreso: %s",
                                        profesional.getRun(), profesional.getNombre(),
                                        profesional.getFechaNacimiento(), profesional.getTitulo(),
                                        profesional.getFechaIngreso());
        assertEquals(expected, profesional.mostrarDatos(), "El método mostrarDatos debe retornar el string esperado.");
    }

    @Test
    void testMostrarDatosFechaIngresoNoInformada_RetornaStringCorrecto() {
        Profesional p = new Profesional(nombreValido, fechaNacimientoValida, runValido, tituloValido, null);
        String expected = String.format("RUT: %s\nNombre: %s\nFecha de Nacimiento: %s\nTitulo: %s\nFecha de ingreso: No informado",
                                        p.getRun(), p.getNombre(), p.getFechaNacimiento(), p.getTitulo());
        assertEquals(expected, p.mostrarDatos(), "El método mostrarDatos debe indicar 'No informado' si la fecha de ingreso es nula.");
    }

    // --- Tests para el método 'analizarUsuario' ---
    @Test
    void testAnalizarUsuario_RetornaStringCorrecto() {
        String expected = "Tipo Usuario: Profesional, Nombre: " + nombreValido + ". RUT: " + runValido + ", Título: " + tituloValido + ", Fecha Ingreso: " + fechaIngresoValida;
        assertEquals(expected, profesional.analizarUsuario(), "El método analizarUsuario debe retornar el string correcto.");
    }

    // --- Tests para el método 'toString' ---
    @Test
    void testToString_RetornaStringCorrecto() {
        String expected = "Título: " + tituloValido + ", Fecha Ingreso: " + fechaIngresoValida;
        assertEquals(expected, profesional.toString(), "El método toString debe retornar el string esperado.");
    }
}