package com.antizore.braingames.math;


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
