package br.com.developer.searchpeole.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class User {
	
	@Id
	private String id;
	
	@Indexed
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
}
