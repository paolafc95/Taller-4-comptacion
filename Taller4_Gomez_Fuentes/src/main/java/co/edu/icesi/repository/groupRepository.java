package co.edu.icesi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.model.TsscGroup;



public interface groupRepository extends CrudRepository<TsscGroup, Long>{
	
	public List<TsscGroup>  findByName(String name);
	public List<TsscGroup>  findById(long id);
//	public void update(TsscGroup gr);
//	public void delete(TsscGroup gr);
//	public Iterable<TsscGroup> findAll();

}
