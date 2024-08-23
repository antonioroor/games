package com.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.game.dto.GameDto;
import com.game.service.GameService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping("/games")
public class GameRestController {
	
	@Autowired
	private GameService gameService;

	/**
	 * Lista de juegos.
	 * 
	 * @return Lista de juegos
	 */
	@Operation(summary = "Lista de juegos", description = "Obtiene una lista de todos los juegos disponibles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La solicitud ha tenido éxito"),
            @ApiResponse(responseCode = "400", description = "El servidor no pudo interpretar la solicitud."),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<GameDto> findAllGames() {
        return gameService.findAllGames();
    }
	
	
	/**
	 * Obtener juego por el id
	 * @param id del juego a obtener
	 * @return juego obtenido
	 */
	@Operation(summary = "Obtener juego por el id", description = "Obtiene un juego por el id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La solicitud ha tenido éxito"),
            @ApiResponse(responseCode = "400", description = "El servidor no pudo interpretar la solicitud."),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	public GameDto findGameById(@PathVariable Long id) {
		return gameService.findGameById(id);
	}
	
	/**
	 * Crear juego
	 * @param gameDto objeto con el juego a crear
	 * @return juego creado
	 */
	@Operation(summary = "Crear juego", description = "Crear juego")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La solicitud ha tenido éxito"),
            @ApiResponse(responseCode = "400", description = "El servidor no pudo interpretar la solicitud."),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public GameDto saveGame(@RequestBody GameDto gameDto) { 
		return gameService.saveGame(gameDto);
	}
	
	
	/**
	 * Actualizar juego
	 * @param gameDto objeto a actualizar
	 * @param id del juego a actualizar
	 * @return juego actualizado
	 */
	@Operation(summary = "Actualizar juego", description = "Actualizar juego")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Recurso actualizado"),
            @ApiResponse(responseCode = "400", description = "El servidor no pudo interpretar la solicitud."),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    })
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public GameDto updateGame(@RequestBody GameDto gameDto, @PathVariable Long id) {
		return gameService.updateGame(gameDto, id);
	}
	
	
	/**
	 * Eliminar juego por el id
	 * @param id del juego a eliminar
	 */
	@Operation(summary = "Eliminar juego", description = "Eliminar juego")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Recurso eliminado"),
            @ApiResponse(responseCode = "400", description = "El servidor no pudo interpretar la solicitud."),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    })
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteGameById(@PathVariable Long id) {
		gameService.deleteGameById(id);
	}
	
	
	

}
