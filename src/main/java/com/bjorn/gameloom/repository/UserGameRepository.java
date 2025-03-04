package com.bjorn.gameloom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bjorn.gameloom.model.Game;
import com.bjorn.gameloom.model.User;
import com.bjorn.gameloom.model.UserGame;

public interface UserGameRepository extends JpaRepository<UserGame, Long>{
    public List<Game> findByUserId(Long userId);
    public UserGame findByUser_UsernameAndGameId(String username, Long gameId);
    public List<UserGame> findAllByUser_Username(String username);
    public UserGame findByUserAndGame(User user, Game game);


    

}
