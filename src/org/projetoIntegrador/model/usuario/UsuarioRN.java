package org.projetoIntegrador.model.usuario;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.projetoIntegrador.util.DAOFactory;

public class UsuarioRN {

	private IUsuarioDAO usuarioDAO;

	public UsuarioRN() {
		this.usuarioDAO = DAOFactory.criaUsuarioDAO();
	}

	public void salvar(Usuario usuario) {
		Long id = usuario.getId();
		if (id == null || id == 0){
			usuarioDAO.salvar(usuario);
		} else {
			usuarioDAO.atualizar(usuario);
		}
	}
	
	public List<Usuario> findByAll(){
		return usuarioDAO.findByAll();
	}
	
	public void excluir(Usuario usuario) {
	    usuarioDAO.excluir(usuario);
	}
	
	public Usuario findByNameAndPassword(Usuario usuario) {
		return usuarioDAO.findByNameAndPassword(usuario.getLogin(), convertStringToMd5(usuario.getSenha()));
	}
	
	private String convertStringToMd5(String valor) {
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("MD5");
			byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));
			StringBuffer sb = new StringBuffer();
			for (byte b : valorMD5) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,3));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
}
