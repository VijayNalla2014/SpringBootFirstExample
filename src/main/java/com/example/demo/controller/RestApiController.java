package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class RestApiController {

	@Autowired
	private UserService userService;

	@Autowired
	private ResourceBundleMessageSource messageSource;

	@GetMapping("/welcome")
	public ResponseEntity<String> getMessage() {
		return new ResponseEntity<String>("welcome to Altimetrik", HttpStatus.OK);
	}

	@GetMapping("/welcome/{subject}")
	public ResponseEntity<String> getMessage(@PathVariable String subject) {
		return new ResponseEntity<String>("welcome to " + subject, HttpStatus.OK);
	}

	@GetMapping("/welcomeInternationalization")
	public ResponseEntity<String> getMessageInternationalization() {
		return ResponseEntity
				.ok(messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale()));
	}

	@PostMapping("/user")
	public ResponseEntity<String> saveUser(@Valid @RequestBody User user) {
		if (userService.saveUserDetails(user)) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{name}")
					.buildAndExpand(user.getName()).toUri();
			return ResponseEntity.created(location).body("Saved Successfully");
		} else {
			return new ResponseEntity<String>("problem in saving", HttpStatus.OK);
		}
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok().body(userService.getAllUsers());
	}

	
	/*
	 * @GetMapping("/user/{name}") public ResponseEntity<EntityModel<User>>
	 * getUser(@PathVariable String name) { User user =
	 * userService.getUserDetails(name); if (user != null) { EntityModel<User> model
	 * = new EntityModel<User>(user); WebMvcLinkBuilder linkTo =
	 * WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).
	 * getAllUsers()); model.add(linkTo.withRel("all-users")); return
	 * ResponseEntity.ok(model); }else { throw new
	 * UserNotFoundException("no resource found with Name :"+name); } }
	 */
	 

	@DeleteMapping("/user/{name}")
	public ResponseEntity<String> deleteUser(@PathVariable String name) {
		if (userService.deleteUserDetails(name) != null) {
			return ResponseEntity.ok("Deleted Succesfully");
		} else {
			throw new UserNotFoundException("no resource found with Name :" + name);
		}
	}

}
