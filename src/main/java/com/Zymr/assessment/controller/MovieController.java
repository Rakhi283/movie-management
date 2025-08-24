package com.Zymr.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Zymr.assessment.dto.MovieDTO;
import com.Zymr.assessment.service.MovieService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path="/movies")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@PostMapping
	public MovieDTO CreateMovie(@Valid @RequestBody MovieDTO movieDTO) {
		System.out.println("movie creation method called");
		return movieService.CreateMovie(movieDTO);
	}
	
	
	@GetMapping(path= "/{id}")
	public MovieDTO getMovieById(@PathVariable("id") String id){
		return movieService.getMovieById(id);
	}
	
	@GetMapping
	public List<MovieDTO> getAllMovies(){
		return  movieService.getAllMovies();
		
	}

	@DeleteMapping(path="/{id}")
	public String deleteMovieById(@PathVariable String id) {
		return movieService.deleteMovieById(id);
	}
	
	@PutMapping(path="/{id}")
	public MovieDTO updateMovie(@PathVariable("id") String id ,@Valid @RequestBody MovieDTO updateMovie) {
		System.out.println("Update mapping executed..");
		return movieService.updateMovie(id,updateMovie);
	}
}
