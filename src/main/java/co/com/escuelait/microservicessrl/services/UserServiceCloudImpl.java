package co.com.escuelait.microservicessrl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.com.escuelait.microservicessrl.cient.UserClient;
import co.com.escuelait.microservicessrl.model.UserDTO;

@Lazy
@Primary
@Service
@Qualifier("CLOUD")
@ConditionalOnProperty(prefix = "app",name = "edition",havingValue = "pro")
public class UserServiceCloudImpl implements UserService{

	@Autowired
	private UserClient userClient;
	
	@Override
	public Optional<UserDTO> getUserById(Integer id) {

		UserDTO userDTO = userClient.getUser(id);	
		
		return Optional.ofNullable(userDTO);
		
		//UserDTO userDTO = new UserDTO(2, "Silvia");
		
		//return Optional.ofNullable(userDTO);
	}

	@Override
	public List<UserDTO> listAllUsers(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO saveUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserById(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
