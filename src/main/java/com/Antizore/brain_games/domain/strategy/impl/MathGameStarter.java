package com.Antizore.brain_games.domain.strategy.impl;

import com.Antizore.brain_games.domain.model.GameType;
import com.Antizore.brain_games.domain.strategy.GameStarter;
import org.springframework.stereotype.Component;

@Component
public class MathGameStarter implements GameStarter {


    @Override
    public boolean supports(GameType type) {
        return type == GameType.MATH;
    }

    @Override
    public void start() {

    }
}
