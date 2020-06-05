package co.edu.icesi.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.Daos.IGameDAO;
import co.edu.icesi.Daos.ITopicDAO;
import co.edu.icesi.model.TsscGame;
import co.edu.icesi.model.TsscStory;
import co.edu.icesi.model.TsscTimecontrol;
import co.edu.icesi.model.TsscTopic;
import co.edu.icesi.repository.gameRepository;
import co.edu.icesi.repository.topicRepository;

@Service
public class gameServiceimp implements gameService{

	@Autowired
	private gameRepository repository;
	@Autowired
	private topicRepository repTopic;
	
	private IGameDAO gameDAO;
	private ITopicDAO topicDAO;
//	@Autowired
	public gameServiceimp(gameRepository rep,topicRepository topicRepository) {
		this.repository = rep;
		this.repTopic = topicRepository;
	}
	@Autowired
	public gameServiceimp(IGameDAO rep,ITopicDAO topicRepository) {
		this.gameDAO = rep;
		this.topicDAO = topicRepository;
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public boolean addGame(TsscGame entity,long idT) throws Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new NullPointerException("El juego no puede ser nulo");
		}
		if(entity.getTsscGroups().size()== 0) {
			throw new Exception("La cantidad grupos no puede ser 0");
		}
		if(entity.getTsscSprints().size()==0) {
			throw new Exception("La cantidad Sprints no puede ser 0");
		}
		if(idT != -1)
			if(topicDAO.findById(idT)==null)
				throw new NullPointerException("El tema no existe");
		
//		Optional<TsscGame> game = repository.findById(entity.getId());
//		if(game!=null)
//			throw new Exception("El juego ya existe");
		entity.setTsscTopic(topicDAO.findById(idT));
		//repository.save(entity);
		gameDAO.save(entity);
		return true;
	}
	@Transactional(value = TxType.REQUIRED)
	public TsscGame addGame2(TsscTopic entity) throws Exception {
		if(entity == null) {
			throw new NullPointerException("El tema no puede ser nulo");
		}if(entity.getDefaultGroups()<= 0) {
			throw new Exception("La cantidad grupos no puede ser 0");
		}
		if(entity.getDefaultSprints()<=0) {
			throw new Exception("La cantidad Sprints no puede ser 0");
		}
		TsscGame newGame = new TsscGame();
		List<TsscTimecontrol> ltc = new ArrayList<>();
		List<TsscStory> lsp = new ArrayList<>();
		newGame.setTsscStories(lsp);
		newGame.setTsscTimecontrol(ltc);
		newGame.getTsscStories().addAll(entity.getTsscStories());
		newGame.getTsscTimecontrols().addAll(entity.getTsscSTimecontrols());
		newGame.setTsscTopic(entity);
		//repository.save(newGame);
		gameDAO.save(newGame);
		return newGame;
	}
	
	//
	@Transactional(value = TxType.REQUIRED)
	public TsscTimecontrol addCrono(TsscTimecontrol entity) throws Exception {
		if(entity == null) {
			throw new NullPointerException("El cronograma no puede ser nulo");
		}
//		if(entity.getDefaultGroups()<= 0) {
//			throw new Exception("La cantidad grupos no puede ser 0");
//		}
//		if(entity.getDefaultSprints()<=0) {
//			throw new Exception("La cantidad Sprints no puede ser 0");
//		}
		TsscGame newGame = new TsscGame();
		TsscTimecontrol newCrono = new TsscTimecontrol();
		TsscTopic topic = new TsscTopic();
		
		List<TsscTimecontrol> ltc = new ArrayList<>();
		List<TsscTopic> lst = new ArrayList<>();
		newCrono.setName("Crono1");
		newCrono.setState("Activo");
		newCrono.getName();
		newCrono.getState();
		newCrono.setType("Tipo1");
		newCrono.getType();
		newCrono.setTsscTopic(topic);
		//newGame.getTsscTimecontrols().addAll(entity.getTsscSTimecontrols());
		
		//repository.save(newGame);
		gameDAO.save(newCrono);
		return newCrono;
	}
	
	@Override
	@Transactional(value = TxType.REQUIRED)
	public void removeGame(TsscGame entity) {
		// TODO Auto-generated method stub
		//repository.delete(entity);
		gameDAO.delete(entity);
	}

	
	@Override
	@Transactional(value = TxType.REQUIRED)
	public TsscGame getGame(long ind) {
		// TODO Auto-generated method stub
		//return repository.findById(ind);
		return gameDAO.findById(ind);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void setGame(TsscGame entity, long idT) throws Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new NullPointerException("El juego no puede ser nulo");
		}
		if(entity.getTsscGroups().size()<= 0) {
			throw new Exception("La cantidad grupos no puede ser 0");
		}
		if(entity.getTsscSprints().size()<=0) {
			throw new Exception("La cantidad Sprints no puede ser 0");
		}
		if(idT != -1)
			if(topicDAO.findById(idT)==null)
				throw new NullPointerException("El tema no puede ser nulo");
//		Optional<TsscGame> game = repository.findById(entity.getId());
//		if(game==null)
//			throw new NullPointerException("El juego no existe");
		//repository.save(entity);
		gameDAO.update(entity);
	}
	@Transactional(value = TxType.REQUIRED)
	public Iterable<TsscGame> findAll() {
		// TODO Auto-generated method stub
		return gameDAO.findAll();
	}
	@Transactional(value = TxType.REQUIRED)
	public TsscGame save(TsscGame game) throws Exception {
		// TODO Auto-generated method stub
		if (game == null) {
			throw new NullPointerException("El juego no puede ser nulo");
		}
		if(game.getNGroups()== 0) {
			throw new Exception("La cantidad grupos no puede ser 0");
		}
		if(game.getNSprints()==0) {
			throw new Exception("La cantidad Sprints no puede ser 0");
		}
		//Optional<TsscGame> game2 = repository.findById(game.getId());
		
		List<TsscGame> game2 = gameDAO.findByName(game.getName());
		System.out.println(game.getName()+" "+ game2.size());
			if(game2.size()==0) {
				gameDAO.save(game);
				System.out.println(game.getId()+"----------------");
				System.out.println(gameDAO.findAll().size());
			}else{
				throw new Exception("El juego ya existe");
			}
			//repository.save(game);
			return game;
		
		
	}
	
	
	@Transactional(value = TxType.REQUIRED)
	public Iterable<TsscStory> findStories(long id) {
		// TODO Auto-generated method stub
		//return getGame(id).get().getTsscStories();
		return getGame(id).getTsscStories();
	}
	
	@Transactional(value = TxType.REQUIRED)
	public Iterable<TsscTimecontrol> findCrono(long id) {
		// TODO Auto-generated method stub
		
		return getGame(id).getTsscTimecontrols();
	}
	@Transactional(value = TxType.REQUIRED)
	public void update(TsscGame game) {
		// TODO Auto-generated method stub
		gameDAO.update(game);
		
	}


	
	
}
