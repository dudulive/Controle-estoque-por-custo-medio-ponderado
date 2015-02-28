package org.projetoIntegrador.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.projetoIntegrador.model.usuario.Usuario;
import org.projetoIntegrador.model.usuario.UsuarioRN;

@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	private String confirmaSenha;
	private String destinoSalvar;

	private List<Usuario> listUsuarios;

	public String actionEditar(){
		destinoSalvar = "/admin/principal";
		confirmaSenha = usuario.getSenha();
		return "/publico/usuario";
	}
	
	public String actionExcluir() {
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.excluir(usuario);
		listUsuarios = null;
		return null;
	}
	
	public List<Usuario> getListUsuarios() {
		if (listUsuarios == null) {
			UsuarioRN usuarioRN = new UsuarioRN();
			listUsuarios = usuarioRN.findByAll();
		}
		return listUsuarios;
	}

	public String actionNovo() {
		destinoSalvar = "usuarioSucesso";
		this.usuario = new Usuario();
		return "usuario";
	}

	public String actionSalvar() {
		if(usuario.getSenha().equals(this.getConfirmaSenha()) ){
			UsuarioRN usuarioRN = new UsuarioRN();
			usuarioRN.salvar(usuario);
			return destinoSalvar;
		}else{
			FacesMessage message = new FacesMessage("Senhas são Diferentes!");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, message);
			return null;
		}
		
	}
	
	
	public String actionLogin() {
		String destino = null;
		FacesContext context = FacesContext.getCurrentInstance();
		if(usuario.getLogin().length() > 0 && usuario.getSenha().length() > 0){
			UsuarioRN usuarioRN = new UsuarioRN();
			usuario = usuarioRN.findByNameAndPassword(usuario);
			if(usuario == null){
				FacesMessage message = new FacesMessage("Dados Inválidos");
				message.setSeverity ( FacesMessage.SEVERITY_ERROR) ;
				context.addMessage(null, message);
			}else if(usuario != null){
				ExternalContext ec = context.getExternalContext();
				HttpSession session =(HttpSession) ec.getSession(false);
				session.setAttribute("usuario", this.usuario);
				destino = "principal";
			}
		}
		return destino;
	}
	
	 public String actionLogout() { 
		  FacesContext context = FacesContext.getCurrentInstance();
		  ExternalContext ec = context.getExternalContext();
	      HttpSession sessao = (HttpSession) ec.getSession(true);  
	      sessao.removeAttribute("usuario"); 
	      return "login"; //AQUI EU PASSO O NOME DA MINHA TELA INICIAL.  
	 }

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}

}
