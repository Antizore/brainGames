package com.antizore.braingames.math;
import java.security.SecureRandom;


public class NumberRandomizer {

    public int[] generateNumbers(char[] operators){

        SecureRandom secureRandom = new SecureRandom();
        int numbersToGenerate = operators.length + 1;
        int[] numberArrays = new int[numbersToGenerate];
        int lowerBond = 1;
        int upperBond = 100;

        for(int i = numbersToGenerate-1; i >= 0; i--){
            if(i == numbersToGenerate - 1) {
                numberArrays[i] = secureRandom.nextInt(lowerBond, upperBond);
                continue;
            }
            if(operators[i] == '/') {
                numberArrays[i] = generateNumberForDivision(numberArrays[i+1], secureRandom);
                continue;
            }
            numberArrays[i] = secureRandom.nextInt(lowerBond, upperBond);
        }

        return numberArrays;
    }

    private int generateNumberForDivision(int previousNumber, SecureRandom secureRandom){
        int lowerBound = 1;
        int upperBound = 5;
        int result = secureRandom.nextInt(lowerBound,upperBound);
        return result * previousNumber;
    }






}
