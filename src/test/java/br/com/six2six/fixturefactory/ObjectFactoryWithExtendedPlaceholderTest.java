package br.com.six2six.fixturefactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.six2six.fixturefactory.model.EmpresaMatriz;

public class ObjectFactoryWithExtendedPlaceholderTest {

	@BeforeClass
	public static void setUp() {
		FixtureFactoryLoader.loadTemplates("br.com.six2six.template");
	}

	@Test
	public void circularReference() {
		EmpresaMatriz empresa = Fixture.from(EmpresaMatriz.class).gimme("valid");
		assertThat(empresa.getCnpj(), is(equalTo(empresa.getEmpresaFilial().getCnpjMatriz())));
	}
}
