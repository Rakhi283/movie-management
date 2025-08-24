package com.Zymr.assessment;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.Zymr.assessment.controller.MovieController;
import com.Zymr.assessment.dto.MovieDTO;
import com.Zymr.assessment.service.MovieService;

@WebMvcTest(MovieController.class)

class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    private final MovieDTO sampleMovie = new MovieDTO("1", "Inception", "Sci-Fi", 7, "Christopher Nolan", 2010);
    

    @Test
    void testCreateMovie() throws Exception {
        Mockito.when(movieService.CreateMovie(any(MovieDTO.class))).thenReturn(sampleMovie);

        mockMvc.perform(post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "id": "1",
                        "title": "Inception",
                        "genre": "Sci-Fi",
                        "director": "Christopher Nolan",
                        "releaseYear": 2010,
                        "rating" : 7
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Inception"));
    }

    @Test
    void testGetMovieById() throws Exception {
        Mockito.when(movieService.getMovieById("1")).thenReturn(sampleMovie);

        mockMvc.perform(get("/movies/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.title").value("Inception"));
    }

    @Test
    void testGetAllMovies() throws Exception {
        Mockito.when(movieService.getAllMovies()).thenReturn(Arrays.asList(sampleMovie));

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Inception"));
    }

    @Test
    void testDeleteMovieById() throws Exception {
        Mockito.when(movieService.deleteMovieById("1")).thenReturn("Movie deleted successfully");

        mockMvc.perform(delete("/movies/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Movie deleted successfully"));
    }

    @Test
    void testUpdateMovie() throws Exception {
        Mockito.when(movieService.updateMovie(eq("1"), any(MovieDTO.class))).thenReturn(sampleMovie);

        mockMvc.perform(put("/movies/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "id": "1",
                        "title": "Inception",
                        "genre": "Sci-Fi",
                        "director": "Christopher Nolan",
                        "releaseYear": 2010,
                        "rating" : 7
                              }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Inception"));
    }
}
