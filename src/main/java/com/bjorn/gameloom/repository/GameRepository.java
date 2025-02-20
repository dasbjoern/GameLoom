package com.bjorn.gameloom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bjorn.gameloom.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>{
}
