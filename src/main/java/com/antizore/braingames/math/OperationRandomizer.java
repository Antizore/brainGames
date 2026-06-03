package com.antizore.braingames.math;

import java.security.SecureRandom;

public class OperationRandomizer {

    private final char[] operators = {'+','-','/','*'};

    public char[] generateOperators(){
        int numberOfOperators = 1;
        char[] generatedOperators = new char[numberOfOperators];

        SecureRandom secureRandom = new SecureRandom();
        int lowerBond = 0;
        int upperBond = operators.length-1;

        for(int i = 0; i<generatedOperators.length; i++){
            generatedOperators[i] = operators[secureRandom.nextInt(lowerBond, upperBond)];
        }

        return generatedOperators;
    }




}
