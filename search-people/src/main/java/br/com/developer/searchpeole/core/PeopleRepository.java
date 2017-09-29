package br.com.developer.searchpeole.core;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PeopleRepository extends MongoRepository<People, String> {

	People findByFullName(String fullName);
	
//	List<People> findByLocationNear(Point p, Distance distance);
}
