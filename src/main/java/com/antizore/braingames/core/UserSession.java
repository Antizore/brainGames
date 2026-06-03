package com.antizore.braingames.core;


import org.hibernate.validator.constraints.UUID;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash("user_session")
public class UserSession {
    @UUID
    private String sessionId;
    private String username;
    private String status;

    @TimeToLive
    private Long ttlInSeconds = 1800L;

    public UserSession(){}

    public UserSession(String sessionId, String username, String status){
        this.sessionId = sessionId;
        this.username = username;
        this.status = status;
    }



        public String getSessionId () {
        return sessionId;
    }


        public String getUsername () {
        return username;
    }

        public String getStatus () {
        return status;
    }
        public void setStatus (String status){
        this.status = status;
    }


}
