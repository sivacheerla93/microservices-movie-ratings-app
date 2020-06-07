package com.siva.movieratingsservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.siva.movieratingsservice.models.CatalogItem;
import com.siva.movieratingsservice.models.Movie;
import com.siva.movieratingsservice.models.UserRating;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	WebClient.Builder webClientBuilder;

	@RequestMapping(value = "/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId,
				UserRating.class);

		return userRating.getRatings().stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(),
					Movie.class);
			return new CatalogItem(movie.getMovieId(), movie.getName(), movie.getDescription(), rating.getRating());
		}).collect(Collectors.toList());

	}
}

/*
 * Alternative WebClient way Movie movie =
 * webClientBuilder.build().get().uri("http://localhost:8082/movies/"+
 * rating.getMovieId()) .retrieve().bodyToMono(Movie.class).block();
 */