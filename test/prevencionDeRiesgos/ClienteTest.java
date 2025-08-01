package prevencionDeRiesgos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClienteTest {

	// ======================= PRUEBAS DEL CONSTRUCTOR =======================

	@Test
	void testConstructorClienteValido() {
		// Cliente de prueba
		Cliente cliente = new Cliente("2.222.222-8", // run
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

		assertEquals("2.222.222-8", cliente.getRun());
		assertEquals("Juanito", cliente.getNombres());
		assertEquals("12/05/1990", cliente.getFechaNacimiento());
		assertEquals("Pérez González", cliente.getApellidos());
		assertEquals("+56912345678", cliente.getTelefono());
		assertEquals("Cuprum", cliente.getAfp());
		assertEquals(1, cliente.getSistemaSalud());
		assertEquals("Av. Siempre Viva 742", cliente.getDireccion());
		assertEquals("Santiago", cliente.getComuna());
		assertEquals(34, cliente.getEdad());

	}

	@Test
	void testConstructorClienteInvalidoNombres() {
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> new Cliente("2.222.222-8", "Ana", "12/05/1990",
						"Pérez González", "+56912345678", "Cuprum", 1,
						"Av. Siempre Viva 742", "Santiago", 34));
		assertEquals("⚠️ Nombres deben tener minimo 5 y máximo 30 caracteres.",
				exception.getMessage());
	}

	// ======================= PRUEBAS SETTERS =======================
	Cliente cliente;

	@BeforeEach
	void setUp() {
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
	}

	@Test
	void testSetNombresValido() {
		cliente.setNombres("Marcelo");
		assertEquals("Marcelo", cliente.getNombres());
	}

	@Test
	void testSetNombresInvalidoCorto() {
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> cliente.setNombres("Ana"));
		assertEquals("⚠️ Nombres deben tener minimo 5 y máximo 30 caracteres.",
				exception.getMessage());
	}

	@Test
	void testSetNombresVacio() {
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> cliente.setNombres(""));
		assertEquals("⚠️ Los nombres son obligatorio.", exception.getMessage());
	}

	@Test
	void testSetApellidosValido() {
		cliente.setApellidos("Ramírez");
		assertEquals("Ramírez", cliente.getApellidos());
	}

	@Test
	void testSetApellidosInvalido() {
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> cliente.setApellidos("Li"));
		assertEquals(
				"⚠️ Apellidos deben tener minimo 5 y máximo 30 caracteres.",
				exception.getMessage());
	}

	@Test
	void testSetTelefonoInvalido() {
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> cliente.setTelefono(""));
		assertEquals("⚠️ El teléfono es obligatorio.", exception.getMessage());
	}

	@Test
	void testSetAfpValido() {
		cliente.setAfp("Habitat");
		assertEquals("Habitat", cliente.getAfp());
	}

	@Test
	void testSetAfpNull() {
		cliente.setAfp(null);
		assertNull(cliente.getAfp());
	}

	@Test
	void testSetSistemaSaludInvalido() {
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> cliente.setSistemaSalud(3));
		assertEquals("⚠️ Elija un ópcion correcta.", exception.getMessage());
	}

	@Test
	void testSetEdadInvalida() {
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> cliente.setEdad(200));
		assertEquals("⚠️ Edad debe ser entre 0 y 150.", exception.getMessage());
	}
	
	  // ======================= PRUEBAS MÉTODOS ESPECIALES =======================

    @Test
    void testObtenerNombre() {
        cliente.setNombres("Pedro");
        cliente.setApellidos("Ramírez");
        assertEquals("Pedro Ramírez", cliente.obtenerNombre());
    }

    @Test
    void testObtenerSistemaSaludFonasa() {
        cliente.setSistemaSalud(1);
        assertEquals("Fonasa", cliente.obtenerSistemaSalud());
    }

    @Test
    void testObtenerSistemaSaludIsapre() {
        cliente.setSistemaSalud(2);
        assertEquals("Isapre", cliente.obtenerSistemaSalud());
    }

    // ======================= PRUEBAS DE REPRESENTACIÓN =======================

    @Test
    void testMostrarDatos() {
        String datos = cliente.mostrarDatos();
        assertTrue(datos.contains("RUT: 2.222.222-8"));
        assertTrue(datos.contains("Fonasa"));
        assertTrue(datos.contains("Edad: 34"));
    }

    @Test
    void testToStringContieneDatos() {
        String texto = cliente.toString();
        assertTrue(texto.contains("Juanito Pérez González"));
        assertTrue(texto.contains("2.222.222-8"));
    }

}
