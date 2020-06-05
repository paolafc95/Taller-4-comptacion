package co.edu.icesi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.model.TsscTimecontrol;
import co.edu.icesi.model.TsscTopic;


public interface timeCRepository extends CrudRepository<TsscTimecontrol, Long>{
	//public void save(TsscTimecontrol time);
	public List<TsscTimecontrol> findByName(String name);
	public List<TsscTimecontrol> findById(long id);
//	public void update(TsscTimecontrol time);
//	public void delete(TsscTimecontrol time);
//	public Iterable<TsscTimecontrol> findAll();

}
