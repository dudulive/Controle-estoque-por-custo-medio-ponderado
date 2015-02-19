package org.projetoIntegrador.model.usuario;

import java.util.List;

public interface IUsuarioDAO {

	public void salvar(Usuario usuario);
	public 	void atualizar(Usuario usuario);
	public void excluir (Usuario usuario);
	public Usuario findByID (Long id);
	public Usuario findByNameAndPassword(String login, String password);
	public List<Usuario> findByAll();
}
