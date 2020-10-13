package co.com.escuelait.microservicessrl.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import co.com.escuelait.microservicessrl.model.AccountDTO;
import co.com.escuelait.microservicessrl.model.UserDTO;
import co.com.escuelait.microservicessrl.services.UserService;
import co.com.escuelait.microservicessrl.validators.GroupValidatorOnCreate;
import co.com.escuelait.microservicessrl.validators.GroupValidatorOnUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/users")
@Api(tags = "User API Rest")
public class UsersControllerRest {

	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	@ApiOperation(notes = "Retrieve one user system by id", value = "Get user by id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Response ok if the operation was seccessful"),
			@ApiResponse(code = 404, message = "Response not found if the resource could not be found")
	})
	public ResponseEntity<UserDTO> getUserById(
			@ApiParam(example = "1", value = "Identifier for User", allowableValues = "1,2,3,4", required = true)
			@PathVariable Integer id) {
		
		System.out.println("Recovery user by id");
		
		Optional<UserDTO> optUserDTO = userService.getUserById(id);
		
		try {
		
			UserDTO userDTO = optUserDTO.orElseThrow(NoSuchElementException::new);
		
			Link withSelfRel = linkTo(methodOn(UsersControllerRest.class).getUserById(userDTO.getId())).withSelfRel();
			userDTO.add(withSelfRel);
			
			return ResponseEntity.ok(userDTO);
		
		} catch (NoSuchElementException e){
			
			return ResponseEntity.notFound().build();
			
		}
			
	}
	
	/*
	@GetMapping
	public ResponseEntity<List<UserDTO>> listAllUsers(){
		
		
		List<UserDTO> list = new ArrayList<>();
		
		list.add(new UserDTO(1, "Santiago"));
		list.add(new UserDTO(2, "Camila"));
		list.add(new UserDTO(3, "Mariana"));
		list.add(new UserDTO(4, "Daniela"));
		
		return ResponseEntity.ok(list);
		
	}*/
	
	@GetMapping
	public ResponseEntity<CollectionModel<UserDTO>> listAllUsers(@RequestParam(required = false) String name,
									  @RequestParam(required = false) String lastname,
									  @RequestParam(required = false) Integer age, 
									  @PageableDefault(size = 3, sort = {"edad","name"}, direction = Direction.ASC) Pageable pageable){
		
		List<UserDTO> list = userService.listAllUsers(pageable);
		
		for (UserDTO userDTO : list) {

			Link withSelfRel = linkTo(methodOn(UsersControllerRest.class).getUserById(userDTO.getId())).withSelfRel();
			userDTO.add(withSelfRel);
			
			Link accountsRel = linkTo(methodOn(UsersControllerRest.class).getUserAccounts(userDTO.getId())).withRel("accounts");
			userDTO.add(accountsRel);

		}
		
		if (name != null) {
			list = list.stream().filter(u -> u.getName().contains(name)).collect(Collectors.toList());
		}
		
		Link link = linkTo(methodOn(UsersControllerRest.class).listAllUsers("","",0,pageable)).withSelfRel();
		CollectionModel<UserDTO> result = CollectionModel.of(list,link);
		return ResponseEntity.ok(result);
		
	}
	
	@PostMapping
	public ResponseEntity<String> createUser(@Validated(value = GroupValidatorOnCreate.class) @RequestBody UserDTO userDTO){
		
		System.out.println("Creating user " + userDTO.getName());
		
		userDTO = userService.saveUser(userDTO);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userDTO.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@PutMapping
	public ResponseEntity<UserDTO> updateUser(@Validated(value = GroupValidatorOnUpdate.class) @RequestBody UserDTO userDTO) {
		
		
		System.out.println("updating data");
		
		return ResponseEntity.ok(userDTO);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
		
		System.out.println("deleting user by id");
		
		userService.deleteUserById(id);
		
		return ResponseEntity.ok(null);
		
	}
	
	@GetMapping("/{id}/accounts")
	public ResponseEntity<List<AccountDTO>> getUserAccounts(@PathVariable Integer id){
		
		List<AccountDTO> list = new ArrayList<>();
		
		list.add(new AccountDTO("google"));
		list.add(new AccountDTO("twitter"));
		list.add(new AccountDTO("facebook"));
		
		return ResponseEntity.ok(list);
		
	}
	
	@GetMapping("/{id}/accounts/{idAccount}")
	public ResponseEntity<AccountDTO> getUserAccountById(@PathVariable Integer id, @PathVariable Integer idAccount){
		
		return ResponseEntity.ok(new AccountDTO("google"));
		
	}
	
}
