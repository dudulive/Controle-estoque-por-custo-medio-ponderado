package org.projetoIntegrador.model.movimento;

import java.util.Date;
import java.util.List;

public interface IMovimento {
	
	public void insert(Movimento entrada);
	public List<Movimento> selectAllEntrada();
	public List<Movimento> selectAllSaida();
	public Date maxDateEntrada(Long produto);


}
