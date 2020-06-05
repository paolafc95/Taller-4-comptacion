package co.edu.icesi.service;

import java.util.Optional;

import co.edu.icesi.model.TsscAdmin;

public interface AdminService{
	
	public void save(TsscAdmin user);

	public TsscAdmin findById(long id) ;

	public Iterable<TsscAdmin> findAll();
	
	public void delete(TsscAdmin user);
}
