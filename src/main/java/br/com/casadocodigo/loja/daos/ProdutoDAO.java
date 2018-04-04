package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Produto;

@Repository // devemos definir que o ProdutoDAO será gerenciado pelo Spring
// classe de acesso a dados responsável por manipular os dados dos produtos :
// realizar a persistencia
@Transactional // o spring vai cuidar da transacao do dao
public class ProdutoDAO {

	@PersistenceContext // fornecedido pelo Spring
	private EntityManager manager; // gerenciador de entidades

	public void gravar(Produto produto) {
		manager.persist(produto);
	}

	public List<Produto> listar() {
		return manager.createQuery("select p from Produto p", Produto.class).getResultList();// criar uma lista com os
																								// resultados da
																								// consulta ao banco de
																								// dados.
	}
}
