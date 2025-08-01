package prevencionDeRiesgos;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.Duration;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.TeeOutputStream;

class PrincipalTest {
	
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    
    private ByteArrayOutputStream outputStream;
    private ByteArrayOutputStream errorStream;

	@BeforeEach
	void configurarScanner() {

		Principal.contenedor = new Contenedor(); // Reiniciar contenedor
		
		 // Captura estándar de salida
        outputStream = new ByteArrayOutputStream();
        TeeOutputStream teeOut = new TeeOutputStream(originalOut, outputStream);
        System.setOut(new PrintStream(teeOut));

        // Captura de errores
        errorStream = new ByteArrayOutputStream();
        TeeOutputStream teeErr = new TeeOutputStream(originalErr, errorStream);
        System.setErr(new PrintStream(teeErr));
	}

	@AfterEach
	void cerrarScanner() {
		Principal.sc.close();

		// Restauramos System.in a su estado original
		System.setIn(originalIn);
        System.setOut(originalOut); // Restaura System.out
        System.setErr(originalErr); // Restaura System.err
	}
	
	
    // ========================= PRUEBAS BIENVENIDA =========================
	
	@Test
	void testMostrarBienvenida() {
	    Principal.mostrarBienvenida();
	    String salida = outputStream.toString();
	    assertTrue(salida.contains("BIENVENIDO AL SISTEMA DE GESTIÓN DE PREVENCIÓN DE RIESGOS"));
	    assertTrue(salida.contains("=".repeat(50)));
	}

	   // ======================= PRUEBAS MOSTRAR MENÚ =======================
	
	@Test
	void testMostrarMenu() {
	    Principal.mostrarMenu();
	    String salida = outputStream.toString();
	    for (int i = 1; i <= 9; i++) {
	        assertTrue(salida.contains("(" + i + ")"));
	    }
	}
	
    // ========================= PRUEBAS OPCIONMENU =========================
	@Test
	void testElegirOpcionMenuEntradaValida() {
		String entradaSimulada = "5\n"; // Simula escribir "5" y presionar Enter
		System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

		Principal.sc = new Scanner(System.in); 
		int resultado = Principal.elegirOpcionMenu();

		assertEquals(5, resultado);
	}

	@Test
	void testElegirOpcionMenuEntradaInvalida() {
		String input = "abc\n"; // Simula escribir "abc" y presionar Enter
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		Principal.sc = new Scanner(System.in);
		int resultado = Principal.elegirOpcionMenu();

		assertEquals(12749, resultado); // Debe retornar el valor por defecto en
										// caso de error
	}
	
	@Test
	void testElegirOpcionMenuEntradaInvalidaConMensaje() {
	    String input = "abc\n";
	    System.setIn(new ByteArrayInputStream(input.getBytes()));
	    Principal.sc = new Scanner(System.in);
	    Principal.elegirOpcionMenu();
	    assertTrue(outputStream.toString().contains("⚠️ Entrada inválida. Debe ingresar un número."));
	}
	
	
	// ======================= PRUEBAS EJECUTAR OPCION =======================
	
	@Test
	void testEjecutarOpcionSalir() {
	    Principal.continuarMain = true;
	    Principal.ejecutarOpcion(9);
	    assertFalse(Principal.continuarMain);
	}
	
	  @Test
	    void testEjecutarOpcionInvalida() {
	        Principal.ejecutarOpcion(20);
	        assertTrue(outputStream.toString().contains("⚠️ Opción no válida"));
	    }

	  // ================== TESTS ingresarCapacitacion ==================
	    @Test
	    void testIngresarCapacitacionConErroresYExito() {
	        String entradaSimulada = String.join("\n",
	                "abc123",          // RUT inválido
	                "2.222.222-8",    // RUT válido
	                "Lunes", 
	                "10:00", 
	                "Sala A, Ofcina estatal",
	                "2 horas",
	                "abc",             // asistentes inválido
	                "20"               // asistentes válido
	        ) + "\n";

	        System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));
	        
			// Reinicia el Scanner antes de cada test (simulando System.in)
			Principal.sc = new Scanner(System.in);
	        Principal.ingresarCapacitacion();
	        String salida = outputStream.toString();
			assertTrue(salida.contains("⚠️ Ingrese un número válido."));
	        assertTrue(salida.contains("Capacitación ingresada con éxito."));
	    }
    // ======================= PRUEBAS INGRESAR CLIENTE =======================
	

    @Test
    void testIngresarClienteExitoso() {
    	String entradaSimulada = String.join("\n", 
    		    "2.222.222-8", 
    		    "12/05/1990", 
    		    "Juan Alberto", 
    		    "Pérez González", 
    		    "+56912345678", 
    		    "Cuprum", 
    		    "1", 
    		    "Av. Siempre Viva 742", 
    		    "Santiago", 
    		    "34"
    		) + "\n";
    	
       System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));
        
		// Reinicia el Scanner antes de cada test (simulando System.in)
		Principal.sc = new Scanner(System.in);
        // Ejecutar el método
        Principal.ingresarCliente();

        // Validar que se haya impreso el mensaje de éxito
        String salidaConsola = outputStream.toString();
        assertTrue(salidaConsola.contains("Cliente ingresado con éxito."));

        // Validar que el cliente se haya almacenado en el contenedor
        assertFalse(Principal.contenedor.listarUsuarios().isEmpty());
       
    }
    
    @Test
    void testIngresarClienteConErroresYExitoFinal() {
    	
    	assertTimeout(Duration.ofSeconds(3), () -> {
        // Simulación de entradas:
        //  - RUT inválido primero ("abc123"), luego válido
        //  - Edad inválida primero ("texto"), luego válida
        String entradaSimulada = String.join("\n",
        		"",						// RUT vacio
                "abc123",              // RUT inválido (provocará error)
                "2.222.222-8",        // RUT válido
                "",						// Fecha vacio
                "01-02-2022",			// Fecha no vAlido formato
                "01/01/1990",          // Fecha nacimiento
                "Juan Alberto",                // Nombres
                "Pérez",               // Apellidos
                "+56912345678",        // Teléfono
                "Cuprum",              // AFP
                "1",                   // Sistema salud (Fonasa)
                "Calle Falsa 123",     // Dirección
                "Santiago",            // Comuna
                "texto",               // Edad inválida (InputMismatchException)
                "30"                   // Edad válida
        ) + "\n";

		System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

		// Reinicia el Scanner antes de cada test (simulando System.in)
		Principal.sc = new Scanner(System.in);
		// Ejecutar el método
		Principal.ingresarCliente();

		// Capturar salida y errores
		String salida = outputStream.toString();

		// Verifica error de RUT vacio
		assertTrue(salida.contains("⚠️ El RUT es obligatorio. Ingrese un RUT en formato 99.999.999-9."));

		// Verificar que el error por RUT inválido se mostró en la salida normal
		assertTrue(salida.contains("⚠️ El formato de RUT debe ser 99.999.999-9."));

		// Verifica error de fecha vacio
		assertTrue(salida.contains("⚠️ La fecha de nacimiento es obligatoria."));

		// Verifica error de fecha formato
		assertTrue(salida.contains(
				"⚠️ El formato de la fecha no es válido. Utilice DD/MM/AAAA."));

		// Verificar que el error por edad inválida se registró en salida
		// (InputMismatch)
		assertTrue(salida.contains("⚠️ Ingrese un número válido."));

		// Verificar que finalmente se ingresó el cliente con éxito
		assertTrue(salida.contains("Cliente ingresado con éxito."));
		
    	  });
    }
    
    @Test
    void testEdadInvalidaLuegoValida() {
        String entradaSimulada = String.join("\n",
                "2.222.222-8",
                "01/01/1990",
                "Juan Alberto", 
    		    "Pérez González",
                "+56912345678",
                "Cuprum",
                "1",
                "Calle Falsa 123",
                "Santiago",
                "abc",       // Edad inválida (texto)
                "30"         // Edad válida
        ) + "\n";

        System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

		// Reinicia el Scanner antes de cada test (simulando System.in)
		Principal.sc = new Scanner(System.in);
        Principal.ingresarCliente();

        String salida = outputStream.toString();
        assertTrue(salida.contains("⚠️ Ingrese un número válido."));
        assertTrue(salida.contains("Cliente ingresado con éxito."));
    }
    
    @Test
    void testSistemaSaludInvalidoLuegoValido() {
        String entradaSimulada = String.join("\n",
        		 "2.222.222-8",
                "01/01/1990",
                "Juan Alberto",
                "Pérez González",
                "+56912345678",
                "Cuprum",
                "3",        // Inválido
                "1",        // Válido (Fonasa)
                "Calle Falsa 123",
                "Santiago",
                "30"
        ) + "\n";

        System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

	// Reinicia el Scanner antes de cada test (simulando System.in)
	Principal.sc = new Scanner(System.in);
        Principal.ingresarCliente();

        String salida = outputStream.toString();
        assertTrue(salida.contains("⚠️ Elija un ópcion correcta."));
        assertTrue(salida.contains("Cliente ingresado con éxito."));
    }
    
    @Test
    void testAfpVacioOpcional() {
        String entradaSimulada = String.join("\n",
        		"2.222.222-8",
                "01/01/1990",
                "Juan Alberto",
                "Pérez González",
                "+56912345678",
                "",         // AFP vacía
                "1",
                "Calle Falsa 123",
                "Santiago",
                "30"
        ) + "\n";

        System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

        // Reinicia el Scanner antes de cada test (simulando System.in)
		Principal.sc = new Scanner(System.in);
        Principal.ingresarCliente();

        String salida = outputStream.toString();
        assertTrue(salida.contains("Cliente ingresado con éxito."));
    }
    
    @Test
    void testIngresarClienteConCamposOpcionalesVacios() {

        String entradaSimulada = String.join("\n",
        		"2.222.222-8",      // RUT
                "01/01/1990",        // Fecha nacimiento
                "Juan Alberto",             // Nombres
                "Pérez González",            // Apellidos
                "+56912345678",      // Teléfono
                "",                  // AFP vacía (opcional)
                "1",                 // Sistema salud (Fonasa)
                "",                  // Dirección vacía (opcional)
                "Santiago",          // Comuna
                "30"                 // Edad
        ) + "\n";

        System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

        // Reinicia el Scanner antes de cada test (simulando System.in)
		Principal.sc = new Scanner(System.in);
        // Ejecutar el método
        Principal.ingresarCliente();

        // Capturar salida
        String salida = outputStream.toString();

        // Verificar que no se lanzaron errores
        assertFalse(salida.contains("⚠️"), "No debería mostrar mensajes de error para AFP o Dirección vacías");

        // Confirmar que muestra mensaje de éxito
        assertTrue(salida.contains("Cliente ingresado con éxito."), "Debe mostrar mensaje de éxito");

        // Confirmar que el cliente se almacena en el contenedor
        assertFalse(Principal.contenedor.listarUsuarios().isEmpty(), "El cliente debe haberse almacenado correctamente");
    }
    
    
 // ================== TESTS ingresarProfesional ==================
    @Test
    void testIngresarProfesionalExitoso() {
        String entradaSimulada = String.join("\n",
                "12.345.678-5",
                "01/01/1985",
                "Pedro Gonzalez",
                "Prevención de riesgos",
                ""   // Fecha ingreso vacía
        ) + "\n";
        System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

        // Reinicia el Scanner antes de cada test (simulando System.in)
		Principal.sc = new Scanner(System.in);
        Principal.ingresarProfesional();
        String salida = outputStream.toString();
        assertTrue(salida.contains("Profesional ingresado con éxito."));
    }
    
    // ================== TESTS ingresarAdministrativo ==================
    @Test
    void testIngresarAdministrativoExitoso() {
        String entradaSimulada = String.join("\n",
                "12.345.678-5",
                "01/01/1980",
                "Luis Torres",
                "Recursos Humanos",
                "" // Experiencia previa vacía
        ) + "\n";
        System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

        // Reinicia el Scanner antes de cada test (simulando System.in)
		Principal.sc = new Scanner(System.in);
        Principal.ingresarAdministrativo();
        String salida = outputStream.toString();
        assertTrue(salida.contains("Administrativo ingresado con éxito."));
    }
    
 // ================== TESTS eliminarUsuario ==================
    @Test
    void testEliminarUsuarioExistente() {
        // Pre-cargar cliente
        Cliente cliente = new Cliente("12.345.678-5", "Juan Alberto", "01/01/1990", "Perez", "+56912345678",
                "Cuprum", 1, "Calle Falsa 123", "Santiago", 30);
        Principal.contenedor.almacenarCliente(cliente);

        String entradaSimulada = "12.345.678-5\n";
        System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

        // Reinicia el Scanner antes de cada test (simulando System.in)
		Principal.sc = new Scanner(System.in);
        Principal.eliminarUsuario();

        String salida = outputStream.toString();
        assertTrue(salida.contains("Usuario RUT: 12.345.678-5 => ELIMINADO"));
    }
    
    @Test
    void testEliminarUsuarioCancelarMenu() {
    	  String entradaSimulada = "menu\n";
    	System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

        // Reinicia el Scanner antes de cada test (simulando System.in)
		Principal.sc = new Scanner(System.in);
        Principal.eliminarUsuario();
        assertTrue(outputStream.toString().contains("menú"));
    }
    
 // ================== TEST listarUsuarios ==================
    @Test
    void testListarUsuariosVacio() {
        Principal.listarUsuarios();
        assertTrue(outputStream.toString().contains("⚠️ No hay usuarios agregados"));
    }
    
    @Test
    void testListarUsuariosConCliente() {
        Cliente cliente = new Cliente("12.345.678-5", "Juan Alberto", "01/01/1990", "Perez", "+56912345678",
                "Cuprum", 1, "Calle Falsa 123", "Santiago", 30);
        Principal.contenedor.almacenarCliente(cliente);

        Principal.listarUsuarios();
        assertTrue(outputStream.toString().contains("LISTA DE USUARIOS"));
        assertTrue(outputStream.toString().contains("Juan Alberto"));
    }

    // ================== TEST listarUsuariosTipo ==================
    @Test
    void testListarUsuariosTipoCliente() {
        Cliente cliente = new Cliente("12.345.678-5", "Juan Alberto", "01/01/1990", "Perez", "+56912345678",
                "Cuprum", 1, "Calle Falsa 123", "Santiago", 30);
        Principal.contenedor.almacenarCliente(cliente);

        String entradaSimulada = "2\n"; // Cliente
        System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

        // Reinicia el Scanner antes de cada test (simulando System.in)
		Principal.sc = new Scanner(System.in);
        Principal.listarUsuariosTipo();

        String salida = outputStream.toString();
        assertTrue(salida.contains("LISTA DE CLIENTES"));
        assertTrue(outputStream.toString().contains("Juan Alberto"));
    }
    
 // ================== TEST listarCapacitaciones ==================
    @Test
    void testListarCapacitacionesVacio() {
        Principal.listarCapacitaciones();
        assertTrue(outputStream.toString().contains("⚠️ No hay capacitaciones agregadas"));
    }
    
    @Test
    void testListarCapacitacionesConDatos() {
        Capacitacion cap = new Capacitacion(1, "12.345.679-3", "Lunes", "10:00", "Sala A, Edificio 83", "2 horas", 20);
        Principal.contenedor.almacenarCapacitacion(cap);

        Principal.listarCapacitaciones();
        assertTrue(outputStream.toString().contains("LISTA DE CAPACITACIONES"));
        assertTrue(outputStream.toString().contains("12.345.679-3"));
    }
    
 // ================== TEST FLUJO COMPLETO main() ==================
    @Test
    void testFlujoCompletoMain() {
        String entradaSimulada = String.join("\n",
                "1", // Ingresar cliente
                "12.345.679-3", "01/01/1990", "Juan Alberto", "Perez", "+56912345678", "Cuprum",
                "1", "Direccion X", "Comuna X", "30",
                "9" // Salir
        ) + "\n";
        

        System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

        // Reinicia el Scanner antes de cada test (simulando System.in)
		Principal.sc = new Scanner(System.in);
        assertTimeout(Duration.ofSeconds(5), () -> Principal.main(new String[] {}));

        String salida = outputStream.toString();
        assertTrue(salida.contains("Cliente ingresado con éxito."));
        assertTrue(salida.contains("El programa ha finalizado."));
    }
}
