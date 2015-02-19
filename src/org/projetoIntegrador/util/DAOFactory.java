package org.projetoIntegrador.util;

import org.projetoIntegrador.model.movimento.MovimentoDAO;
import org.projetoIntegrador.model.produto.ProdutoDAO;
import org.projetoIntegrador.model.saldo.SaldoDAO;
import org.projetoIntegrador.model.usuario.UsuarioDAO;

public class DAOFactory {
	
	public static UsuarioDAO criaUsuarioDAO(){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.setConexao(ConnectionFactory.getConexao());
		return usuarioDAO;
	}
	
	public static ProdutoDAO criarProdutoDAO() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
	    produtoDAO.setConnection(ConnectionFactory.getConexao());
		return produtoDAO;
	}
	
	public static MovimentoDAO criarMovimentoDAO() {
		MovimentoDAO movimentoDAO = new MovimentoDAO();
	    movimentoDAO.setConnection(ConnectionFactory.getConexao());
		return movimentoDAO;
	}
	
	public static SaldoDAO criarSaldoDAO() {
		SaldoDAO saldoDAO = new SaldoDAO();
		saldoDAO.setConnection(ConnectionFactory.getConexao());
		return saldoDAO;
	}


}
