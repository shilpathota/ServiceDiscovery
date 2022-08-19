package com.movierating.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movierating.models.Rating;
import com.movierating.models.UserRating;

@RestController
@RequestMapping("/ratingdata")
public class MovieRatingService {
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId,4);
	}
	
	@RequestMapping("/users/{userId}")
	public UserRating getRatings(@PathVariable("userId") String userId) {
		List<Rating> ratings = Arrays.asList(new Rating("user1",4),new Rating("user2",3));
		UserRating userRating = new UserRating(ratings);
		userRating.setUserRatings(ratings);
		return userRating;
	}
}
