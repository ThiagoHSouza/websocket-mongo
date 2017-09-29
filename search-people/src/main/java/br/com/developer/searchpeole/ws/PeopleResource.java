package br.com.developer.searchpeole.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import br.com.developer.searchpeole.core.Location;
import br.com.developer.searchpeole.core.LocationRepository;
import br.com.developer.searchpeole.core.People;
import br.com.developer.searchpeole.core.PeopleRepository;
import lombok.Data;

@RestController
@CrossOrigin
public class PeopleResource {
	
	@Autowired
	PeopleRepository peopleRepository;
	
	@Autowired
	LocationRepository locationRepository;
	
	@GetMapping("find")
	public List<People> ping(){
		List<People> p = peopleRepository.findAll();
		return p;
	}
	
	@GetMapping("delete")
	public void deleteAll(){
		peopleRepository.deleteAll();
	}
	
	@PutMapping("find")
	public List<People> distance(
			@RequestHeader("Authorization") String idUser, 
			@RequestBody Distancia location){
		
//		People peopleReference = peopleRepository.findAll().get(0);
		GeoJsonPoint loc = Location.parseToGeoJsonPoint(location.getPosition());
		
		return locationRepository.findByCoordinateNear(new Point(loc.getX(), loc.getY()), new Distance(location.getDistancia(), Metrics.KILOMETERS));
	}
	
	@GetMapping("coordinate")
	public String getCoordinate(){
		Location coord = locationRepository.findAll().get(0);
		System.out.println(coord);
		return locationRepository.findAll().get(0).getCoordinate();
	}
	
	@PostMapping("position")
	public String position(@RequestHeader("Authorization") String idUser, @RequestBody String location){
		
		People people = peopleRepository.findOne(idUser);
		Location coo = new Location(people, location);
		
		locationRepository.save(coo);
		
		return coo.getCoordinate();
	}
}

@Data
class Distancia{
	private double distancia;
	private String position;
}
