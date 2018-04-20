package br.com.casadocodigo.loja.models;

//CarrinhoItem: forma de relacionar o produto com seu preço

public class CarrinhoItem {

	private Produto produto;
	private TipoPreco tipoPreco;

	public TipoPreco getTipoPreco() {
		return tipoPreco;
	}

	public CarrinhoItem(Produto produto, TipoPreco tipoPreco) {
		super();
		this.tipoPreco = tipoPreco;
		this.produto = produto;
	}

	public void setTipoPreco(TipoPreco tipoPreco) {
		this.tipoPreco = tipoPreco;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
