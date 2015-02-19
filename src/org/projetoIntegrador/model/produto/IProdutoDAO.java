package org.projetoIntegrador.model.produto;

import java.util.List;

public interface IProdutoDAO {

	public void insert(Produto produto);
	public void update(Produto produto);
	public void delete(Produto produto);
	public List<Produto> selectAll();
}
