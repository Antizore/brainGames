package com.Antizore.brain_games.domain.strategy;


import com.Antizore.brain_games.domain.model.GameType;

public interface GameStarter {
    boolean supports(GameType type);
    //TODO
    void start();

}
