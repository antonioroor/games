package com.game.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.dto.GameDto;
import com.game.entity.Game;
import com.game.exception.BadRequestException;
import com.game.exception.NotFoundException;
import com.game.repository.GameRepository;
import com.game.service.GameService;

@Service
public class GameServiceImpl implements GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public List<GameDto> findAllGames() {
		List<Game> games = (List<Game>) gameRepository.findAll();
		if (games != null) {
			return games.stream().map(game -> mapper.map(game, GameDto.class)).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	@Override
	public GameDto findGameById(Long id) {
		Game game = gameRepository.findById(id).orElseThrow(
				() -> new NotFoundException("El registro con id " + id + " no existe en la base de datos.")); 
		return mapper.map(game, GameDto.class);
	}

	@Override
	public GameDto saveGame(GameDto gameDto) {
		Game game = null;
		try {
			game = gameRepository.save(mapper.map(gameDto, Game.class));
		} catch (Exception e) {
			throw new BadRequestException("Los datos del juego no son correctos.");
		}
		return mapper.map(game, GameDto.class);
	}

	@Override
	public GameDto updateGame(GameDto gameDto, Long id) {
		GameDto currentGameDto = findGameById(id);
		currentGameDto.setTitle(gameDto.getTitle());
		currentGameDto.setPlatform(gameDto.getPlatform());
		currentGameDto.setCategory(gameDto.getCategory());
		currentGameDto.setYear(gameDto.getYear());
		currentGameDto.setDescription(gameDto.getDescription());
		return saveGame(currentGameDto);
	}

	@Override
	public void deleteGameById(Long id) {
		try {
			gameRepository.deleteById(id);
		} catch (Exception e) {
			throw new NotFoundException("El registro con id " + id + " no existe en la base de datos.");
		}
		
	}

}
