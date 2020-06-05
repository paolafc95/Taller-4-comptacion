package co.edu.icesi.Daos;

import java.util.List;

import co.edu.icesi.model.TsscAdmin;
import co.edu.icesi.model.TsscTimecontrol;

public interface ITimeDAO {
	public void save(TsscTimecontrol entity);
	public void update(TsscTimecontrol entity);
	public void delete(TsscTimecontrol entity);
	
	public List<TsscTimecontrol> findByName(String name);
	public List<TsscTimecontrol> findAll();
	public TsscTimecontrol findById(long id);

}
