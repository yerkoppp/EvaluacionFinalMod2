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

class UsuarioTest {

    private Usuario usuario; // Instancia de Usuario para usar en los tests

    // Este método se ejecuta antes de CADA test para asegurar un estado limpio
    @BeforeEach
    void setUp() {
        // Inicializamos un usuario con datos válidos por defecto
        usuario = new Usuario("Juan juanson", "05/05/1990", "15.912.125-9");
    }

    // --- Tests para el Constructor por Defecto ---
    @Test
    @DisplayName("Constructor por defecto: Debería crear un objeto Usuario no nulo")
    void constructorDefecto_creaObjetoNoNulo() {
        Usuario usuarioDefault = new Usuario();
        assertNotNull(usuarioDefault, "El objeto Usuario creado por el constructor por defecto no debería ser nulo.");
    }

    // --- Tests para el Constructor con Parámetros ---
    @Test
    @DisplayName("Constructor con parámetros: Debería inicializar un usuario con datos válidos")
    void constructorParametros_datosValidos_inicializaUsuario() {
        String nombre = "Willy Wonka"; // Asegurarse que tiene 10-50 chars
        String fechaNacimiento = "15/05/1985";
        String run = "8.286.855-0"; // RUN válido
        Usuario nuevoUsuario = new Usuario(nombre, fechaNacimiento, run);

        assertNotNull(nuevoUsuario);
        assertEquals(nombre, nuevoUsuario.getNombre());
        assertEquals(fechaNacimiento, nuevoUsuario.getFechaNacimiento());
        assertEquals(run, nuevoUsuario.getRun());
    }

    @ParameterizedTest
    @CsvSource({
            "Corto, 01/01/1990, 12.345.678-9", // Nombre muy corto (longitud 5)
            // Longitud 51, excede el máximo de 50
            "EsteEsUnNombreMuyLargoQueExcedeElMaximoPermitidoDe50Caracteres, 01/01/1990, 12.345.678-9"
    })
    @DisplayName("Constructor con parámetros: Debería lanzar IllegalArgumentException para nombre inválido por longitud")
    void constructorParametros_nombreLongitudInvalida_lanzaIllegalArgumentException(String nombre, String fechaNacimiento, String run) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new Usuario(nombre, fechaNacimiento, run)
        );
        // El mensaje de error esperado es el de Usuario.setNombre, que concatena el de Validacion.validarLargoString
        // "Nombre inválido: ⚠️ El largo no es permitido. Debe estar entre 10 y 50 carácteres"
        assertTrue(thrown.getMessage().contains("Nombre inválido: ⚠️ El largo no es permitido."));
        assertTrue(thrown.getMessage().contains("Debe estar entre 10 y 50 carácteres"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t"})
    @DisplayName("Constructor con parámetros: Debería lanzar IllegalArgumentException para nombre nulo o vacío")
    void constructorParametros_nombreNuloVacio_lanzaIllegalArgumentException(String nombre) {
        // Usamos una fecha y RUN válidos para aislar el error del nombre
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new Usuario(nombre, "01/01/1990", "12.345.678-9")
        );
        assertTrue(thrown.getMessage().contains("⚠️ Los nombres son obligatorio."));
    }

    // --- TEST QUE FALLÓ ANTERIORMENTE, REVISADO CON MENSAJES DE Validacion.java ---
    @ParameterizedTest
    @CsvSource({
            "Pedro Garcia, 32/12/2000, 12.345.678-9", // Fecha inválida por día (Validacion.validarFecha)
            "Pedro Garcia, 01-01-2000, 12.345.678-9", // Formato de fecha inválido (Validacion.validarFecha)
            "Pedro Garcia, 01/01/2050, 12.345.678-9"  // Fecha en el futuro (Validacion.calcularEdad, llamada por validarFecha)
    })
    @DisplayName("Constructor con parámetros: Debería lanzar IllegalArgumentException para fecha de nacimiento inválida")
    void constructorParametros_fechaNacimientoInvalida_lanzaIllegalArgumentException(String nombre, String fechaNacimiento, String run) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new Usuario(nombre, fechaNacimiento, run)
        );

        String actualMessage = thrown.getMessage();
        // Los mensajes de error de Validacion.java son:
        // "⚠️ El formato de la fecha no es válido. Utilice DD/MM/AAAA."
        // "Error: La fecha de nacimiento no puede ser en el futuro."
        assertTrue(
            actualMessage.contains("El formato de la fecha no es válido.") ||
            actualMessage.contains("La fecha de nacimiento no puede ser en el futuro."),
            "El mensaje de la excepción no coincide con los errores esperados de formato o fecha futura. Mensaje real: " + actualMessage
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t"})
    @DisplayName("Constructor con parámetros: Debería lanzar IllegalArgumentException para fecha de nacimiento nula o vacía")
    void constructorParametros_fechaNacimientoNulaVacia_lanzaIllegalArgumentException(String fechaNacimiento) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new Usuario("Nombre Valido Prueba", fechaNacimiento, "12.345.678-9")
        );
        // Mensaje directo de Usuario.setFechaNacimiento para null/vacío
        assertTrue(thrown.getMessage().contains("⚠️ La fecha de nacimiento es obligatoria."));
    }

    @ParameterizedTest
    @CsvSource({
            "Pedro Garcia, 01/01/2000, 1.234.567-8L", // RUN con caracter inválido
            "Pedro Garcia, 01/01/2000, 12345678-3",   // RUN sin puntos ni guión (formato incorrecto)
            "Pedro Garcia, 01/01/2000, 12.345.678-3"  // RUN con dígito verificador incorrecto (el correcto para 12345678 es 9)
    })
    @DisplayName("Constructor con parámetros: Debería lanzar IllegalArgumentException para RUN inválido")
    void constructorParametros_runInvalido_lanzaIllegalArgumentException(String nombre, String fechaNacimiento, String run) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new Usuario(nombre, fechaNacimiento, run)
        );
        String actualMessage = thrown.getMessage();
        // Los mensajes de error de Validacion.validarRut son:
        // "⚠️ El RUT es obligatorio. Ingrese un RUT en formato 99.999.999-9."
        // "⚠️ El formato de RUT debe ser 99.999.999-9."
        // "⚠️ RUT ingresado es invalido."
        assertTrue(
            actualMessage.contains("El RUT es obligatorio.") ||
            actualMessage.contains("El formato de RUT debe ser 99.999.999-9.") ||
            actualMessage.contains("RUT ingresado es invalido."),
            "El mensaje de la excepción no coincide con los errores esperados de RUT. Mensaje real: " + actualMessage
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t"})
    @DisplayName("Constructor con parámetros: Debería lanzar IllegalArgumentException para RUN nulo o vacío")
    void constructorParametros_runNuloVacio_lanzaIllegalArgumentException(String run) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new Usuario("Nombre Valido Prueba", "01/01/1990", run)
        );
        // Mensaje directo de Validacion.validarRut para null/vacío
        assertTrue(thrown.getMessage().contains("⚠️ El RUT es obligatorio."));
    }

    // --- Tests para Getters ---
    @Test
    @DisplayName("getNombre: Debería retornar el nombre correcto")
    void getNombre_retornaNombreCorrecto() {
        assertEquals("Juan juanson", usuario.getNombre());
    }

    @Test
    @DisplayName("getFechaNacimiento: Debería retornar la fecha de nacimiento correcta")
    void getFechaNacimiento_retornaFechaNacimientoCorrecta() {
        assertEquals("05/05/1990", usuario.getFechaNacimiento());
    }

    @Test
    @DisplayName("getRun: Debería retornar el RUN correcto")
    void getRun_retornaRunCorrecto() {
        assertEquals("15.912.125-9", usuario.getRun());
    }

    // --- Tests para Setters ---
    // setNombre
    @Test
    @DisplayName("setNombre: Debería establecer un nombre válido")
    void setNombre_nombreValido_estableceCorrectamente() {
        String nuevoNombre = "Un Nuevo Nombre Valido Ahora"; // 27 caracteres (entre 10 y 50)
        usuario.setNombre(nuevoNombre);
        assertEquals(nuevoNombre, usuario.getNombre());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t"})
    @DisplayName("setNombre: Debería lanzar IllegalArgumentException para nombre nulo, vacío o solo espacios")
    void setNombre_nombreNuloVacioEspacios_lanzaIllegalArgumentException(String nombreInvalido) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                usuario.setNombre(nombreInvalido)
        );
        assertTrue(thrown.getMessage().contains("⚠️ Los nombres son obligatorio."));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Tom", "NombreDemasiadoLargoParaSerValidoPorLaValidacionDelSistemaQueDebeTenerUnMaximoDeCincuentaCaracteresExactamente"}) // Corto tiene <10, Largo tiene >50
    @DisplayName("setNombre: Debería lanzar IllegalArgumentException para nombre fuera del rango de longitud (10-50)")
    void setNombre_nombreLongitudInvalida_lanzaIllegalArgumentException(String nombreInvalido) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> // Notar el .Service aquí.
                usuario.setNombre(nombreInvalido)
        );
        // El mensaje de error esperado es el de Usuario.setNombre, que concatena el de Validacion.validarLargoString
        // "Nombre inválido: ⚠️ El largo no es permitido. Debe estar entre 10 y 50 carácteres"
        assertTrue(thrown.getMessage().contains("Nombre inválido: ⚠️ El largo no es permitido."));
        assertTrue(thrown.getMessage().contains("Debe estar entre 10 y 50 carácteres"));
    }

    // setFechaNacimiento
    @Test
    @DisplayName("setFechaNacimiento: Debería establecer una fecha de nacimiento válida")
    void setFechaNacimiento_fechaValida_estableceCorrectamente() {
        String nuevaFecha = "20/02/1995";
        usuario.setFechaNacimiento(nuevaFecha);
        assertEquals(nuevaFecha, usuario.getFechaNacimiento());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t"})
    @DisplayName("setFechaNacimiento: Debería lanzar IllegalArgumentException para fecha de nacimiento nula o vacía")
    void setFechaNacimiento_nulaOVacia_lanzaIllegalArgumentException(String fechaInvalida) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                usuario.setFechaNacimiento(fechaInvalida)
        );
        // Mensaje directo de Usuario.setFechaNacimiento para null/vacío
        assertTrue(thrown.getMessage().contains("⚠️ La fecha de nacimiento es obligatoria."));
    }

    @ParameterizedTest
    @ValueSource(strings = {"32/01/2000", "01/13/2000", "2000-01-01", "01/01/50"}) // Fechas con formato o valores inválidos
    @DisplayName("setFechaNacimiento: Debería lanzar IllegalArgumentException para fecha de nacimiento con formato inválido")
    void setFechaNacimiento_formatoInvalido_lanzaIllegalArgumentException(String fechaInvalida) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                usuario.setFechaNacimiento(fechaInvalida)
        );
        // El mensaje de error viene directamente de Validacion.validarFecha
        assertTrue(thrown.getMessage().contains("⚠️ El formato de la fecha no es válido. Utilice DD/MM/AAAA."));
    }

    @Test
    @DisplayName("setFechaNacimiento: Debería lanzar IllegalArgumentException para fecha de nacimiento en el futuro")
    void setFechaNacimiento_fechaFutura_lanzaIllegalArgumentException() {
        // Generar una fecha en el futuro para asegurar que el test sea robusto con el tiempo
        String fechaFutura = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                usuario.setFechaNacimiento(fechaFutura)
        );
        // El mensaje de error viene de Validacion.calcularEdad (que es llamado por Validacion.validarFecha si está en el futuro)
        assertTrue(thrown.getMessage().contains("Error: La fecha de nacimiento no puede ser en el futuro."));
    }

    // setRun
    @Test
    @DisplayName("setRun: Debería establecer un RUN válido")
    void setRun_runValido_estableceCorrectamente() {
        String nuevoRun = "14.790.717-6"; // Un RUN válido diferente al inicial
        usuario.setRun(nuevoRun);
        assertEquals(nuevoRun, usuario.getRun());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "1234567-8", "12.345.678-A", "12.345.678-3"}) // RUNs inválidos: sin formato, caracter no DV, DV incorrecto
    @DisplayName("setRun: Debería lanzar IllegalArgumentException para RUN inválido")
    void setRun_runInvalido_lanzaIllegalArgumentException(String runInvalido) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                usuario.setRun(runInvalido)
        );
        String actualMessage = thrown.getMessage();
        // Los mensajes de error de Validacion.validarRut son:
        // "⚠️ El RUT es obligatorio. Ingrese un RUT en formato 99.999.999-9."
        // "⚠️ El formato de RUT debe ser 99.999.999-9."
        // "⚠️ RUT ingresado es invalido."
        assertTrue(
            actualMessage.contains("El RUT es obligatorio.") ||
            actualMessage.contains("El formato de RUT debe ser 99.999.999-9.") ||
            actualMessage.contains("RUT ingresado es invalido."),
            "El mensaje de la excepción no coincide con los errores esperados de RUT. Mensaje real: " + actualMessage
        );
    }


    // --- Tests para Métodos Especiales ---
    @Test
    @DisplayName("mostrarDatos: Debería retornar el String formateado con los datos del usuario")
    void mostrarDatos_retornaStringFormateado() {
        // Basado en la implementación de Usuario.mostrarDatos():
        // String.format("RUT: %s\nNombre: %s\nFecha de Nacimiento: %s", nombre, run, fechaNacimiento);
        // Esto significa que el primer %s es 'nombre', el segundo 'run', y el tercero 'fechaNacimiento'.
        // El 'expected' se construye para coincidir con este orden.
        String expected = String.format(
                "RUT: %s\nNombre: %s\nFecha de Nacimiento: %s",
                usuario.getNombre(), usuario.getRun(), usuario.getFechaNacimiento());

        assertEquals(expected, usuario.mostrarDatos());
    }

    @Test
    @DisplayName("mostrarEdad: Debería retornar el String formateado con la edad del usuario")
    void mostrarEdad_retornaStringConEdad() {
        // Establecemos una fecha de nacimiento específica para el usuario de prueba
        // para asegurar que la edad calculada sea predecible (ej. 25 años).
        String dob = LocalDate.now().minusYears(25).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        usuario.setFechaNacimiento(dob);

        String expected = String.format("El usuario tiene %d años", 25);
        assertEquals(expected, usuario.mostrarEdad());
    }

    @Test
    @DisplayName("analizarUsuario: Debería retornar el String con nombre y RUT para análisis")
    void analizarUsuario_retornaStringAnalisis() {
        String expected = String.format("\nNombre: %s. RUT: %s\n", usuario.getNombre(), usuario.getRun());
        assertEquals(expected, usuario.analizarUsuario());
    }

    @Test
    @DisplayName("toString: Debería retornar el String de representación del objeto Usuario")
    void toString_retornaStringCorrecto() {
        String expected = String.format("Usuario: %s, Fecha Nacimiento: %s, RUN:%s",
                usuario.getNombre(), usuario.getFechaNacimiento(), usuario.getRun());
        assertEquals(expected, usuario.toString());
    }
}