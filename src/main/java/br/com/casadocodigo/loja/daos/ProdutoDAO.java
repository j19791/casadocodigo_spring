package br.com.casadocodigo.loja.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Produto;

@Repository // devemos definir que o ProdutoDAO será gerenciado pelo Spring
// classe de acesso a dados responsável por manipular os dados dos produtos :
// realizar a persistencia
public class ProdutoDAO {

	@PersistenceContext // fornecedido pelo Spring
	private EntityManager manager; // gerenciador de entidades

	public void gravar(Produto produto) {
		manager.persist(produto);
	}
}
