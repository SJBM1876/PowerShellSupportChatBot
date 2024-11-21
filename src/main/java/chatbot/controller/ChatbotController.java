package chatbot.controller;

import chatbot.service.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import chatbot.model.UserInput;
import java.util.Map;
import java.util.Collections;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:3000")
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;

    @PostMapping
    public Map<String, String> chat(@RequestBody UserInput userInput) {
        String response = chatbotService.getChatResponse(userInput.getInput());
        return Collections.singletonMap("message", response);
    }

}
