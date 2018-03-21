package br.com.casadocodigo.loja.conf;

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
		return new Class[] { AppWebConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() { // array com os mapeamentos que queremos que nosso servlet atenda
		return new String[] { "/" }; // o servlet do SpringMVC atenderá as requisições a partir da raiz do nosso
										// projeto (/)
	}

}
