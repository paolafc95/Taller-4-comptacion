package co.edu.icesi.service;

import java.util.List;
import java.util.Optional;

import co.edu.icesi.model.TsscGame;
import co.edu.icesi.model.TsscStory;


public interface storyService {

	public boolean addStory(TsscStory entity,long l) throws Exception;
	
	public void removeStory (TsscStory entity);
	
	public void setStory (TsscStory entity, long l) throws Exception;
	
	public TsscStory getStory (long l);

	public TsscStory save(TsscStory entity) throws Exception;
}
