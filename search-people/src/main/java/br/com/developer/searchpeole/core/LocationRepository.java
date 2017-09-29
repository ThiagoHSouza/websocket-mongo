package br.com.developer.searchpeole.core;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

public interface LocationRepository extends MongoRepository<Location, String>{
	
	List<People> findByCoordinateNear(Point p, Distance distance);
	
}
