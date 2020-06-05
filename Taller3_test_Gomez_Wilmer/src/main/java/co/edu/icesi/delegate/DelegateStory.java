package co.edu.icesi.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.model.TsscStory;

@Component
public class DelegateStory {
	
	public final static String URI= "http://localhost:8080/";
	public RestTemplate rest = new RestTemplate();
	
		
	public TsscStory findById(Integer id) throws Exception {
		TsscStory story = rest.getForObject(URI + "api/stories/" + id , TsscStory.class);
		if (story == null) {
			throw new Exception("Story is null");			
		}
		return story;
	}
	
	public Iterable<TsscStory> findAll() {

		TsscStory[] stories = rest.getForObject(URI + "api/stories", TsscStory[].class);
		List<TsscStory> sl;
		try {
			sl = Arrays.asList(stories);
			return sl;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void save(TsscStory tsscStory) throws Exception {

		TsscStory story = rest.postForEntity(URI + "api/stories", tsscStory, TsscStory.class).getBody();

		if (story == null ) {
			throw new Exception("Story is null");
		}
	}
	


}
