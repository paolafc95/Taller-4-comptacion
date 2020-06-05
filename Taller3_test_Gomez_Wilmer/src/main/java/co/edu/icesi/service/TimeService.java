package co.edu.icesi.service;

import co.edu.icesi.model.TsscTimecontrol;

public interface TimeService {
	
	public TsscTimecontrol save(TsscTimecontrol entity);
	
	public void remove (TsscTimecontrol entity);
	
	public void set (TsscTimecontrol entity);
	
	//public TsscTopic getTopic (int ind);

	public TsscTimecontrol get(long ind);

}
