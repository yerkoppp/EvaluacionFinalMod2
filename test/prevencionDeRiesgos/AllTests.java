package prevencionDeRiesgos;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ AccidenteTest.class, CapacitacionTest.class, ClienteTest.class, ContenedorTest.class,
		PrincipalTest.class, RevisionTest.class, UsuarioTest.class, ValidacionTest.class, VisitaEnTerrenoTest.class })
public class AllTests {

}
