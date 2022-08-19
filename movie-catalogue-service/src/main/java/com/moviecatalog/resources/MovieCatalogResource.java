package com.moviecatalog.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.moviecatalog.models.CatalogItem;
import com.moviecatalog.models.Movie;
import com.moviecatalog.models.Rating;
import com.moviecatalog.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	RestTemplate restTemplate;
	
	
	@Autowired
	WebClient.Builder builder;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCataglog(@PathVariable("userId")String userId){
		//get all the rated movies		
		UserRating ratings = restTemplate.getForObject("http://rating-service/ratingdata/users/"+userId,UserRating.class );
		return ratings.getUserRatings().stream().map(rating->{
		Movie movie = restTemplate.getForObject("http://info-service/movies/"+rating.getMovieId(), Movie.class);	
//			Movie movie = builder
//								.build()
//								.get()
//								.uri("http://localhost:8081/movies/"+rating.getMovieId())
//								.retrieve()
//								.bodyToMono(Movie.class)
//								.block();
		System.out.println(movie.getName());
			return new CatalogItem(
				movie.getName(),"Test",rating.getRating());
		})
				.collect(Collectors.toList());
	}
	

}
