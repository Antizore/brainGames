package com.antizore.braingames.core;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
public class UserSessionController {

    private final UserSessionRepository uSessionRepo;

    public UserSessionController(UserSessionRepository uSessionRepo) {
        this.uSessionRepo = uSessionRepo;
    }


    @PostMapping("/session")
    public ResponseEntity<UserDTOs.userSession> initSession(){

        Random random = new Random();
        String sesionId = UUID.randomUUID().toString();
        String username = "Player #" + random.nextInt(0,9999);


        UserSession userSession = new UserSession(sesionId, username, "IN_MENU");
        uSessionRepo.save(userSession);



        return ResponseEntity
                .ok()
                .body(new UserDTOs.userSession(sesionId, username));
    }


}
