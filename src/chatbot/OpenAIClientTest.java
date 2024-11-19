package chatbot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class OpenAIClientTest {
    private OpenAIClient openAIClient;

    @BeforeEach
    void setUp() {
        openAIClient = new OpenAIClient();
    }

    @Test
    void testGetAIResponse_WithValidPrompt() throws IOException {
        String prompt = "Hello, how are you?";
        String response = openAIClient.getAIResponse(prompt);
        assertNotNull(response);
        assertFalse(response.isEmpty(), "Response should not be empty");
    }

    @Test
    void testGetAIResponse_WithEmptyPrompt() throws IOException {
        String response = openAIClient.getAIResponse("");
        assertNotNull(response, "Response should not be null even for empty prompts");
    }

}
