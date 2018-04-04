package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public ModelAndView gravar(Produto produto, RedirectAttributes redirectAttributes) {
		// O SpringMVC sozinho verifica a assinatura do nosso método e faz um bind dos
		// parâmetros do método com os names do formulário.
		System.out.println(produto);

		produtoDao.gravar(produto);

		// return "/produtos/ok"; // alterando para retornar p/ lista de produtos após o
		// cadastro
		// return "produtos"; //erro: o spring vai mandar para a pagina produtos.jsp q
		// não existe
		// return listar(); //bug do f5 - navegador nos questiona se queremos resubmeter
		// o formulário ao clciar f5
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");// recurso do Spring que nos
																							// permite enviar
																							// informações entre
																							// requisições. Usaremos
																							// então esse objeto para
																							// adicionar um atributo do
																							// tipo Flash , passando
																							// assim a uma chave /valor.
																							// Eles só duram até a
																							// próxima requisição, ou
																							// seja, transportam
																							// informações de uma
																							// requisição para a outra e
																							// então, deixam de existir.
		return new ModelAndView("redirect:produtos"); // usaremos um recurso chamado de redirect, que passa um status
														// para o navegador carregar uma outra página e esquecer dos
														// dados da requisição anterior.
														// Always redirect after post
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
