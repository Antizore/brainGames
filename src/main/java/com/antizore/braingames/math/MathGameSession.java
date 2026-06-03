package com.antizore.braingames.math;

import org.hibernate.validator.constraints.UUID;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash("math_game_session")
public class MathGameSession {

    @UUID
    private String sessionId;

    private int score;
    private int difficultyLvl;
    private long expiresAt;

    private String currentEquation;
    private int currentCorrectAnswer;

    @TimeToLive
    private Long ttlInSeconds = 900L;

    public MathGameSession(){}

    public MathGameSession(String sessionId, int score, int difficultyLvl, long expiresAt) {
        this.sessionId = sessionId;
        this.score = score;
        this.difficultyLvl = difficultyLvl;
        this.expiresAt = expiresAt;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDifficultyLvl() {
        return difficultyLvl;
    }

    public void setDifficultyLvl(int difficultyLvl) {
        this.difficultyLvl = difficultyLvl;
    }

    public long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getCurrentEquation() {
        return currentEquation;
    }

    public void setCurrentEquation(String currentEquation) {
        this.currentEquation = currentEquation;
    }

    public int getCurrentCorrectAnswer() {
        return currentCorrectAnswer;
    }

    public void setCurrentCorrectAnswer(int currentCorrectAnswer) {
        this.currentCorrectAnswer = currentCorrectAnswer;
    }

}
