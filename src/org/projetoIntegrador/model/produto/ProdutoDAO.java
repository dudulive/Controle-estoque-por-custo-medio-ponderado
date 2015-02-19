package org.projetoIntegrador.model.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.projetoIntegrador.util.ConnectionFactory;


public class ProdutoDAO implements IProdutoDAO {

	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Produto produto) {
		String sql = "INSERT INTO produto (nome, descricao) "
				+ " VALUES (?, ?)";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, produto.getNome());
			ps.setString(2, produto.getDescricao());
			ps.execute();
		} catch (Exception e) {
			System.err.println("Erro na inserção da linha. " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(connection, ps);
		}
	}

	@Override
	public void update(Produto produto) {
		String sql = "UPDATE produto SET nome = ?, descricao = ? "
				+ " WHERE codigo = ?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, produto.getNome());
			ps.setString(2, produto.getDescricao());
			ps.setLong(3, produto.getCodigo());
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println("Erro no método atualizar. " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(connection, ps);
		}
	}

	@Override
	public void delete(Produto produto) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM produto WHERE codigo = ?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, produto.getCodigo());
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println("Erro no método delete. " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(connection, ps);
		}
	}

	@Override
	public List<Produto> selectAll() {
		List<Produto> lsProdutos = null;
		String sql = "SELECT produto.codigo, produto.nome, produto.descricao"
				+ " FROM produto ORDER BY produto.codigo";

		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			lsProdutos = new ArrayList<Produto>();
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setCodigo(rs.getLong("codigo"));
				produto.setNome(rs.getString("nome"));
				produto.setDescricao(rs.getString("descricao"));
				lsProdutos.add(produto);
			}
		} catch (Exception e) {
			System.err.println("Erro no método selectAll de lsProdutos");
			System.err.println(e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(connection, ps, rs);
		}
		return lsProdutos;
	}

}
