package co.edu.icesi.service;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.Daos.AdminDAO;
import co.edu.icesi.Daos.IAdminDAO;
import co.edu.icesi.model.TsscAdmin;
import co.edu.icesi.repository.adminRepository;

@Service
public class AdminServiceImp implements AdminService
{
	private adminRepository adminRepository;
	private IAdminDAO adminDAO;

	//@Autowired
	public AdminServiceImp(adminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}
	@Autowired
	public AdminServiceImp(AdminDAO adminRepository) {
		this.adminDAO = adminRepository;
	}
	@Transactional(value = TxType.REQUIRED)
	public void save(TsscAdmin user) {
		//adminRepository.save(user);
		adminDAO.save(user);
	}
	@Transactional(value = TxType.REQUIRED)
	public TsscAdmin findById(long id) {

		//return adminRepository.findById(id);
		return adminDAO.findById(id);
	}
	@Transactional(value = TxType.REQUIRED)
	public Iterable<TsscAdmin> findAll() {
		//return adminRepository.findAll();
		return adminDAO.findAll();
	}
	@Transactional(value = TxType.REQUIRED)
	public void delete(TsscAdmin user) {
		//adminRepository.delete(user);
		adminDAO.delete(user);
	}
	@Transactional(value = TxType.REQUIRED)
	public void update(TsscAdmin user) {
		//adminRepository.delete(user);
		adminDAO.update(user);
	}
}
