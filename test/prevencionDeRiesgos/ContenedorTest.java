package prevencionDeRiesgos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContenedorTest {

    private Contenedor contenedor;
    private Cliente cliente;
    private Profesional profesional;
    private Administrativo administrativo;
    private Capacitacion capacitacion;

    @BeforeEach
    void setUp() {
        contenedor = new Contenedor();

        // Cliente de prueba
        cliente = new Cliente("2.222.222-8", // run
                "Juanito", // nombres
                "12/05/1990", // fechaNacimiento
                "Pérez González", // apellidos
                "+56912345678", // teléfono
                "Cuprum", // afp
                1, // sistemaSalud (1 para Fonasa, 2 para Isapre)
                "Av. Siempre Viva 742", // dirección
                "Santiago", // comuna
                34 // edad
        );

        // Profesional de prueba
        profesional = new Profesional("María José Contreras", // nombre
                "20/06/1985", // fechaNacimiento
                "14.256.780-6", // run
                "Ingeniera en Prevención de Riesgos", // título
                "15/03/2020" // fechaIngreso
        );

        // Administrativo de prueba
        administrativo = new Administrativo("Luis Torres", // nombre
                "15/04/1980", // fechaNacimiento
                "14.256.789-K", // run
                "Recursos Humanos", // área
                "Más de 10 años en selección" // experienciaPrevia
        );

        // Capacitacion de prueba
        capacitacion = new Capacitacion(
                "18.234.567-9", // rutCliente
                "Lunes", // día
                "10:00", // hora
                "Sala de reuniones 3", // lugar
                "90 minutos", // duración
                25 // cantidad de asistentes
        );
    }

    // ========================= ALMACENAR USUARIOS =========================
    
    @Test
    void testAlmacenarClienteExitoso() {
        contenedor.almacenarCliente(cliente);
        assertTrue(contenedor.getAsesorias().size() == 1);
    }
    
    @Test
    void testAlmacenarClienteNulo() {
        assertThrows(IllegalArgumentException.class, () -> contenedor.almacenarCliente(null));
    }
    
    @Test
    void testAlmacenarProfesionalExitoso() {
        contenedor.almacenarProfesional(profesional);
        assertTrue(contenedor.getAsesorias().size() == 1);
    }

    @Test
    void testAlmacenarProfesionalNulo() {
        assertThrows(IllegalArgumentException.class, () -> contenedor.almacenarProfesional(null));
    }
    
    @Test
    void testAlmacenarAdministrativoExitoso() {
        contenedor.almacenarAdministrativo(administrativo);
        assertTrue(contenedor.getAsesorias().size() == 1);
    }
    
    @Test
    void testAlmacenarAdministrativoNulo() {
        assertThrows(IllegalArgumentException.class, () -> contenedor.almacenarAdministrativo(null));
    }

    // ========================= ELIMINAR USUARIO =========================

    @Test
    void testEliminarUsuarioExitoso() {
        contenedor.almacenarCliente(cliente);
        contenedor.eliminarUsuario("2.222.222-8");
        assertThrows(IllegalArgumentException.class, () -> contenedor.listarUsuarios());
    }

    @Test
    void testEliminarUsuarioInexistente() {
    	contenedor.almacenarCliente(cliente);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        	contenedor.eliminarUsuario("9.999.999-3");
        });
        assertEquals("⚠️ RUT no existe en usuarios.", exception.getMessage());
    }
    

    @Test
    void testEliminarUsuarioListaVacia() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        	contenedor.eliminarUsuario("9.999.999-3");
        });
        assertEquals("⚠️ No hay usuarios agregados a asesorias.", exception.getMessage());
    }

    // ========================= LISTAR USUARIOS =========================

    @Test
    void testListarUsuariosVacio() {        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        	contenedor.listarUsuarios();
        });
        assertEquals("⚠️ No hay usuarios agregados a asesorias.", exception.getMessage());
    }

    @Test
    void testListarUsuariosExitoso() {
        contenedor.almacenarCliente(cliente);
        contenedor.almacenarProfesional(profesional);
        String resultado = contenedor.listarUsuarios();
        assertTrue(resultado.contains("2.222.222-8"));
        assertTrue(resultado.contains("14.256.780-6"));
    }

    // ========================= LISTAR POR TIPO =========================

    @Test
    void testListarUsuariosPorTipoCliente() {
        contenedor.almacenarCliente(cliente);
        String resultado = contenedor.listarUsuariosPorTipo("cliente");
        assertTrue(resultado.contains("2.222.222-8"));
    }

    @Test
    void testListarUsuariosPorTipoProfesional() {
        contenedor.almacenarProfesional(profesional);
        String resultado = contenedor.listarUsuariosPorTipo("profesional");
        assertTrue(resultado.contains("14.256.780-6"));
    }

    @Test
    void testListarUsuariosPorTipoAdministrativo() {
        contenedor.almacenarAdministrativo(administrativo);
        String resultado = contenedor.listarUsuariosPorTipo("administrativo");
        assertTrue(resultado.contains("14.256.789-K"));
    }

    @Test
    void testListarUsuariosPorTipoInvalido() {
    	contenedor.almacenarCliente(cliente);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        	contenedor.listarUsuariosPorTipo("jefe");
        });
        assertEquals("⚠️ El tipo ingresado es inválido.", exception.getMessage());
    }
    
    
    @Test
    void testListarUsuariosPorTipoSinUsuarios() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        	contenedor.listarUsuariosPorTipo("cliente");
        });
        assertEquals("⚠️ No hay usuarios agregados a asesorias.", exception.getMessage());
    }

    // ========================= LISTAR CAPACITACIONES =========================

    @Test
    void testListarCapacitacionesVacio() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        	contenedor.listarCapacitaciones();
        });
        assertEquals("⚠️ No hay capacitaciones agregadas.", exception.getMessage());
    }

    @Test
    void testListarCapacitacionesExitoso() {
        contenedor.almacenarCapacitacion(capacitacion);
        String resultado = contenedor.listarCapacitaciones();
        assertTrue(resultado.contains("18.234.567-9")); // Depende de toString() de Capacitacion
    }
}
