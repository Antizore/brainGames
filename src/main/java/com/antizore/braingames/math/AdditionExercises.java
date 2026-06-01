package com.antizore.braingames.math;

import java.security.SecureRandom;

public class AdditionExercises {


    public boolean checkUserInput(AdditionResponse userResponse){
        return userResponse.firstNumber() + userResponse.secondNumber() == userResponse.userInput();
    }

}
