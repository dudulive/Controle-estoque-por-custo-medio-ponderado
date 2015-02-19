package org.projetoIntegrador.model.produto;

import java.util.List;

import org.projetoIntegrador.util.DAOFactory;




public class ProdutoRN {
	
private ProdutoDAO produtoDAO;
	
	public ProdutoRN() {
	   this.produtoDAO = DAOFactory.criarProdutoDAO();
	}
	
	public List<Produto> selectAll(){
		return this.produtoDAO.selectAll();
	}
	
	public void insert(Produto produto){
		Long id = produto.getCodigo();
		if (id == null || id == 0){
			this.produtoDAO.insert(produto);
		} else {
			this.produtoDAO.update(produto);
		}
	}
	
	public void delete(Produto produto) {
		produtoDAO.delete(produto);
	}

}
