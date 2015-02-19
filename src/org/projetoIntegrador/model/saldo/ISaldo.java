package org.projetoIntegrador.model.saldo;

import java.util.List;


public interface ISaldo {

	public List<Saldo> findBySaldo(Long produto);
	public List<Saldo> selectAll(String movimento);
	public List<Saldo> selectAllMovimentos(Long codigo);
	public List<Saldo> selectAll();
		
}
