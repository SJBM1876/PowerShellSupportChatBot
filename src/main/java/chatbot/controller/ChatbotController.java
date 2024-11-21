package chatbot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import chatbot.PowerShellChatbot;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from your React app
public class ChatbotController {

    private final PowerShellChatbot chatbot;

    public ChatbotController(PowerShellChatbot chatbot) {
        this.chatbot = chatbot;
    }

    @PostMapping("/chat")
    public ResponseEntity<Map<String, String>> chat(@RequestBody Map<String, String> request) {
        // Extract the user message from the request body
        String userMessage = request.get("message");

        // Get the bot's response (either from predefined commands or AI-generated response)
        String botResponse = chatbot.getResponse(userMessage);

        // Ensure the response is in a consistent format
        Map<String, String> response = new HashMap<>();
        response.put("response", botResponse);

        // Return the response as a JSON object with the "response" field
        return ResponseEntity.ok(response);
    }
}



