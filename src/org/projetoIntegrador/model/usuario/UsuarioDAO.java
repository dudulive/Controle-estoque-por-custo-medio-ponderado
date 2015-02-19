package org.projetoIntegrador.model.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.projetoIntegrador.util.ConnectionFactory;

public class UsuarioDAO implements IUsuarioDAO {

	private Connection conexao;
	private PreparedStatement ps;
	private ResultSet rs;

	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void salvar(Usuario usuario) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO usuario (nome, idioma, login, senha ) "
				+ " VALUES (?, ?, ?, MD5(?))";
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getIdioma());
			ps.setString(3, usuario.getLogin());
			ps.setString(4, usuario.getSenha());
			ps.execute();
		} catch (Exception e) {
			System.err.println("Erro na inserção da linha. " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(conexao, ps);
		}
	}

	@Override
	public void atualizar(Usuario usuario) {
		// TODO Auto-generated method stub
		String sql = "UPDATE usuario SET nome = ?, idioma = ?, login = ?, senha = MD5(?), "
				+ " WHERE id = ?";
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(3, usuario.getIdioma());
			ps.setString(4, usuario.getLogin());
			ps.setString(5, usuario.getSenha());
			ps.setLong(9, usuario.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println("Erro no método atualizar. " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(conexao, ps);
		}
	}

	@Override
	public void excluir(Usuario usuario) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM usuario WHERE id = ?";
		try {
			ps = conexao.prepareStatement(sql);
			ps.setLong(1, usuario.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println("Erro no método excluir. " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(conexao, ps);
		}
	}

	@Override
	public Usuario findByID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario findByNameAndPassword(String login, String password) {
		Usuario usuario = null;
		String sql = "SELECT id, nome, idioma, "
				+ " login, senha FROM usuario WHERE login = ? AND senha = ?";
		try{
			ps = conexao.prepareStatement(sql);
			ps.setString(1, login);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getLong("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setIdioma(rs.getString("idioma"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
	
			}
		}catch (Exception e) {
			System.err.println("Erro no método findByNameAndPassword. " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(conexao, ps, rs);
		}
		return usuario;
	}

	@Override
	public List<Usuario> findByAll() {
		List<Usuario> lsUsuarios = null;
		String sql = "SELECT id, nome, idioma, "
				+ " login, senha, FROM usuario";
		try {
			ps = conexao.prepareStatement(sql);
			rs = ps.executeQuery();
			lsUsuarios = new ArrayList<Usuario>();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getLong("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setIdioma(rs.getString("idioma"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				lsUsuarios.add(usuario);
			}
		} catch (Exception e) {
			System.err.println("Erro no método findByAll. " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(conexao, ps, rs);
		}
		return lsUsuarios;
	}

}
