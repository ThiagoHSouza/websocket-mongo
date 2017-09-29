package br.com.developer.searchpeole.core;

import java.util.List;

import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@NoArgsConstructor
public class People{
	
	@Id private String id = null;
	private String fullName;
	private String image;
	
	@LastModifiedDate private LocalDateTime updateAt;	
	@CreatedDate private LocalDateTime createdAt;
	
	private User user;
	@DBRef private List<Location> locations;
		
	public People(String id, String fullName){
//		this.id = id;
		this.fullName = fullName;
	}
}
