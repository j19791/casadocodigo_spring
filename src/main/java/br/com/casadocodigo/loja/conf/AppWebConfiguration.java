package br.com.casadocodigo.loja.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.CarrinhoCompras;

@EnableWebMvc // anotamos que precisamos usar o recurso de Web MVC do SpringMVC
@ComponentScan(basePackageClasses = { HomeController.class, ProdutoDAO.class, FileSaver.class, CarrinhoCompras.class }) // array
																														// de
																														// classes
																														// de
// onde o SpringMVC
// pode extrair os
// pacotes nos quais ele pode encontrar os controllers
// automaticamente
// onfigurar para que encontre nossos daos também.
public class AppWebConfiguration {

	@Bean // @Bean - retorno da chamada deste metódo possa ser gerenciada pelo SpringMVC
	// configurar o projeto para que o SpringMVC consiga encontrar as views
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/"); // pasta aonde q estao as views
		resolver.setSuffix(".jsp"); // extensao do arquivo da view. nao precisa digita-lo no controller

		//// resolver.setExposeContextBeansAsAttributes(true); deixa todos os beans
		//// expostos
		resolver.setExposedContextBeanNames("carrinhoCompras"); // Para que possamos acessar o Bean carrinhoCompras em
																// nossas views (no caso: detalhes)

		return resolver;
	}

	// precisamos configurar o Spring para que ele encontre esse nosso arquivos de
	// mensagens
	@Bean // para que o Spring possa reconhecer essa configuração.
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages");// terá o nome base dos nossos resources
		messageSource.setDefaultEncoding("UTF-8");// para evitar o problema de caracteres estranhos
		messageSource.setCacheSeconds(1);// para que o Spring recarregue o arquivo de tempos em tempos
		return messageSource;
	}

	// Imagine que em todo lugar que precisemos usar data precisaremos lembrar dessa
	// formatação @DateTimeFormat(pattern = "dd/MM/yyyy") Esse será o padrão de toda
	// aplicação.
	// faremos essa nova configuração para que não precisemos ficar repetindo essa
	// configuração de data em todos os atributos das outras classes do sistema
	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();// responsável
																										// pelo serviço
																										// de conversão
																										// de formato
		DateFormatterRegistrar formatterRegistrar = new DateFormatterRegistrar();// fará o registro do formato de data
																					// usado para a conversão
		formatterRegistrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		formatterRegistrar.registerFormatters(conversionService);

		return conversionService;
	}

	@Bean // configuração necessaria para receber arquivos. MultipartResolver se refere a
			// um resolvedor de dados multimidia. Quando temos texto e arquivos por exemplo.
			// Os arquivos podem ser: imagem, PDF e outros. Este objeto é que identifica
			// cada um dos recursos enviados e nos fornece uma forma mais simples de
			// manipulalos.
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

}
