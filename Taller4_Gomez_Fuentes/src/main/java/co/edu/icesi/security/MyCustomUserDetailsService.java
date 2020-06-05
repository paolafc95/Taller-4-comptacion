package co.edu.icesi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.edu.icesi.model.TsscAdmin;
import co.edu.icesi.model.TsscGame;
import co.edu.icesi.repository.adminRepository;



@Service
public class MyCustomUserDetailsService implements UserDetailsService {
	
	private adminRepository userRepository;

	@Autowired
	public MyCustomUserDetailsService(adminRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TsscAdmin userApp = userRepository.findByUsername(username).get(0);
		if (userApp != null) {
			User.UserBuilder builder = User.withUsername(username).password(userApp.getPassword())
					.roles(userApp.getAdminType().toString());
			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}