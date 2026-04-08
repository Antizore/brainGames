package com.Antizore.brain_games.infrastructure.controller;


import com.Antizore.brain_games.application.dto.AdditionRequest;
import com.Antizore.brain_games.application.dto.AdditionResponse;
import com.Antizore.brain_games.application.math.AdditionExercises;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/math")
public class MathGameController {

    @GetMapping("/task")
    public ResponseEntity<AdditionRequest> requestTask(){
        return ResponseEntity
                .ok()
                .body(new AdditionExercises().generateNumbers());
    }

    @PostMapping("/task")
    public ResponseEntity<Boolean> checkTask(@RequestBody AdditionResponse userResponse){
        return ResponseEntity
                .ok()
                .body(
                        new AdditionExercises().checkUserInput(userResponse)
                );
    }


}
