package org.projetoIntegrador.web;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "internacionalizacaoBean")
@SessionScoped
public class InternacionalizacaoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String linguagem = "";
	private String pais = "";

	public String mudarIdioma() {
		if (!"".equals(pais)) {
			this.mudarLocalidade(new Locale(linguagem, pais));
		} else {
			this.mudarLocalidade(new Locale(linguagem));
		}
		return null;
	}

	private void mudarLocalidade(Locale locale) {
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	} 

	/** Metodos get e set */

	public String getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(String linguagem) {
		this.linguagem = linguagem;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
}
