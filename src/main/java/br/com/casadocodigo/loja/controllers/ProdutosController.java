package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Controller
public class ProdutosController {

	@Autowired // O Spring cria o ProdutoDAO
	private ProdutoDAO produtoDao;

	@RequestMapping("/produtos/form")
	/*
	 * public String form() { System.out.println("entrou"); return "produtos/form";
	 * }
	 */
	public ModelAndView form() {

		ModelAndView modelAndView = new ModelAndView("produtos/form");// para que o Spring entenda qual o arquivo que
																		// ele deverá retornar ao navegador.
		// para fazer o binding dos tipos de preço
		modelAndView.addObject("tipos", TipoPreco.values());

		return modelAndView;
	}

	@RequestMapping("/produtos")
	public String gravar(Produto produto) {
		// O SpringMVC sozinho verifica a assinatura do nosso método e faz um bind dos
		// parâmetros do método com os names do formulário.
		System.out.println(produto);

		produtoDao.gravar(produto);

		return "/produtos/ok";

	}

}
