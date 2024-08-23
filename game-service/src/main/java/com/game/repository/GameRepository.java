package com.game.repository;

import org.springframework.data.repository.CrudRepository;

import com.game.entity.Game;

public interface GameRepository extends CrudRepository<Game, Long>{

}
