package org.projetoIntegrador.model.movimento;

import java.util.Date;

import org.projetoIntegrador.model.produto.Produto;

public class Movimento {
	
	private Long id;
	private Date data;
	private Double precoItem;
	private Integer qtdItem;
	@SuppressWarnings("unused")
	private Double precoTotal;
	private Produto produto = new Produto();
	private String tipo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Double getPrecoItem() {
		return precoItem;
	}
	public void setPrecoItem(Double precoItem) {
		this.precoItem = precoItem;
	}
	public Integer getQtdItem() {
		return qtdItem;
	}
	public void setQtdItem(Integer qtdItem) {
		this.qtdItem = qtdItem;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Double getPrecoTotal() {
		return this.precoItem * this.qtdItem;
	}
	public void setPrecoTotal(Double precoTotal) {
		this.precoTotal = precoTotal;
	}
	
	

}
