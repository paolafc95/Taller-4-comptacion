package co.edu.icesi.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.Daos.IGameDAO;
import co.edu.icesi.Daos.IStoryDAO;
import co.edu.icesi.model.TsscStory;
import co.edu.icesi.repository.gameRepository;
import co.edu.icesi.repository.storyRepository;

@Service
public class storyServiceimp implements storyService{

	private gameRepository repositoryG;
	private storyRepository repositoryS;
	private IStoryDAO storyDAO;
	private IGameDAO gameDAO;
	//@Autowired
	public storyServiceimp(gameRepository repositoryG, storyRepository repositoryS) {
		
		this.repositoryG = repositoryG;
		this.repositoryS = repositoryS;
	}
	@Autowired
	public storyServiceimp(IGameDAO repositoryG, IStoryDAO repositoryS) {
		
		this.gameDAO = repositoryG;
		this.storyDAO = repositoryS;
	}
	
	@Override
	@Transactional(value = TxType.REQUIRED)
	public boolean addStory(TsscStory entity, long idT) throws Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new NullPointerException("la historia no puede ser nula");
		}
		if(entity.getBusinessValue().compareTo(new BigDecimal(0))<= 0) {
			throw new Exception("el valor de negocio no puede ser 0");
		}
		if(entity.getInitialSprint().compareTo(new BigDecimal(0))<=0) {
			throw new Exception("La cantidad Sprints inicial no puede ser 0");
		}
		if(entity.getPriority().compareTo(new BigDecimal(0))<=0)
			throw new Exception("La prioridad no puede ser 0");
		
			if(gameDAO.findById(idT)==null)
				throw new NullPointerException("El juego debe existir");		
		if(storyDAO.findById(entity.getId())!=null){
			throw new Exception("La historia ya existe");
		}
		//entity.setTsscGame(repositoryG.findById(idT).get());
		//repositoryS.save(entity);
		
		storyDAO.save(entity);
		return true;
	}
	@Override
	@Transactional(value = TxType.REQUIRED)
	public void removeStory(TsscStory entity) {
		// TODO Auto-generated method stub
		//repositoryS.delete(entity);
		storyDAO.delete(entity);
	}
	@Override
	@Transactional(value = TxType.REQUIRED)
	public void setStory(TsscStory entity, long idT) throws Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new NullPointerException("la historia no puede ser nula");
		}
		if(entity.getBusinessValue().compareTo(new BigDecimal(0))<= 0) {
			throw new Exception("el valor de negocio no puede ser 0");
		}
		if(entity.getInitialSprint().compareTo(new BigDecimal(0))<=0) {
			throw new Exception("La cantidad Sprints inicial no puede ser 0");
		}
		if(entity.getPriority().compareTo(new BigDecimal(0))<=0) {
			throw new Exception("La prioridad no puede ser 0");
		}
		if(gameDAO.findById(idT)==null)
			throw new NullPointerException("El juego debe existir");
		if(storyDAO.findById(idT)!=null){
			throw new Exception("La historia debe existir");
		}
	
	entity.setTsscGame(gameDAO.findById(idT));
	//repositoryS.save(entity);
	storyDAO.update(entity);
	//	repositoryS.save(entity);
	}
	@Override
	@Transactional(value = TxType.REQUIRED)
	public TsscStory getStory(long ind) {
		// TODO Auto-generated method stub
		//return repositoryS.findById(ind);
		return storyDAO.findById(ind);
	}
	@Transactional(value = TxType.REQUIRED)
	public Iterable<TsscStory> findAll() {
		// TODO Auto-generated method stub
		//return repositoryS.findAll();
		return storyDAO.findAll();
	}
	@Transactional(value = TxType.REQUIRED)
	public void update(TsscStory story){
		//repositoryS.save(story);
		storyDAO.update(story);
	}
	@Override
	@Transactional(value = TxType.REQUIRED)
	public TsscStory save(TsscStory entity) throws Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new NullPointerException("la historia no puede ser nula");
		}
		if(entity.getBusinessValue().compareTo(new BigDecimal(0))<= 0) {
			throw new Exception("el valor de negocio no puede ser 0");
		}
		if(entity.getInitialSprint().compareTo(new BigDecimal(0))<=0) {
			throw new Exception("La cantidad Sprints inicial no puede ser 0");
		}
		if(entity.getPriority().compareTo(new BigDecimal(0))<=0)
			throw new Exception("La prioridad no puede ser 0");
		
		if(storyDAO.findById(entity.getId())!=null){
			throw new Exception("La historia ya existe");
		}
		//entity.setTsscGame(repositoryG.findById(idT).get());
		//repositoryS.save(entity);
		
		storyDAO.save(entity);
		return entity;
	}
	
	
}
