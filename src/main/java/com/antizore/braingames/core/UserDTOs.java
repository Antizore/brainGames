package com.antizore.braingames.core;

public class UserDTOs {


    public record userSession(
            String sessionId,
            String username){}

}
