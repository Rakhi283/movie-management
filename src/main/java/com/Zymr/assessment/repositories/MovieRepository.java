package com.Zymr.assessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Zymr.assessment.entites.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, String> {

}
