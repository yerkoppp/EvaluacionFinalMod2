package prevencionDeRiesgos;

import static org.junit.Assume.assumeNoException;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidacionTest {

	@Test
	public void testValidarFecha() {
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Validacion.validarFecha("23-05-25"); }	);
		assertEquals("⚠️ El formato de la fecha no es válido. Utilice DD/MM/AAAA.", exception.getMessage());
	}

}
