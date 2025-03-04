package com.bjorn.gameloom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bjorn.gameloom.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>{
    Optional<Game> findByName(String name);
}
