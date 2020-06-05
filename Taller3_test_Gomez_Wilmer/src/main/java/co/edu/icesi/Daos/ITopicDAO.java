package co.edu.icesi.Daos;

import java.time.LocalDate;
import java.util.List;

import co.edu.icesi.model.TsscAdmin;
import co.edu.icesi.model.TsscTopic;

public interface ITopicDAO {
	public void save(TsscTopic entity);
	public void update(TsscTopic entity);
	public void delete(TsscTopic entity);
	
	public List<TsscTopic> findByName(String name);
	public List<TsscTopic> findByDescription(String name);
	public List<TsscTopic> findByGroupPrefix(String name);
	public List<TsscTopic> findAll();
	public TsscTopic findById(long id);
	

}
