	package co.edu.icesi.service;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.Daos.ITopicDAO;
import co.edu.icesi.model.TsscTopic;
import co.edu.icesi.repository.topicRepository;

@Service
public class topicServiceimp implements topicService{

	private topicRepository repository;
	private ITopicDAO topicDAO;
	
	//@Autowired
	public topicServiceimp(topicRepository topicRep) {
		this.repository = topicRep;
	}

	@Autowired
	public topicServiceimp(ITopicDAO topicRep) {
		this.topicDAO = topicRep;
	}
	@Override
	@Transactional(value = TxType.REQUIRED)
	public TsscTopic addTopic(TsscTopic entity) throws Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new NullPointerException("El tema no puede ser nulo");
		}
		if(entity.getDefaultGroups()<= 0) {
			throw new Exception("La cantidad grupos no puede ser 0");
		}
		if(entity.getDefaultSprints()<=0) {
			throw new Exception("La cantidad Sprints no puede ser 0");
		}
		//repository.save(entity);
		topicDAO.save(entity);
		return entity;
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void removeTopic(TsscTopic entity) {
		// TODO Auto-generated method stub
		//repository.delete(entity);
		topicDAO.delete(entity);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void setTopic(TsscTopic entity) throws Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new NullPointerException("El tema no puede ser nulo");
		}
		if(entity.getDefaultGroups()<= 0) {
			throw new Exception("La cantidad grupos no puede ser 0");
		}
		if(entity.getDefaultSprints()<=0) {
			throw new Exception("La cantidad Sprints no puede ser 0");
		}
		//repository.save(entity);
		topicDAO.update(entity);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public TsscTopic getTopic(long ind) {
		// TODO Auto-generated method stub
		//return repository.findById(ind);
		return topicDAO.findById(ind);
	}
	@Transactional(value = TxType.REQUIRED)
	public Iterable<TsscTopic> findAll() {
		// TODO Auto-generated method stub
		//return repository.findAll();
		return topicDAO.findAll();
	}
	
	
}
