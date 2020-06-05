package co.edu.icesi.service;

import java.util.Optional;

import co.edu.icesi.model.TsscTopic;

public interface topicService {
	
	public TsscTopic addTopic(TsscTopic entity) throws Exception;
	
	public void removeTopic (TsscTopic entity);
	
	public void setTopic (TsscTopic entity) throws Exception;
	
	//public TsscTopic getTopic (int ind);

	public TsscTopic getTopic(long ind);
}
