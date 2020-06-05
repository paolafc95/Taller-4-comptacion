package co.edu.icesi.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.model.TsscGame;
import co.edu.icesi.model.TsscStory;


public interface gameRepository extends CrudRepository<TsscGame, Long>{
	public List<TsscGame> findByName(String name);
//	public List<TsscGame> findById(long id);
//	public void update(TsscGame game);
//	public void delete(TsscGame game);
//	public Iterable<TsscGame> findAll();

	

//	public void update(TsscGame entity);
	

}
