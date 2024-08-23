package com.game.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "GAME")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game implements Serializable {
	
	private static final long serialVersionUID = -5057177084349055328L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TITLE", nullable = false, length = 255)
	private String title;
	
	@Column(name = "PLATFORM", nullable = false)
	private String platform;

	@Column(name = "CATEGORY", nullable = false)
	private String category;

	@Column(name = "YEAR")
	private Integer year;
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

}
