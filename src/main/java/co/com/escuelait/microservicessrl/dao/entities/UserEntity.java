package co.com.escuelait.microservicessrl.dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "ms_users")
public class UserEntity extends RepresentationModel<UserEntity> {

	@NonNull
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	private String name;
	
	@Column(name = "last_name")
	private String lastname;
	
	private int edad;
	
	private String email;
	
}
