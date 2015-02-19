package org.projetoIntegrador.model.saldo;

import java.util.List;

import org.projetoIntegrador.util.DAOFactory;

public class SaldoRN {

	private SaldoDAO saldoDAO;

	public SaldoRN() {
		this.saldoDAO = DAOFactory.criarSaldoDAO();
	}

	public Saldo saldoProduto(Long produto) {
		List<Saldo> listSaldos = this.saldoDAO.findBySaldo(produto);
		Saldo saldo = new Saldo();
		int j = 0;
		for (int i = 0; i < listSaldos.size(); i++) {
			j = i - 1;
			if(listSaldos.size() == 1){
				saldo.setSomaPrecoTotal(listSaldos.get(i).getSomaPrecoTotal());
				saldo.setSomaQtdTotal(listSaldos.get(i).getSomaQtdTotal());
			}else if(listSaldos.size() != 0 && listSaldos.get(i).getTipo().equals("saida")){
				saldo.setSomaPrecoTotal(listSaldos.get(j).getSomaPrecoTotal() - listSaldos.get(i).getSomaPrecoTotal());
				saldo.setSomaQtdTotal(listSaldos.get(j).getSomaQtdTotal() - listSaldos.get(i).getSomaQtdTotal());
				saldo.setTipo("saldo");
			}	
		}
		return saldo;
	}
	
	public List<Saldo> selectAll(String movimento){
		return saldoDAO.selectAll(movimento);
	}
	
	public List<Saldo> selectAll(){
		List<Saldo> saldos = this.saldoDAO.selectAll();
		int j = 0 ; 
		for (int i = 0; i < saldos.size(); i++) {
			j = i - 1;
			if(i != 0  && saldos.get(i).getProduto().getCodigo() == saldos.get(j).getProduto().getCodigo()){
				saldos.get(i).setSomaQtdTotal(saldos.get(j).getSomaQtdTotal() - saldos.get(i).getSomaQtdTotal());
				saldos.get(i).setSomaPrecoTotal(saldos.get(j).getSomaPrecoTotal() - saldos.get(i).getSomaPrecoTotal());
				saldos.get(i).setCmv(saldos.get(j).getSomaPrecoTotal() -  saldos.get(i).getSomaPrecoTotal());
				saldos.get(i).setTipo("saldo");
			}
		}
		return  saldos;
	}
	
	public List<Saldo> selectAllMovimentos(Long codigo){
		List<Saldo> saldos = this.saldoDAO.selectAllMovimentos(codigo);
		int j = 0;
		for (int i = 0; i < saldos.size(); i++) {
			j = i - 1;
			if(i == 0){
				saldos.get(i).setData(saldos.get(i).getMovimento().getData());
				saldos.get(i).setSomaQtdTotal(saldos.get(i).getMovimento().getQtdItem());
				saldos.get(i).setSomaPrecoTotal(saldos.get(i).getMovimento().getPrecoTotal());
				saldos.get(i).setCmv(saldos.get(i).getSomaPrecoTotal() - saldos.get(i).getMovimento().getPrecoTotal());
				saldos.get(i).setTipo("saldo");
			}
			if(saldos.get(i).getMovimento().getTipo().equals("entrada") && i != 0){
				saldos.get(i).setData(saldos.get(i).getMovimento().getData());
				saldos.get(i).setSomaQtdTotal(saldos.get(i).getMovimento().getQtdItem() + saldos.get(j).getMovimento().getQtdItem());
				saldos.get(i).setSomaPrecoTotal(saldos.get(i).getMovimento().getPrecoTotal() + saldos.get(j).getMovimento().getPrecoTotal());
				saldos.get(i).setCmv(saldos.get(j).getCmv());
				saldos.get(i).setTipo("saldo");
			}
			if(saldos.get(i).getMovimento().getTipo().equals("saida")){
				saldos.get(i).setData(saldos.get(i).getMovimento().getData());
				saldos.get(i).setSomaQtdTotal(saldos.get(j).getSomaQtdTotal() - saldos.get(i).getMovimento().getQtdItem() );
				saldos.get(i).setSomaPrecoTotal(saldos.get(j).getSomaPrecoTotal() - saldos.get(i).getMovimento().getPrecoTotal());
				saldos.get(i).setCmv(saldos.get(j).getSomaPrecoTotal() - saldos.get(i).getSomaPrecoTotal());
				saldos.get(i).setTipo("saldo");
			}	
		}		
		return saldos;
	}

}
