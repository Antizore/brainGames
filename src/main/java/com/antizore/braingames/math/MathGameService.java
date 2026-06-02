package com.antizore.braingames.math;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import org.springframework.data.redis.core.RedisTemplate;
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
                Duration.ofSeconds(15)
        );

        return new AdditionRequest(
                equation,
                taskId
        );
    }




}
