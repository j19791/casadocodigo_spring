package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Controller
@RequestMapping("produtos") // Para que não precisemos ficar passando /produtos em todos os métodos do
							// controller,
public class ProdutosController {

	@Autowired // O Spring cria o ProdutoDAO
	private ProdutoDAO produtoDao;

	// @RequestMapping("/produtos/form")
	@RequestMapping("/form")
	/*
	 * public String form() { System.out.println("entrou"); return "produtos/form";
	 * }
	 */
	// Quando utilizamos o ModelAndView, além retornar uma página, temos a
	// possibilidade de enviar objetos de qualquer classe para essas páginas. Em
	// outras palavras, dessa forma somos capazes de exibir, por exemplo, as
	// informações das nossas classes modelos.
	// envie a String "tipos" e os valores do enum TipoPreco a partir do método
	// values()
	public ModelAndView form() {

		ModelAndView modelAndView = new ModelAndView("produtos/form");// para que o Spring entenda qual o arquivo que
																		// ele deverá retornar ao navegador.
		// para fazer o binding dos tipos de preço
		modelAndView.addObject("tipos", TipoPreco.values());

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	// @RequestMapping(value = "/produtos", method = RequestMethod.POST) //
	// method=RequestMethod: diferenciando as rotas
	// pelos métodos usados pelo protocolo HTTP.
	// post utilizado para envio de forms
	public String gravar(Produto produto) {
		// O SpringMVC sozinho verifica a assinatura do nosso método e faz um bind dos
		// parâmetros do método com os names do formulário.
		System.out.println(produto);

		produtoDao.gravar(produto);

		return "/produtos/ok";

	}

	@RequestMapping(method = RequestMethod.GET)
	// @RequestMapping(value = "/produtos", method = RequestMethod.GET) // get :
	// verbo http utilizado qdo acessa url, clica
	// em links
	public ModelAndView listar() { // use o método listar do ProdutoDAO e retornar essa lista de produtos para a
									// view.
		List<Produto> produtos = produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView("/produtos/lista");// ModelAndView para anexar objetos que serão
																		// usados em nossa view e retornando a lista.
		modelAndView.addObject("produtos", produtos);// ModelAndView é uma classe do Spring que faz um relacionamento de
														// um modelo (model) com uma visualização (view) . Podendo
														// disponibilizar um objeto qualquer para a view
		return modelAndView;
	}

}
