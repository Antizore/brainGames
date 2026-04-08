package com.Antizore.brain_games.application.dto;


import java.util.Optional;

public record AdditionResponse(
        int firstNumber,
        int secondNumber,
        int userInput
) { }
