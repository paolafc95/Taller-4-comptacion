package co.edu.icesi.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.Daos.IGameDAO;
import co.edu.icesi.Daos.IStoryDAO;
import co.edu.icesi.Daos.ITimeDAO;
import co.edu.icesi.model.TsscTimecontrol;
import co.edu.icesi.repository.gameRepository;
import co.edu.icesi.repository.storyRepository;

@Service
public class timeServiceimp implements TimeService{

	
	private ITimeDAO timeDAO;
	@Autowired
	public timeServiceimp(ITimeDAO repositoryS) {
		
		this.timeDAO = repositoryS;
	}
	
	@Override
	@Transactional(value = TxType.REQUIRED)
	public void remove(TsscTimecontrol entity) {
		// TODO Auto-generated method stub
		//repositoryS.delete(entity);
		timeDAO.delete(entity);
	}
	@Override
	@Transactional(value = TxType.REQUIRED)
	public void set(TsscTimecontrol entity){
		// TODO Auto-generated method stub
	//repositoryS.save(entity);
	timeDAO.update(entity);
	//	repositoryS.save(entity);
	}
	@Override
	@Transactional(value = TxType.REQUIRED)
	public TsscTimecontrol get(long ind) {
		// TODO Auto-generated method stub
		//return repositoryS.findById(ind);
		return timeDAO.findById(ind);
	}
	@Transactional(value = TxType.REQUIRED)
	public Iterable<TsscTimecontrol> findAll() {
		// TODO Auto-generated method stub
		//return repositoryS.findAll();
		return timeDAO.findAll();
	}
	@Transactional(value = TxType.REQUIRED)
	public void update(TsscTimecontrol story){
		//repositoryS.save(story);
		timeDAO.update(story);
	}

	@Transactional(value = TxType.REQUIRED)
	@Override
	public TsscTimecontrol save(TsscTimecontrol entity) {
		// TODO Auto-generated method stub
		timeDAO.save(entity);
		return entity;
	}
	
	
	
}
