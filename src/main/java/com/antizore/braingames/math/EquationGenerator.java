package com.antizore.braingames.math;


import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


public class EquationGenerator {




    public static StringBuilder equation(){
        NumberRandomizer nbr = new NumberRandomizer();
        OperationRandomizer opr = new OperationRandomizer();

        char[] generatedOperators = opr.generateOperators();
        int[] generatedNumbers = nbr.generateNumbers(generatedOperators);

        StringBuilder equation = new StringBuilder();

        for(int i = 0; i<generatedNumbers.length;i++){
            equation.append(generatedNumbers[i]);
            if(i < generatedOperators.length) {
                equation.append(' ');
                equation.append(generatedOperators[i]);
                equation.append(' ');
            }
        }

        return equation;
    }


}
