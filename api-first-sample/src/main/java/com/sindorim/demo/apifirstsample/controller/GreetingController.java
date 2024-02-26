package com.sindorim.demo.apifirstsample.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "greeting", description = "Test용 api endpoint 설정입니다. ")
public class GreetingController {

    @GetMapping("")
    @Operation(
            operationId = "greeting",
            summary = "Greeting 테스트용 오퍼레이션입니다. ",
            description = "이름을 입력받아 해당 이름에 대한 인사를 수행합니다. ",
            tags = { "greeting" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = {
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = String.class)),
                            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
                    }),
                    @ApiResponse(responseCode = "405", description = "Invalid input")
            }
    )
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome Page");
    }

    @GetMapping("/greeting/{name}")
    public ResponseEntity<String> greeting(
            @Parameter(name = "name", description = "Name for Greeting", required = true, in = ParameterIn.PATH) @PathVariable String name) {
        return ResponseEntity.ok(String.format("Hello %s.", name));
    }
    
    
    @GetMapping("/greeting/{name}/{message}")
    public ResponseEntity<String> greetingWithMessage(
            @Parameter(name = "name", description = "Name for Greeting", required = true, in = ParameterIn.PATH) @PathVariable String name,
            @Parameter(name = "message", description = "Message for Greeting", required = true, in = ParameterIn.PATH) @PathVariable String message
        ) {
        return ResponseEntity.ok(String.format("Hello %s, %s", name, message));
    }

}