package prevencionDeRiesgos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prevencionDeRiesgos.Administrativo;

class AdministrativoTest {

    private Administrativo administrativo;
    private final String nombreValido = "Wenceslao Arriagada";
    private final String fechaNacimientoValida = "15/09/1990";
    private final String runValido = "4.252.845-5";
    private final String areaValida = "Recursos Humanos";
    private final String experienciaValida = "Más de 5 años de experiencia en gestión de personal.";

    @BeforeEach
    void setUp() {
        // Inicializar un objeto Administrativo para cada test con datos válidos
        administrativo = new Administrativo(nombreValido, fechaNacimientoValida, runValido, areaValida, experienciaValida);
    }

    @Test
    void testConstructorVacio_CreaObjetoAdministrativo() {
        Administrativo a = new Administrativo();
        assertNotNull(a, "El constructor vacío no debe retornar null.");
    }

    @Test
    void testConstructorConParametros_InicializaCorrectamente() {
        assertNotNull(administrativo, "El constructor con parámetros no debe retornar null.");
        assertEquals(nombreValido, administrativo.getNombre(), "El nombre no coincide.");
        assertEquals(fechaNacimientoValida, administrativo.getFechaNacimiento(), "La fecha de nacimiento no coincide.");
        assertEquals(runValido, administrativo.getRun(), "El RUN no coincide.");
        assertEquals(areaValida, administrativo.getArea(), "El área no coincide.");
        assertEquals(experienciaValida, administrativo.getExperienciaPrevia(), "La experiencia previa no coincide.");
    }

    // --- Tests para el atributo 'area' y sus validaciones ---
    @Test
    void testSetAreaValida_AsignaCorrectamente() {
        String nuevaArea = "Contabilidad";
        administrativo.setArea(nuevaArea);
        assertEquals(nuevaArea, administrativo.getArea(), "El setter de área debe asignar un valor válido.");
    }

    @Test
    void testSetAreaCorta_LanzaExcepcion() {
        String areaInvalida = "Cont"; // Menos de 5 caracteres
        assertThrows(IllegalArgumentException.class, () -> administrativo.setArea(areaInvalida),
                     "El setter de área debe lanzar excepción si es muy corta.");
    }

    @Test
    void testSetAreaLarga_LanzaExcepcion() {
        String areaInvalida = "Administración y Finanzas muy larga"; // Más de 20 caracteres
        assertThrows(IllegalArgumentException.class, () -> administrativo.setArea(areaInvalida),
                     "El setter de área debe lanzar excepción si es muy larga.");
    }
    
    @Test
    void testSetAreaNula_LanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> administrativo.setArea(null),
                     "El setter de área debe lanzar excepción si es null.");
    }
    
    @Test
    void testSetAreaVacia_LanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> administrativo.setArea("  "),
                     "El setter de área debe lanzar excepción si es vacía.");
    }

    // --- Tests para el atributo 'experienciaPrevia' y sus validaciones ---
    @Test
    void testSetExperienciaPreviaValida_AsignaCorrectamente() {
        String nuevaExperiencia = "Experiencia en gestión de personal.";
        administrativo.setExperienciaPrevia(nuevaExperiencia);
        assertEquals(nuevaExperiencia, administrativo.getExperienciaPrevia(), "El setter de experiencia previa debe asignar un valor válido.");
    }
    
    @Test
    void testSetExperienciaPreviaConMasDe100Caracteres_LanzaExcepcion() {
        String experienciaInvalida = "Experiencia laboral muy detallada que sobrepasa el límite de cien caracteres para probar que la validación funciona correctamente. Este texto es bastante largo.";
        assertThrows(IllegalArgumentException.class, () -> administrativo.setExperienciaPrevia(experienciaInvalida),
                     "El setter de experiencia previa debe lanzar excepción si es muy larga.");
    }
    
    @Test
    void testSetExperienciaPreviaNula_AsignaNulo() {
        administrativo.setExperienciaPrevia(null);
        assertNull(administrativo.getExperienciaPrevia(), "El setter de experiencia previa debe asignar null si la entrada es nula.");
    }
    
    @Test
    void testSetExperienciaPreviaVacia_AsignaNulo() {
        administrativo.setExperienciaPrevia(" ");
        assertNull(administrativo.getExperienciaPrevia(), "El setter de experiencia previa debe asignar null si la entrada es una cadena vacía o con espacios.");
    }

    // --- Tests para el método 'mostrarDatos' ---
    @Test
    void testMostrarDatosCompleto_RetornaStringCorrecto() {
        String expected = String.format("RUT: %s\nNombre: %s\nFecha de Nacimiento: %s\nArea: %s\nExperiencia previa: %s",
                                        administrativo.getRun(), administrativo.getNombre(),
                                        administrativo.getFechaNacimiento(), administrativo.getArea(),
                                        administrativo.getExperienciaPrevia());
        assertEquals(expected, administrativo.mostrarDatos(), "El método mostrarDatos debe retornar el string esperado.");
    }

    @Test
    void testMostrarDatosExperienciaNoInformada_RetornaStringCorrecto() {
        Administrativo a = new Administrativo(nombreValido, fechaNacimientoValida, runValido, areaValida, null);
        String expected = String.format("RUT: %s\nNombre: %s\nFecha de Nacimiento: %s\nArea: %s\nExperiencia previa: No informado",
                                        a.getRun(), a.getNombre(), a.getFechaNacimiento(), a.getArea());
        assertEquals(expected, a.mostrarDatos(), "El método mostrarDatos debe indicar 'No informado' si la experiencia es nula.");
    }

    // --- Tests para el método 'analizarUsuario' ---
    @Test
    void testAnalizarUsuario_RetornaStringCorrecto() {
        String expected = "Tipo Usuario: Administrativo, Nombre: " + nombreValido + ". RUT: " + runValido + ", Área:" + areaValida + ", Experiencia Previa: " + experienciaValida;
        assertEquals(expected, administrativo.analizarUsuario(), "El método analizarUsuario debe retornar el string correcto.");
    }

    // --- Tests para el método 'toString' ---
    @Test
    void testToString_RetornaStringCorrecto() {
        String expected = "Área:" + areaValida + ", Experiencia Previa: " + experienciaValida;
        assertEquals(expected, administrativo.toString(), "El método toString debe retornar el string esperado.");
    }
}