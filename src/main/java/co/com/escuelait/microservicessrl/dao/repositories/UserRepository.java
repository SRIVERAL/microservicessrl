package co.com.escuelait.microservicessrl.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.escuelait.microservicessrl.dao.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	public List<UserEntity> findByEdadLessThan(int edad);
	public List<UserEntity> findByEdadGreaterThanEqual(int edad);
	public List<UserEntity> findByNameLike(String name);
	public List<UserEntity> findByNameContaining(String name);
	
	@Query(value="SELECT * FROM ms_users WHERE name = ?1 AND edad >= ?2 AND edad <= ?3", nativeQuery= true)
	public List<UserEntity> findAllUsersBetweenAgeAndName(String name, int ageBegin, int ageEnd);
}
