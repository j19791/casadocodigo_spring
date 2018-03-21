package br.com.casadocodigo.loja.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controllers.HomeController;

@EnableWebMvc // anotamos que precisamos usar o recurso de Web MVC do SpringMVC
@ComponentScan(basePackageClasses = { HomeController.class }) // array de classes de onde o SpringMVC pode extrair os
																// pacotes nos quais ele pode encontrar os controllers
																// automaticamente
public class AppWebConfiguration {

	@Bean // @Bean - retorno da chamada deste metódo possa ser gerenciada pelo SpringMVC
	// configurar o projeto para que o SpringMVC consiga encontrar as views
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/"); // pasta aonde q estao as views
		resolver.setSuffix(".jsp"); // extensao do arquivo da view. nao precisa digita-lo no controller
		return resolver;
	}

}
