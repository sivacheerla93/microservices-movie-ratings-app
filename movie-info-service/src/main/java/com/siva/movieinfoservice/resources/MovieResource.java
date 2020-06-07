package com.siva.movieinfoservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.siva.movieinfoservice.models.Movie;
import com.siva.movieinfoservice.models.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieResource {

	@Value("${api.key}")
	private String apiKey;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		/*
		 * MovieSummary movieSummary = restTemplate.getForObject(
		 * "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey,
		 * MovieSummary.class); return new Movie(movieId, movieSummary.getTitle(),
		 * movieSummary.getOverview());
		 */

		return new Movie(movieId, "Movie Title", "Movie Overview");

	}

}
