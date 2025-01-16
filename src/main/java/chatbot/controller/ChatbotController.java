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
    public ResponseEntity<Map<String, Object>> chat(@RequestBody Map<String, String> request) {
        String userMessage = request.get("message");

        // Check if the user input is "exit"
        boolean isExit = "exit".equalsIgnoreCase(userMessage.trim());
        String botResponse = chatbot.getResponse(userMessage);

        // Construct the response JSON with the exit flag
        Map<String, Object> response = new HashMap<>();
        response.put("response", botResponse);
        response.put("exit", isExit);

        return ResponseEntity.ok(response);
    }
}





