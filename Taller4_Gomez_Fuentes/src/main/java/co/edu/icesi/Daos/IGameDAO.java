package co.edu.icesi.Daos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import co.edu.icesi.model.TsscAdmin;
import co.edu.icesi.model.TsscGame;
import co.edu.icesi.model.TsscTimecontrol;
import co.edu.icesi.model.TsscTopic;

public interface IGameDAO {
	public void save(TsscGame entity);
	public void update(TsscGame entity);
	public void delete(TsscGame entity);
	
	public List<TsscGame> findByName(String name);
	public List<TsscGame> findByNameTopic(String name);
	public List<TsscGame> findByDescriptionTopic(String topic);
	public List<TsscGame> findByIdTopic(long id);
	public List<TsscGame> findByDateRange(LocalDate inicial,LocalDate fin);
	public List<TsscGame> findByDateTime(LocalDate inicial,LocalTime inicio,LocalTime fin);
	public List<TsscGame> findByDate(LocalDate inicial);
	public List<TsscGame> findAll();
	
	public TsscGame findById(long id);
	public List<TsscTopic> topicsByDateGame(LocalDate date);
	public void save(TsscTimecontrol newCrono);

}
