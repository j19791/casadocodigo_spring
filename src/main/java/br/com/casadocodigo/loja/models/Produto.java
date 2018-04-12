package br.com.casadocodigo.loja.models;

import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity // definir que o produto é uma entidade para ser usado pelo JPA
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // o hibernate obriga que toda entidade precisa de um id
	private int id;

	private String titulo;// String: objeto: pode receber null
	private String descricao;
	private int paginas; // int : tipo primitivo : não pode receber null

	// @DateTimeFormat(pattern = "dd/MM/yyyy") // definir padrao de data p/ o Spring
	// converter texto para Calendar
	// corretamente
	@DateTimeFormat // usando o padrao configurado no mvcConversionService do AppWebConfiguration
					// (padrão p/ toda aplicação - não precisa do pattern)
	private Calendar dataLancamento; // Existem duas principais classes para trabalhar com datas: Date e a Calendar.
										// Calendar é mais recente e simples de usar

	@ElementCollection // vai criar uma tabela chamada Produto_Preco com Produto_id, tipo, valor
	private List<Preco> precos; // precos podem ser de impresso, ebook ou ebook+impresso e além
	// list: objeto: pode recebr null

	private String sumarioPath; // guardar o arquivo de sumario nas pastas do sistema de arquivos do servidor.
								// Nele será guardado apenas o caminho (path) do arquivo.

	public String getSumarioPath() {
		return sumarioPath;
	}

	public void setSumarioPath(String sumarioPath) {
		this.sumarioPath = sumarioPath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	@Override
	public String toString() {
		return "Produto [titulo=" + titulo + ", descricao=" + descricao + ", paginas=" + paginas + "]";
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public List<Preco> getPrecos() {
		return precos;
	}

	public void setPrecos(List<Preco> precos) {
		this.precos = precos;
	}

	public Calendar getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

}
