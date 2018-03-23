package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@Controller
public class ProdutosController {

	@Autowired // O Spring cria o ProdutoDAO
	private ProdutoDAO produtoDao;

	@RequestMapping("/produtos/form")
	public String form() {
		System.out.println("entrou");
		return "produtos/form";
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
