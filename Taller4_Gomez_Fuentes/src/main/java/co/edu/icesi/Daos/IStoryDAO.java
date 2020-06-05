package co.edu.icesi.Daos;

import java.util.List;

import co.edu.icesi.model.TsscAdmin;
import co.edu.icesi.model.TsscStory;

public interface IStoryDAO {
	public void save(TsscStory entity);
	public void update(TsscStory entity);
	public void delete(TsscStory entity);
	
	public List<TsscStory> findByDescription(String descrip);
	public List<TsscStory> findAll();
	public TsscStory findById(long id);

}
