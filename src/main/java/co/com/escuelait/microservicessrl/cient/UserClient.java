package co.com.escuelait.microservicessrl.cient;

import co.com.escuelait.microservicessrl.model.UserDTO;

public interface UserClient {

	public UserDTO getUser(Integer id);
	
}
