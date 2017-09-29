package br.com.developer.searchpeole;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import br.com.developer.searchpeole.core.LocationRepository;
import br.com.developer.searchpeole.core.People;
import br.com.developer.searchpeole.core.PeopleRepository;

@SpringBootApplication
@EnableMongoAuditing
public class SearchPeopleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchPeopleApplication.class, args);
	}

	@Bean
	CommandLineRunner init(PeopleRepository peopleRepository, LocationRepository locationRepository){
		peopleRepository.deleteAll();
		locationRepository.deleteAll();
		
		return (evt) -> {
			
			
			peopleRepository.save(new People("1", "Thiago Souza"));//, new GeoJsonPoint(-25.43813973, -49.26640682))); //Bradesco
			peopleRepository.save(new People("2", "João Machado"));//, new GeoJsonPoint(-25.43838558, -49.26614664))); //Aroma Mineiro
			peopleRepository.save(new People("3", "Gabriel Valença"));//, new GeoJsonPoint(-25.43850305, -49.26787063))); //TIM
			peopleRepository.save(new People("4", "Débora Ançay"));//, new GeoJsonPoint(-25.43780062, -49.2651616))); //Renner
		
			
			
		};
	}
}
