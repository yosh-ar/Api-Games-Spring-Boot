package com.example.service_game.controller.commons.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    @NotBlank(message = "El campo name is required")
    private String name ;

}
