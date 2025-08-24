package com.Zymr.assessment.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MovieDTO {
	private String id;
	@NotNull 
	@NotBlank(message="Title is Required")
	private String title;
	private String director;
	private Integer releaseYear; 
	private String genre;
	@Min(0)
	@Max(10)
	private Integer rating;

}
