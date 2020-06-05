package co.edu.icesi.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.model.TsscTopic;

@Component
public class DelegateTopic {
	
	public final static String URI= "http://localhost:8080/";
	public RestTemplate rest = new RestTemplate();
	
		
	public TsscTopic findById(long id) throws Exception {
		TsscTopic topic = rest.getForObject(URI + "api/topics/" + id , TsscTopic.class);
		if (topic == null) {
			throw new Exception("Topic is null");			
		}
		return topic;
	}
	
	public Iterable<TsscTopic> findAll() {

		TsscTopic[] topics = rest.getForObject(URI + "api/topics", TsscTopic[].class);
		List<TsscTopic> tl;
		try {
			tl = Arrays.asList(topics);
			return tl;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void save(TsscTopic tsscTopic) throws Exception {

		TsscTopic topic = rest.postForEntity(URI + "api/topics", tsscTopic, TsscTopic.class).getBody();

		if (topic == null ) {
			throw new Exception("Topic is null");
		}
	}
	


}
