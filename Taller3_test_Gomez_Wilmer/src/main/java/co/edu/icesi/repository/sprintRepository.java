package co.edu.icesi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.model.TsscSprint;


public interface sprintRepository extends CrudRepository<TsscSprint, Long> {
	//public void save(TsscSprint sprint);
	public List<TsscSprint> findById(long id);
//	public void update(TsscSprint sprint);
//	public void delete(TsscSprint sprint);
//	public Iterable<TsscSprint> findAll();

}
