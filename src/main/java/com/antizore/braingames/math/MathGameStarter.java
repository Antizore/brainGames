package com.antizore.braingames.math;

import com.antizore.braingames.core.GameType;
import com.antizore.braingames.core.GameStarter;
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
