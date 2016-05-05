package br.com.six2six.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.six2six.fixturefactory.model.EmpresaMatriz;

public class EmpresaMatrizTemplate implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(EmpresaMatriz.class).addTemplate("valid", new Rule() {
			{
				add("cnpj", cnpj());
				add("empresaFilial.cnpjMatriz", "#{cnpj}");
			}
		});
	}

}
