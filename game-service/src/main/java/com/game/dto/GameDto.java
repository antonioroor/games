package com.game.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameDto implements Serializable{
	
	private static final long serialVersionUID = -4674861561810519800L;
	
	private Long id;
	private String title;
	private String platform;
	private String category;
	private Integer year;
	private String description;
	

}
