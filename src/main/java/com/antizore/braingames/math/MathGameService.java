package com.antizore.braingames.math;

import com.antizore.braingames.core.UserDTOs;
import com.antizore.braingames.core.UserSession;
import com.antizore.braingames.core.UserSessionRepository;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class MathGameService {

    private final StringRedisTemplate redisTemplate;
    private final MathGameSessionRepository gameRepository;
    private final UserSessionRepository userRepository;

    public MathGameService(StringRedisTemplate redisTemplate, MathGameSessionRepository gameRepository, UserSessionRepository userRepository) {
        this.redisTemplate = redisTemplate;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    public void startNewGame(UserDTOs.userSession userSession) {
        UserSession user = userRepository.findById(userSession.sessionId()).orElseThrow(() -> new RuntimeException("There is no user session"));
        user.setStatus("IN_GAME");
        userRepository.save(user);

        final int STARTING_SCORE = 0;
        final int STARTING_DIFFICULTY = 1;
        final long EXPIRES_AT = Instant.now().plus(1, ChronoUnit.MINUTES).toEpochMilli();

        MathGameSession mathGameSession = new MathGameSession(
                java.util.UUID.randomUUID().toString(),
                STARTING_SCORE,
                STARTING_DIFFICULTY,
                EXPIRES_AT
        );

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

        boolean task = redisTemplate.opsForValue().get(key).equals(String.valueOf(userResponse.userInput()));




        if(task){
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
