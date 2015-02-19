package org.projetoIntegrador.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.projetoIntegrador.model.saldo.Saldo;
import org.projetoIntegrador.model.saldo.SaldoRN;

@ManagedBean(name = "saldoBean")
@SessionScoped
public class SaldoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Saldo> listSaidas;
	private List<Saldo> listEntradas;
	private List<Saldo> listSaldos;
	private List<Saldo> listHistoricoProduto;
	private Long codigo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public List<Saldo> getListSaidas() {
		SaldoRN saldoRN = new SaldoRN();
		listSaidas = saldoRN.selectAll("saida");
		return listSaidas;
	}

	public List<Saldo> getListEntradas() {
		SaldoRN saldoRN = new SaldoRN();
		listEntradas = saldoRN.selectAll("entrada");
		return listEntradas;
	}

	public List<Saldo> getListSaldos() {
		SaldoRN saldoRN = new SaldoRN();
		listSaldos = saldoRN.selectAll();
		return listSaldos;
	}

	public String actionListHistoricoProduto() {
		SaldoRN saldoRN = new SaldoRN();
		this.listHistoricoProduto = saldoRN.selectAllMovimentos(codigo);
		return "lista_saldo_produto";
	}

	public List<Saldo> getListHistoricoProduto() {
		return listHistoricoProduto;
	}

	public void setListHistoricoProduto(List<Saldo> listHistoricoProduto) {
		this.listHistoricoProduto = listHistoricoProduto;
	}

}
