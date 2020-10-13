package co.com.escuelait.microservicessrl.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import co.com.escuelait.microservicessrl.dao.entities.UserEntity;
import co.com.escuelait.microservicessrl.model.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {

	public UserDTO getUserDto(UserEntity userEntity);
	
	//@Mapping(source = "userDTO.edad", target = "age")
	public UserEntity getUserEntity(UserDTO userDTO);
	
	public List<UserDTO> getUserDtos(List<UserEntity> userEntity);
}
