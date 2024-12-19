package chatbot;

import okhttp3.*;
import org.json.JSONObject;
import java.io.IOException;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

@Service
public class OpenAIClient {
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY;
    private final OkHttpClient client;

    static {
        // Load API key from the .env file
        Dotenv dotenv = Dotenv.load();
        API_KEY = dotenv.get("OPENAI_API_KEY");
        if (API_KEY == null || API_KEY.isEmpty()) {
            throw new RuntimeException("API key not found in .env file!");
        }
    }

    public OpenAIClient() {
        client = new OkHttpClient();
    }

    public String getAIResponse(String prompt) throws IOException {
        // Updated the model to gpt-3.5-turbo and increased max_tokens to 200 for longer responses
        String jsonPayload = "{ \"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}], \"max_tokens\": 200 }";

        RequestBody body = RequestBody.create(jsonPayload, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                JSONObject jsonResponse = new JSONObject(responseBody);
                String aiResponse = jsonResponse.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");

                // Clean up the response to remove incomplete sentences
                return trimToCompleteSentence(aiResponse);
            } else {
                String errorResponse = response.body() != null ? response.body().string() : "No response body";
                System.out.println("Error: " + response.code() + " - " + errorResponse);
                return "Unable to fetch a response from OpenAI.";
            }
        }
    }

    /**
     * Helper method to trim the text to the last complete sentence.
     */
    private String trimToCompleteSentence(String text) {
        if (text == null || text.isEmpty()) return text;

        // Find the last period in the string
        int lastPeriodIndex = text.lastIndexOf('.');
        if (lastPeriodIndex != -1) {
            return text.substring(0, lastPeriodIndex + 1).trim();
        }
        return text.trim(); // If there's no period, return the original text
    }

}






