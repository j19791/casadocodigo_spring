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

	public Produto find(int id) {
		// return manager.find(Produto.class, id); ira dar erro de
		// LazyInitializationException
		// tratando LazyInitializationException: indica q ao carregar o produto, os
		// preços não foram carregados juntos. Ou seja, tentamos exibir os preços sem
		// carregá-los do bd. Isto acontece porque nosso ProdutoDAO no método find só
		// busca o produto, sem se relacionar com seus preços.
		// query planejada: produtos x precos
		return manager.createQuery("select distinct(p) from Produto p join fetch p.precos precos where p.id = :id",
				Produto.class).setParameter("id", id).getSingleResult();
	}
}
