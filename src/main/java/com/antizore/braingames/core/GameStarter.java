package com.antizore.braingames.core;


public interface GameStarter {
    boolean supports(GameType type);
    //TODO
    void start();

}
