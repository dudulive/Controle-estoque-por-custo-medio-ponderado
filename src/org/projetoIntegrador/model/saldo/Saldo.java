package org.projetoIntegrador.model.saldo;

import java.util.Date;

import org.projetoIntegrador.model.movimento.Movimento;
import org.projetoIntegrador.model.produto.Produto;


public class Saldo {
	
	private Produto produto = new Produto();
	private String tipo;
	private Integer somaQtdTotal;
    private Double somaPrecoTotal;
    private Date data;
    @SuppressWarnings("unused")
	private Double precoMedio;
    private Double cmv;
    private Movimento movimento = new Movimento();
    
	public Double getCmv() {
		return cmv;
	}
	public void setCmv(Double cmv) {
		this.cmv = cmv;
	}
	public Integer getSomaQtdTotal() {
		return somaQtdTotal;
	}
	public void setSomaQtdTotal(Integer somaQtdTotal) {
		this.somaQtdTotal = somaQtdTotal;
	}
	public void setPrecoMedio(Double precoMedio) {
		this.precoMedio = precoMedio;
	}
	public Double getSomaPrecoTotal() {
		return somaPrecoTotal;
	}
	public void setSomaPrecoTotal(Double somaPrecoTotal) {
		this.somaPrecoTotal = somaPrecoTotal;
	}
	public Double getPrecoMedio() {
		return this.somaPrecoTotal / this.somaQtdTotal;
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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Movimento getMovimento() {
		return movimento;
	}
	public void setMovimento(Movimento movimento) {
		this.movimento = movimento;
	}
}
