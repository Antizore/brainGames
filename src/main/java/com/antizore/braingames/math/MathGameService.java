package com.antizore.braingames.math;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.time.Duration;

@Service
public class MathGameService {

    private final StringRedisTemplate redisTemplate;

    public MathGameService(StringRedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    public  MathGameDto.TaskResponse generateEquation(){

        String taskId = String.valueOf(java.util.UUID.randomUUID());
        StringBuilder equation = EquationGenerator.equation();
        Expression e = new ExpressionBuilder(String.valueOf(equation)).build();
        int resultOfEquation = (int) e.evaluate();

        redisTemplate.opsForValue().set(
                "task: " + taskId,
                String.valueOf(resultOfEquation),
                Duration.ofSeconds(45)
        );

        return new MathGameDto.TaskResponse(
                equation,
                taskId
        );
    }

    public MathGameDto.EvaluationResponse checkAnswer(MathGameDto.CheckRequest userResponse){
        String key = "task: " + userResponse.taskId();

        if(redisTemplate.opsForValue().get(key).equals(String.valueOf(userResponse.userInput()))){
            return new MathGameDto.EvaluationResponse(
                    true,
                    0,
                    0
            );
        }
        else{
            return new MathGameDto.EvaluationResponse(
                    false,
                    0,
                    0);
        }

    }




}
