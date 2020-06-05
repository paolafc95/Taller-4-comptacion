package co.edu.icesi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.model.AdminType;
import co.edu.icesi.model.TsscAdmin;

public interface adminRepository extends CrudRepository<TsscAdmin, Long>{
	public List<TsscAdmin> findByUsername(String username);
	List<TsscAdmin> findByAdminType(AdminType adminType);

}
