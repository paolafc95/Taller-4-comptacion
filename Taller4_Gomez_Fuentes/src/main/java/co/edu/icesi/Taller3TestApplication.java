package co.edu.icesi;

import java.text.SimpleDateFormat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.model.AdminType;
import co.edu.icesi.model.TsscAdmin;
import co.edu.icesi.model.TsscGame;
import co.edu.icesi.model.TsscTopic;
import co.edu.icesi.service.AdminServiceImp;
import co.edu.icesi.service.gameServiceimp;
import co.edu.icesi.service.topicServiceimp;
@Configuration
@EnableAutoConfiguration
@SpringBootApplication
public class Taller3TestApplication {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
	
	public static void main(String[] args) {
		//SpringApplication.run(TallerTestApplication.class, args);
		ConfigurableApplicationContext c = SpringApplication.run(Taller3TestApplication.class, args);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//gameServiceimp g= c.getBean(gameServiceimp.class);
		AdminServiceImp as= c.getBean(AdminServiceImp.class);
		TsscAdmin a1= new TsscAdmin();
		a1.setPassword(passwordEncoder.encode("123"));
		a1.setSuperAdmin("superadmin");
		a1.setUsername("admin");
		a1.setAdminType(AdminType.admin);
		//a1.setPassword("123");
		as.save(a1);
		TsscAdmin a2= new TsscAdmin();
		a2.setPassword(passwordEncoder.encode("1234"));
		//a2.setPassword("123");
		a2.setSuperAdmin("superadmin");
		a2.setUsername("admin2");
		a2.setAdminType(AdminType.superAdmin);
		as.save(a2);
		gameServiceimp gs=c.getBean(gameServiceimp.class);
		TsscGame g1=new TsscGame();
		g1.setName("juego1");
		g1.setNGroups(3);
		g1.setNSprints(4);
		g1.setAdminPassword("123");
		g1.setGuestPassword("123");
		g1.setUserPassword("123");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		TsscGame game2 = new TsscGame();
		game2.setName("202-MGP");
		//game2.setScheduledDate(format.parse("2021-01-01"));
		game2.setScheduledTime(null);
		game2.setNGroups(4);
		game2.setNSprints(5);
		game2.setAdminPassword("123");
		game2.setGuestPassword("123");
		game2.setUserPassword("123");
		
		topicServiceimp ts=c.getBean(topicServiceimp.class);
		TsscTopic topic = new TsscTopic();
		topic.setName("Scrum<20 MGP-Corto");
		topic.setDescription("Scrum menos de 20 personas MGP");
		topic.setDefaultGroups(4);
		topic.setDefaultSprints(3);
		topic.setGroupPrefix("Grupo");
		
		try {
			gs.save(g1);
			System.out.println("dsd---------------------");
			gs.save(game2);
			System.out.println("game2----");
			ts.addTopic(topic);
			System.out.println("tp---");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
