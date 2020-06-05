package co.edu.icesi.Daos;

import java.util.List;

import co.edu.icesi.model.TsscAdmin;

public interface IAdminDAO {
	public void save(TsscAdmin entity);
	public void update(TsscAdmin entity);
	public void delete(TsscAdmin entity);
	
	public List<TsscAdmin> findByUsername(String username);
	public List<TsscAdmin> findAll();
	public TsscAdmin findById(long id);

}
