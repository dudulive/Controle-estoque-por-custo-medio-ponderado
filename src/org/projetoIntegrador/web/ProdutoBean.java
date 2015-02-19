package org.projetoIntegrador.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.projetoIntegrador.model.produto.Produto;
import org.projetoIntegrador.model.produto.ProdutoRN;


@ManagedBean(name = "produtoBean")
@RequestScoped
public class ProdutoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Produto> listProdutos;
	private Produto produto = new Produto();
	private String destinoSalvar;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getListProdutos() {
		if (this.listProdutos == null) {
			ProdutoRN produtoRN = new ProdutoRN();
			this.listProdutos = produtoRN.selectAll();
		}
		return listProdutos;
	}

	public String actionSalvar() {
		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.insert(produto);
		return destinoSalvar;
	}

	public String actionExcluir() {
		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.delete(produto);
		listProdutos = null;
		return null;
	}

	public String actionEditar() {
		destinoSalvar = "/produto/lista_produto";
		return "/produto/form_produto";
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}
	
	public String actionManterProduto(){
		destinoSalvar = "produtoSucesso";
		this.produto = new Produto();
		return "form_produto";
	}
}
