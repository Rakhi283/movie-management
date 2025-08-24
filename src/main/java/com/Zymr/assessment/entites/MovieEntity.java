package com.Zymr.assessment.entites;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity {
	@Id
	private String id;
	private String title;
	private String director;
	private Integer releaseYear; 
	private String genre;
	private Integer rating;

}
