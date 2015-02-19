package org.projetoIntegrador.web;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.projetoIntegrador.model.movimento.Movimento;
import org.projetoIntegrador.model.movimento.MovimentoRN;
import org.projetoIntegrador.model.saldo.Saldo;
import org.projetoIntegrador.model.saldo.SaldoRN;

@ManagedBean(name = "movimentoBean")
@RequestScoped
public class MovimentoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Movimento> listSaidas;
	private List<Movimento> listEntradas;
	private Movimento movimento = new Movimento();
	private String destinoSalvar;
	private Double precoAjax;
	
	public Movimento getmovimento() {
		return movimento;
	}
	public void setMovimento(Movimento movimento) {
		this.movimento = movimento;
	}
	public String getDestinoSalvar() {
		return destinoSalvar;
	}
	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}
	
	public String actionSalvar() {
		MovimentoRN movimentoRN = new MovimentoRN();
		Date data = movimentoRN.dataMaxEntrada(movimento.getProduto().getCodigo());
		if(movimento.getTipo().equals("saida")){
			SaldoRN saldoRN = new SaldoRN();
			Saldo saldo = saldoRN.saldoProduto(movimento.getProduto().getCodigo());
			if(movimento.getQtdItem() > saldo.getSomaQtdTotal()){
				FacesMessage message = new FacesMessage("Saldo insuficiente!");
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, message);
				return null;
			} else if(movimento.getData().before(data)){
				FacesMessage message = new FacesMessage("Data de Saida menor que a ultima data de Entrada!");
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, message);
				return null;
			}
		}
		movimentoRN.insert(movimento);
		return destinoSalvar;
	}
	
	public String actionManterEntrada(){
		destinoSalvar = "entradaSucesso";
		this.movimento = new Movimento();
		this.movimento.setTipo("entrada");
		return "form_entrada";
	}
	
	public String actionManterSaida(){
		destinoSalvar = "saidaSucesso";
		this.movimento = new Movimento();
		this.movimento.setTipo("saida");
		return "form_saida";
	}
	
	public List<Movimento> getlistEntradas() {
		if (this.listEntradas == null) {
			MovimentoRN movimentoRN = new MovimentoRN();
			this.listEntradas = movimentoRN.selectAllEntrada();
		}
		return listEntradas;
	}
	
	public List<Movimento> getlistSaidas() {
		if (this.listSaidas == null) {
			MovimentoRN movimentoRN = new MovimentoRN();
			this.listSaidas = movimentoRN.selectAllSaida();
		}
		return listSaidas;
	}
	public Double getPrecoAjax() {
		SaldoRN saldoRN = new SaldoRN();
		Saldo saldo = saldoRN.saldoProduto(movimento.getProduto().getCodigo());
		precoAjax = saldo.getPrecoMedio();
		return precoAjax;
	}
	

}
