package com.spring.project.repository;

import com.spring.project.model.PlayingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlayingTypeRepository extends JpaRepository<PlayingType,Integer> {
}
