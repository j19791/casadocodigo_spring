package br.com.casadocodigo.loja.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// configuração do SpringMVC para atender as requisições que chegam para nossa aplicação
//configurarmos o servidor Tomcat para que ele passe as requisições para o SpringMVC através de Servlet
public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() { // a classe AppWebConfiguration será usada como classe de
														// configuração do servlet do SpringMVC
		return new Class[] { AppWebConfiguration.class, JPAConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() { // array com os mapeamentos que queremos que nosso servlet atenda
		return new String[] { "/" }; // o servlet do SpringMVC atenderá as requisições a partir da raiz do nosso
										// projeto (/)
	}

	@Override
	protected Filter[] getServletFilters() {// ao receber a requisição, o Spring filtra a requisição com o encoding que
											// vamos configurar
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8"); // adicionar este filtro ao array de filtros
		return new Filter[] { encodingFilter }; // retornar o filtro configurado (o array) para o Spring
	}

	@Override // mais configurações para importar arquivo
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}

}
