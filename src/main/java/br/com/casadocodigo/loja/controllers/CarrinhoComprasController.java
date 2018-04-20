package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.CarrinhoItem;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoComprasController {

	@Autowired
	private ProdutoDAO produtoDao;

	@Autowired
	private CarrinhoCompras carrinhoCompras;

	// adicao de produtos ao carrinho de compras
	@RequestMapping("/add")
	public ModelAndView add(Integer produtoId, // apenas o id esta sendo enviado (hidden) na pagian de detalhes
			TipoPreco tipo) {
		ModelAndView modelAndView = new ModelAndView("redirect:/produtos");// método redirecione o usuário para a
																			// listagem de produtos.

		CarrinhoItem carrinhoItem = this.criaItem(produtoId, tipo);

		carrinhoCompras.add(carrinhoItem);

		return modelAndView;
	}

	private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipo) {
		Produto produto = produtoDao.find(produtoId);// Busque o produto pelo id no banco de dados
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipo);// relacione o produto com o preço selecionado pelo
																	// usuário.
		return carrinhoItem;
	}

}
