package br.com.casadocodigo.loja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.casadocodigo.loja.models.Produto;

@Controller
public class ProdutosController {

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
		return "/produtos/ok";

	}

}
