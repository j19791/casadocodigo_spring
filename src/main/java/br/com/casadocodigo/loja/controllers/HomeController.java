package br.com.casadocodigo.loja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // classe que vai atender as requisições do usuário
public class HomeController {

	@RequestMapping("/")
	public String index() {// metodo responsável por atender as requisições que chegam na página inicial: o
							// endereço raiz ("/")
		System.out.println("Entrando na home da CDC");
		return "home"; // view que o Spring espera receber
	}
}
