package com.Zymr.assessment.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Zymr.assessment.ExceptionHandler.MovieNotFoundException;
import com.Zymr.assessment.dto.MovieDTO;
import com.Zymr.assessment.entites.MovieEntity;
import com.Zymr.assessment.repositories.MovieRepository;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepo;
    @Autowired
    private ModelMapper modelMapper;

    public MovieDTO getMovieById(String id) throws MovieNotFoundException {
    		
            MovieEntity movieEntity = movieRepo.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with id: " + id));
            return modelMapper.map(movieEntity, MovieDTO.class);
    }

    public MovieDTO CreateMovie(MovieDTO movieDTO) {
        try {
            if (movieDTO == null) {
                throw new MovieNotFoundException("MovieDTO cannot be null");
            }
            String uuid = UUID.randomUUID().toString();
            movieDTO.setId(uuid);
            MovieEntity movieEntity = modelMapper.map(movieDTO, MovieEntity.class);
            movieEntity = movieRepo.save(movieEntity); //save to db
            return modelMapper.map(movieEntity, MovieDTO.class);
        } catch (Exception e) {
            throw new MovieNotFoundException("Error creating movie: " + e.getMessage());
        }
    }

    public List<MovieDTO> getAllMovies() throws MovieNotFoundException {
        try {
            List<MovieDTO> MoviesList = movieRepo.findAll()
                .stream().map(movieEntity -> modelMapper.map(movieEntity, MovieDTO.class))
                .collect(Collectors.toList());
            return MoviesList;
        } catch (Exception e) {
            throw new MovieNotFoundException("Error retrieving movies: " + e.getMessage());
        }
    }

    public String deleteMovieById(String id) {
            boolean isMoviePresent = movieRepo.existsById(id);
            if (!isMoviePresent) {
                throw new MovieNotFoundException("Movie Not Found with id : " + id);
            }
            movieRepo.deleteById(id);
            return "Movie Deleted with id : "+id;
        
    }

    public MovieDTO updateMovie(String id, MovieDTO updateMovie) {
        try {
            if (updateMovie == null) {
                throw new MovieNotFoundException("UpdateMovie DTO cannot be null");
            }
            MovieEntity movieEntity = movieRepo.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie does not exist with id: " + id));
            movieEntity.setDirector(updateMovie.getDirector());
            movieEntity.setGenre(updateMovie.getGenre());
            movieEntity.setRating(updateMovie.getRating());
            movieEntity.setReleaseYear(updateMovie.getReleaseYear());
            movieEntity.setTitle(updateMovie.getTitle());
            movieEntity = movieRepo.save(movieEntity);
            return modelMapper.map(movieEntity, MovieDTO.class);
        } catch (Exception e) {
            throw new MovieNotFoundException("Error updating movie: " + e.getMessage());
        }
    }
}