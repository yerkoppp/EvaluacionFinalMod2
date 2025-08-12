package prevencionDeRiesgos;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ AccidenteTest.class, AdministrativoTest.class, CapacitacionTest.class, ClienteTest.class,
		ContenedorTest.class, PrincipalTest.class, ProfesionalTest.class, RevisionTest.class, UsuarioTest.class,
		ValidacionTest.class })
public class AllTests {

}
