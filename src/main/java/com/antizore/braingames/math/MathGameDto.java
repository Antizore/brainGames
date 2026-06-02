package com.antizore.braingames.math;

public class MathGameDto {


    /**
     * DTO with equation that is sent to user
     * @param equation
     * @param taskId
     */
    public record TaskResponse(
            StringBuilder equation,
            String taskId
    ){}

    /**
     * DTO for user request to evaluate user's input
     * @param userInput
     * @param taskId
     */
    public record CheckRequest(
            int userInput,
            String taskId
    ){}

    /**
     * DTO that sends evaluation response if user's answer is correct
     * @param isCorrect
     */
    public record EvaluationResponse(
            boolean isCorrect
    ){}

}
