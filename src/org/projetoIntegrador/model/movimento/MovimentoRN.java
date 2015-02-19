package org.projetoIntegrador.model.movimento;

import java.util.Date;
import java.util.List;

import org.projetoIntegrador.model.saldo.Saldo;
import org.projetoIntegrador.model.saldo.SaldoRN;
import org.projetoIntegrador.util.DAOFactory;

public class MovimentoRN {

	private MovimentoDAO movimentoDAO;

	public MovimentoRN() {
		this.movimentoDAO = DAOFactory.criarMovimentoDAO();
	}

	public void insert(Movimento movimento) {
		Long id = movimento.getId();
		if (id == null || id == 0) {
			if(movimento.getTipo().equals("saida")){
				SaldoRN saldoRN = new SaldoRN();
				Saldo saldo = saldoRN.saldoProduto(movimento.getProduto().getCodigo()); 
				movimento.setPrecoItem(saldo.getPrecoMedio());
			}
			this.movimentoDAO.insert(movimento);
		}
	}
	
	public List<Movimento> selectAllEntrada() {
		return this.movimentoDAO.selectAllEntrada();
	}
	
	public List<Movimento> selectAllSaida() {
		return this.movimentoDAO.selectAllSaida();
	}
	
	public Date dataMaxEntrada(Long produto){
		return this.movimentoDAO.maxDateEntrada(produto);
	}
}
