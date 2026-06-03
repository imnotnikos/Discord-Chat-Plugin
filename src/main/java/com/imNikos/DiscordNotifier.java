package com.imNikos;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DiscordNotifier {
    
    // Remember to paste your actual Webhook URL here!
    private static final String WEBHOOK_URL = "Webhook_URL";
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void sendToDiscord(String message) {
        String safeMessage = message.replace("\"", "\\\"");
        String jsonPayload = "{\"content\": \"" + safeMessage + "\"}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(WEBHOOK_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }
}