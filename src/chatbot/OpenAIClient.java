package chatbot;

import okhttp3.*;
import org.json.JSONObject;
import java.io.IOException;

public class OpenAIClient {
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = "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"; // Ensure to replace this with your actual API key
    private final OkHttpClient client;

    public OpenAIClient() {
        client = new OkHttpClient();
    }

    public String getAIResponse(String prompt) throws IOException {
        // Change the model name to gpt-3.5-turbo
        String jsonPayload = "{ \"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}], \"max_tokens\": 50 }";

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
                return jsonResponse.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");
            } else {
                // Detailed error logging to help debug
                String errorResponse = response.body() != null ? response.body().string() : "No response body";
                System.out.println("Error: " + response.code() + " - " + errorResponse);
                return "Unable to fetch a response from OpenAI.";
            }
        }
    }
}



