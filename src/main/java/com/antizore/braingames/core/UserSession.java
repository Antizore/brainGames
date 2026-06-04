package com.antizore.braingames.core;


import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash("user_session")
public class UserSession {
    @Id
    private String sessionId;
    private String username;
    private String status;

    @TimeToLive
    private Long ttlInSeconds = 10L;

    public UserSession(){}

    /**
     *
     * @param sessionId
     * @param username
     * @param status
     */
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
