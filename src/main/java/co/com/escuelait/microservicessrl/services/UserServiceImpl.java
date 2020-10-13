package co.com.escuelait.microservicessrl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import co.com.escuelait.microservicessrl.dao.entities.UserEntity;
import co.com.escuelait.microservicessrl.dao.repositories.UserRepository;
import co.com.escuelait.microservicessrl.mappers.UserMapper;
import co.com.escuelait.microservicessrl.model.UserDTO;

@Service
@Qualifier("BD")
@ConditionalOnProperty(prefix = "app", name = "edition", havingValue = "Community")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	public Optional<UserDTO> getUserById(Integer id) {
		
		Optional<UserEntity> optUserDto = userRepository.findById(id);
		
		UserEntity userEntity = optUserDto.get();
		
		UserDTO userDto = userMapper.getUserDto(userEntity);
		
		return Optional.of(userDto);
		
	}

	@Override
	public List<UserDTO> listAllUsers(Pageable pageable) {
		
		//List<UserDTO> users = userRepository.findByEdadLessThan(22);
		//List<UserDTO> users = userRepository.findAllUsersBetweenAgeAndName("Camila",10,25);
		Page<UserEntity> pageUsers = userRepository.findAll(pageable);
		
		List<UserEntity> userEntities = pageUsers.getContent();
		
		List<UserDTO> userDtos = userMapper.getUserDtos(userEntities);
		
		return userDtos;
	}

	@Override
	public UserDTO saveUser(UserDTO userDTO) {
		
		UserEntity userEntity = userMapper.getUserEntity(userDTO);
		
		userEntity = userRepository.save(userEntity);
		
		userDTO = userMapper.getUserDto(userEntity);
		
		return userDTO;
	}

	@Override
	public void deleteUserById(Integer id) {
		
		userRepository.deleteById(id);
		
	}
}
