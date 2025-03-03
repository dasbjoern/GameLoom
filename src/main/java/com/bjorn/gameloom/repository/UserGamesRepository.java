package com.bjorn.gameloom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bjorn.gameloom.model.UserGames;

public interface UserGamesRepository extends JpaRepository<UserGames, Long>{
    public UserGames findByUserId(Long UserId);

    

}
