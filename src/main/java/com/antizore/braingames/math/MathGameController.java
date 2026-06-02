package com.antizore.braingames.math;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/math")
public class MathGameController {

    private final MathGameService mathGameService;

    public MathGameController(MathGameService mathGameService){
        this.mathGameService = mathGameService;
    }



    @GetMapping("/start")
    public void startGame(){
        MathGameStarter mathGameStarter = new MathGameStarter();
        mathGameStarter.start();
    }

    @GetMapping("/task")
    public ResponseEntity<MathGameDto.TaskResponse> requestTask(){
        return ResponseEntity
                .ok()
                .body(mathGameService.generateEquation());
    }

    @PostMapping("/task")
    public ResponseEntity<MathGameDto.EvaluationResponse> checkTask(@RequestBody MathGameDto.CheckRequest userResponse){
        return ResponseEntity
                .ok()
                .body(
                        mathGameService.checkAnswer(userResponse)
                );
    }


}
