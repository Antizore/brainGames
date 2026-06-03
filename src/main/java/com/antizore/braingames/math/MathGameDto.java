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
     * DTO for evaluation response if user's answer is correct and sends how much points is gained
     * with new time left
     * @param isCorrect
     * @param pointsGained
     * @param newTotalTimeLeft
     */
    public record EvaluationResponse(
            boolean isCorrect,
            int pointsGained,
            int newTotalTimeLeft
    ){}

}
