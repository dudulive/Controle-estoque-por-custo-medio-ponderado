package org.projetoIntegrador.model.saldo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.projetoIntegrador.model.movimento.Movimento;
import org.projetoIntegrador.model.produto.Produto;
import org.projetoIntegrador.util.ConnectionFactory;

public class SaldoDAO implements ISaldo {
	
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Saldo> findBySaldo(Long produto) {
		List<Saldo> lsSaldos = null;
		String sql = "SELECT SUM(qtdItem),SUM(precoTotal),movimento.tipo "
				+ " FROM movimento"
				+ " INNER JOIN produto ON produto.codigo = movimento.produto "
				+ " WHERE movimento.produto = ? "
				+ " GROUP BY movimento.tipo";
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, produto);
			rs = ps.executeQuery();
			lsSaldos = new ArrayList<Saldo>();
			while (rs.next()) {
				Saldo saldo = new Saldo();
				saldo.setSomaQtdTotal(rs.getInt(1));
			    saldo.setSomaPrecoTotal(rs.getDouble(2));
			    saldo.setTipo(rs.getString(3));
			    lsSaldos.add(saldo);
			}
		} catch (Exception e) {
			System.err.println("Erro no método findBySaldo de Saldo");
			System.err.println(e.getMessage());
		} finally {
		   ConnectionFactory.fecharConexao(connection, ps, rs);
		}
		return lsSaldos;
	}

	@Override
	public List<Saldo> selectAll(String movimento) {
		List<Saldo> lsSaldos = null;
		String sql = "SELECT produto.codigo,produto.nome,"
				+ " produto.descricao,"
				+ " SUM(qtdItem),SUM(precoTotal),movimento.tipo"
				+ " FROM produto "
				+ " LEFT JOIN movimento ON produto.codigo = movimento.produto"
				+ " WHERE tipo =  ? "
				+ " GROUP BY produto.codigo,produto.nome,produto.descricao,movimento.tipo"
				+ " ORDER BY produto.codigo,movimento.tipo";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, movimento);
			rs = ps.executeQuery();
			lsSaldos = new ArrayList<Saldo>();
			while (rs.next()) {
				Saldo saldo = new Saldo();
				Produto produto = new Produto();
				produto.setCodigo(rs.getLong(1));
				produto.setNome(rs.getString(2));
				produto.setDescricao(rs.getString(3));
				saldo.setProduto(produto);
				saldo.setSomaQtdTotal(rs.getInt(4));
				saldo.setSomaPrecoTotal(rs.getDouble(5));
				saldo.setTipo(rs.getString(6));
				lsSaldos.add(saldo);
			}
		} catch (Exception e) {
			System.err.println("Erro no método select de lsSaldos");
			System.err.println(e.getMessage());
		} finally {
		    ConnectionFactory.fecharConexao(connection, ps, rs);
		}
		return lsSaldos;
	}

	@Override
	public List<Saldo> selectAllMovimentos(Long codigo) {
		List<Saldo> lsSaldos = null;
		String sql = "SELECT produto.codigo,produto.nome,produto.descricao,movimento.data,"
				+ " movimento.qtdItem,movimento.precoItem,movimento.precoTotal,movimento.tipo "
				+ " FROM movimento "
				+ " INNER JOIN produto ON produto.codigo = movimento.produto "
				+ " WHERE produto.codigo = ? "
				+ " GROUP BY produto.codigo,produto.nome,produto.descricao, "
				+ " movimento.data,movimento.qtdItem,movimento.precoTotal,movimento.tipo,movimento.precoItem  "
				+ " ORDER BY movimento.data";
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, codigo);
			rs = ps.executeQuery();
			lsSaldos = new ArrayList<Saldo>();
			while (rs.next()) {
				Saldo saldo = new Saldo();
				Produto produto = new Produto();
				produto.setCodigo(rs.getLong(1));
				produto.setNome(rs.getString(2));
				produto.setDescricao(rs.getString(3));
				saldo.setProduto(produto);
				Movimento movimento = new Movimento();
				movimento.setData(rs.getDate(4));
				movimento.setQtdItem(rs.getInt(5));
				movimento.setPrecoItem(rs.getDouble(6));
				movimento.setPrecoTotal(rs.getDouble(7));
				movimento.setTipo(rs.getString(8));
				saldo.setMovimento(movimento);
				lsSaldos.add(saldo);
			}
		} catch (Exception e) {
			System.err.println("Erro no método selectAllMovimentos de lsSaldos");
			System.err.println(e.getMessage());
		} finally {
		    ConnectionFactory.fecharConexao(connection, ps, rs);
		}
		return lsSaldos;
	}

	@Override
	public List<Saldo> selectAll() {
		List<Saldo> lsSaldos = null;
		
		String sql = "SELECT produto.codigo,produto.nome, "
				+ " produto.descricao, "
				+ " SUM(qtdItem),SUM(precoTotal),movimento.tipo  "
				+ " FROM produto "
				+ " INNER JOIN movimento ON produto.codigo = movimento.produto "
				+ " GROUP BY produto.codigo,produto.nome,produto.descricao,movimento.tipo "
				+ " ORDER BY produto.codigo,movimento.tipo";
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			lsSaldos = new ArrayList<Saldo>();
			while (rs.next()) {
				Saldo saldo = new Saldo();
				Produto produto = new Produto();
				produto.setCodigo(rs.getLong(1));
				produto.setNome(rs.getString(2));
				produto.setDescricao(rs.getString(3));
				saldo.setProduto(produto);
				saldo.setSomaQtdTotal(rs.getInt(4));
				saldo.setSomaPrecoTotal(rs.getDouble(5));
				saldo.setTipo(rs.getString(6));
				lsSaldos.add(saldo);
			}
		} catch (Exception e) {
			System.err.println("Erro no método selectAll de lsSaldos");
			System.err.println(e.getMessage());
		} finally {
		    ConnectionFactory.fecharConexao(connection, ps, rs);
		}
		return lsSaldos;
	}

	
}
