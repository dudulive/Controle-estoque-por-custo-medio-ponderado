package org.projetoIntegrador.model.movimento;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.projetoIntegrador.model.produto.Produto;
import org.projetoIntegrador.util.ConnectionFactory;

public class MovimentoDAO implements IMovimento {
	
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Movimento movimento) {
		String sql = "INSERT INTO movimento (data, precoItem, qtdItem, precoTotal, produto, tipo) "
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		try {
			ps = connection.prepareStatement(sql);
			ps.setDate(1, new Date(movimento.getData().getTime()));
			ps.setDouble(2, movimento.getPrecoItem());
			ps.setInt(3, movimento.getQtdItem());
			ps.setDouble(4, movimento.getPrecoTotal());
			ps.setLong(5, movimento.getProduto().getCodigo());
			ps.setString(6, movimento.getTipo());
			ps.execute();
		} catch (Exception e) {
			System.err.println("Erro na inserção da linha. " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(connection, ps);
		}
	}	

	@Override
	public List<Movimento> selectAllEntrada() {
		List<Movimento> lsMovimentos = null;
		String sql = "SELECT movimento.id, movimento.data, movimento.precoItem, "
				+ " movimento.qtdItem, movimento.precoTotal, movimento.tipo, produto.codigo, produto.nome, produto.descricao "
				+ " FROM movimento, produto  WHERE produto.codigo = movimento.produto"
				+ " AND movimento.tipo = 'entrada' "
				+ " ORDER BY movimento.id";
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			lsMovimentos = new ArrayList<Movimento>();
			while (rs.next()) {
				Movimento movimento = new Movimento();
				Produto produto = new Produto();
				movimento.setId(rs.getLong("id"));
				movimento.setData(rs.getDate("data"));
				movimento.setPrecoItem(rs.getDouble("precoItem"));
				movimento.setQtdItem(rs.getInt("qtdItem"));
				movimento.setPrecoTotal(rs.getDouble("precoTotal"));
				movimento.setTipo(rs.getString("tipo"));
				produto.setCodigo(rs.getLong("codigo"));
				produto.setNome(rs.getString("nome"));
				produto.setDescricao(rs.getString("descricao"));
				movimento.setProduto(produto);
				lsMovimentos.add(movimento);
			}
		} catch (Exception e) {
			System.err.println("Erro no método selectAllEntradas de lsMovimentos");
			System.err.println(e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(connection, ps, rs);
		}
		return lsMovimentos;
	}
	
	@Override
	public List<Movimento> selectAllSaida() {
		List<Movimento> lsMovimentos = null;
		String sql = "SELECT movimento.id, movimento.data, movimento.precoItem, "
				+ " movimento.qtdItem, movimento.precoTotal, movimento.tipo, produto.codigo, produto.nome, produto.descricao "
				+ " FROM movimento, produto "
				+ " WHERE produto.codigo = movimento.produto"
				+ " AND movimento.tipo = 'saida' "
				+ " ORDER BY movimento.id ";
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			lsMovimentos = new ArrayList<Movimento>();
			while (rs.next()) {
				Movimento movimento = new Movimento();
				Produto produto = new Produto();
				movimento.setId(rs.getLong("id"));
				movimento.setData(rs.getDate("data"));
				movimento.setPrecoItem(rs.getDouble("precoItem"));
				movimento.setQtdItem(rs.getInt("qtdItem"));
				movimento.setPrecoTotal(rs.getDouble("precoTotal"));
				movimento.setTipo(rs.getString("tipo"));
				produto.setCodigo(rs.getLong("codigo"));
				produto.setNome(rs.getString("nome"));
				produto.setDescricao(rs.getString("descricao"));
				movimento.setProduto(produto);
				lsMovimentos.add(movimento);
			}
		} catch (Exception e) {
			System.err.println("Erro no método selectAllSaidas de lsMovimentos");
			System.err.println(e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(connection, ps, rs);
		}
		return lsMovimentos;
	}

	@Override
	public java.util.Date maxDateEntrada(Long produto) {
		Date data = null;
		String sql = " SELECT max(data) "
				+ "  FROM movimento"
				+ " INNER JOIN produto ON produto.codigo = movimento.produto "
				+ " WHERE tipo = 'entrada' "
				+ " AND movimento.produto = ? ";
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, produto);
			rs = ps.executeQuery();
			while (rs.next()) {
				data = rs.getDate(1);
			}
		} catch (Exception e) {
			System.err.println("Erro no método maxDateEntrada de Movimento");
			System.err.println(e.getMessage());
		} finally {
			//ConnectionFactory.fecharConexao(connection, ps, rs);
		}
		return data;
	}
}
