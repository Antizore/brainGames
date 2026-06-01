package com.antizore.braingames.math;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class MathGameService {

    public StringBuilder generateEquation(){

        String taskId = String.valueOf(java.util.UUID.randomUUID());

        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.opsForValue().set(
                redisTemplate.opsForValue()
                        .set("task:" + taskId, "25", Duration.ofSeconds(15)));


        StringBuilder equation = EquationGenerator.equation();

        Expression e = new ExpressionBuilder(String.valueOf(equation)).build();

        int resultOfEquation = (int) e.evaluate();

        return equation;
    }




}
