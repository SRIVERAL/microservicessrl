package co.com.escuelait.microservicessrl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import co.com.escuelait.microservicessrl.model.UserDTO;

public interface UserService {

	public Optional<UserDTO> getUserById(Integer id);
	
	public List<UserDTO> listAllUsers(Pageable pageable);
	
	public UserDTO saveUser(UserDTO userDTO);
	
	public void deleteUserById(Integer id);
}
