package br.com.developer.searchpeole.core;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@NoArgsConstructor
//{idPeople}@{coordinateX}@{coordinateY}@{userUpdateAt}
public class Location  implements Persistable<String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4311556620965471631L;
	
	private static final String SEPARATOR = "@";
	private static final String ID_PEOPLE_SCHEMA = "%s";
	private static final String COORDINATE_SCHEMA = "%s,%s";
	private static final String USER_UPDATE_AT_SCHEMA = "%s";	
	private static final String SCHEMA = ID_PEOPLE_SCHEMA + SEPARATOR + COORDINATE_SCHEMA + SEPARATOR + USER_UPDATE_AT_SCHEMA;
	
	@Id
	private String id;
	
	private GeoJsonPoint coordinate;
	
	@CreatedDate
	private LocalDateTime createdAt;
	
	@Autowired
	static PeopleRepository peopleRepository;
	
	@Version
	private Long version;
	
	@DBRef
	private People people;
	
	public Location(String buffer){
		this(getPeopleByBuffer(buffer), 
				parseToGeoJsonPoint(buffer));
	}
	
	public Location(People people, String bufferCoordinate){
		this(people, parseToGeoJsonPoint(bufferCoordinate));
	}
		
	public Location(People people, GeoJsonPoint coordinate){
		this.people = people;
		this.coordinate = coordinate;
	}
	
	private static People getPeopleByBuffer(String buffer){
		String idPeople = buffer.split(SEPARATOR)[0];
		return peopleRepository.findOne(idPeople);
	}
	
	public String getCoordinate(){
		return String.format(SCHEMA, people.getId(), coordinate.getX(), coordinate.getY(), people.getUpdateAt());
	}
	
	public static GeoJsonPoint parseToGeoJsonPoint(String coordinateBuffer){
		String[] splitedBuffer = coordinateBuffer.split(",");
		return new GeoJsonPoint(Double.parseDouble(splitedBuffer[0]), Double.parseDouble(splitedBuffer[1]));
	}

	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return true;
	}
}
