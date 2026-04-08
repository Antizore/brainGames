package com.Antizore.brain_games.application.math;

import com.Antizore.brain_games.application.dto.AdditionRequest;
import com.Antizore.brain_games.application.dto.AdditionResponse;

import java.security.SecureRandom;

public class AdditionExercises {

    public static void main(String[] args) {
        new AdditionExercises().generateNumbers();
    }


    public AdditionRequest generateNumbers(){

        int lowerBond = 0;
        int upperBond = 100;

        SecureRandom secureRandom = new SecureRandom();
        int firstNumber = secureRandom.nextInt(lowerBond, upperBond);
        int secondNumber = secureRandom.nextInt(lowerBond, upperBond);

        System.out.println(firstNumber);
        System.out.println(secondNumber);

        return new AdditionRequest(firstNumber, secondNumber);
    }

    public boolean checkUserInput(AdditionResponse userResponse){
        return userResponse.firstNumber() + userResponse.secondNumber() == userResponse.userInput();
    }

}
