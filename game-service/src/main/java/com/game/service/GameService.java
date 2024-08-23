package com.game.service;

import java.util.List;

import com.game.dto.GameDto;

public interface GameService {
	
	/**
	 * Lista de juegos.
	 * 
	 * @return Lista de juegos
	 */
	public List<GameDto> findAllGames();
	
	
	/**
	 * Obtener juego por el id
	 * @param id del juego a obtener
	 * @return juego obtenido
	 */
	public GameDto findGameById(Long id);

	/**
	 * Crear juego.
	 *
	 * @param Game juego a crear
	 * @return juego creado
	 */
	public GameDto saveGame(GameDto gameDto);
	
	/**
	 * Actualizar juego.
	 *
	 * @param Game juego a actualizar
	 * @return juego actualizado
	 */
	public GameDto updateGame(GameDto gameDto, Long id);
	
	/**
	 * Eliminar juego.
	 *
	 * @param id del juego a eliminar
	 */
	public void deleteGameById(Long id);

}
