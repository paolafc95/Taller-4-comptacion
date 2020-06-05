package co.edu.icesi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.model.TsscStory;


public interface storyRepository extends CrudRepository<TsscStory, Long>{
	//public void save(TsscStory story);
//	public List<TsscStory> findById(long id);
//	public void update(TsscStory story);
//	public void delete(TsscStory story);
//	public Iterable<TsscStory> findAll();

	//public void update(TsscStory entity);

}
