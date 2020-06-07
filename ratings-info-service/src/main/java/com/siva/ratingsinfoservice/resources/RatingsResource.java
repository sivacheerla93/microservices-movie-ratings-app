package com.siva.ratingsinfoservice.resources;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siva.ratingsinfoservice.model.Rating;
import com.siva.ratingsinfoservice.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

	@RequestMapping(value = "/movies/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Rating getMovieRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}

	@RequestMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserRating getUserRatings(@PathVariable("userId") String userId) {
		UserRating userRating = new UserRating();
		userRating.initData(userId);
		return userRating;

	}

}
