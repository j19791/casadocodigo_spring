package br.com.casadocodigo.loja.models;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component // p/ q o Spring instancie o carrinhoComprar qdo necessário. transformando nossa
			// classe em um Bean do Spring
// Por padrão, estamos também configurando que este objeto será Singleton: unico
// carrinho de compras p/ todos os usuários
@Scope(value = WebApplicationContext.SCOPE_SESSION) // Qdo se faz necessário q um recurso seja individual, ( único para
													// cada usuário) definimos os recursos com o escopo de
													// sessão: criado a partir do momento em que o usuário entra
													// em uma determinada aplicação e segue até o encerramento da mesma
													// - ou ao fechar do navegador em alguns casos.
public class CarrinhoCompras {

	// o selecionarmos determinado produto, podemos também especificar a quantidade.
	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();

	// ao adicionarmos um produto, seja verificado se o produto já existe em nossa
	// lista. Caso exista, somaremos +1 na quantidade, caso não exista, adicionamos
	// o produto selecionado.
	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidade(item) + 1); // Ele deve retornar o número de vezes em que o produto foi encontrado
													// na lista e somar 1 a essa quantidade.
	}

	private int getQuantidade(CarrinhoItem item) {
		if (!itens.containsKey(item)) {// Caso o item não exista na lista, colocamos o item e retornamos o valor 0
			itens.put(item, 0);
		}
		return itens.get(item);// caso o item já exista, retornamos apenas o valor que representa a
		// quantidade de vezes que o produto foi adicionado na lista.
	}

	public int getQuantidade() {// irá iterar entre todos os itens do carrinho e contar quantos produtos estão
								// na lista de itens.
		return itens.values().stream().reduce(0, (proximo, acumulador) -> (proximo + acumulador));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itens == null) ? 0 : itens.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarrinhoCompras other = (CarrinhoCompras) obj;
		if (itens == null) {
			if (other.itens != null)
				return false;
		} else if (!itens.equals(other.itens))
			return false;
		return true;
	}
}
