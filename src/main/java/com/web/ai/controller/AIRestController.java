package com.web.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/")
public class AIRestController {

    private final ChatClient chatClient;

    public AIRestController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/prompt")
    public ResponseEntity<Flux<String>> getAIResponse(@RequestParam String message) {
        System.out.println(message);
        Prompt prompt = new Prompt(message);
        Flux<String> promptResponse = this.chatClient.prompt().user(message).stream().content();
        return ResponseEntity.ok(promptResponse);
    }

}
