package co.edu.icesi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.model.TsscTopic;


public interface topicRepository extends CrudRepository<TsscTopic, Long>{
	//public void save(TsscTopic topic);
	public List<TsscTopic> findByName(String name);
	//public List<TsscTopic> findById(long id);
//	public void update(TsscTopic topic);
//	public void delete(TsscTopic topic);
//	public Iterable<TsscTopic> findAll();
//	public void update(TsscTopic entity);

}
