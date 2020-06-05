package co.edu.icesi.service;

import java.util.Optional;

import co.edu.icesi.model.TsscGame;
import co.edu.icesi.model.TsscTopic;


public interface gameService {

	public boolean addGame(TsscGame entity,long idT) throws Exception;
	
	public void removeGame (TsscGame entity);
	
	public void setGame (TsscGame entity, long idT) throws Exception;
	
	public TsscGame getGame (long ind);
}
