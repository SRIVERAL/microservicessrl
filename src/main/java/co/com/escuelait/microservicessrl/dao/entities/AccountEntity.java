package co.com.escuelait.microservicessrl.dao.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class AccountEntity {

	private Integer id;
	
	@NonNull
	private String name;
	
}
