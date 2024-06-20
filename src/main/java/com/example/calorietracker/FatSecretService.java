package com.example.calorietracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

@Service
public class FatSecretService {

    @Value("${fatsecret.api.key}")
    private String apiKey;

    @Value("${fatsecret.api.secret}")
    private String apiSecret;

    private static final String BASE_URL = "https://platform.fatsecret.com/rest/server.api";

    public List<Meal> searchMeals(String searchTerm) throws Exception {
        String oauthNonce = Long.toString(System.currentTimeMillis());
        String oauthTimestamp = Long.toString(System.currentTimeMillis() / 1000);

        String signatureBaseString = "GET&" +
                java.net.URLEncoder.encode(BASE_URL, "UTF-8") + "&" +
                java.net.URLEncoder.encode("format=json&method=foods.search&oauth_consumer_key=" + apiKey +
                        "&oauth_nonce=" + oauthNonce + "&oauth_signature_method=HMAC-SHA1&oauth_timestamp=" + oauthTimestamp +
                        "&oauth_version=1.0&search_expression=" + java.net.URLEncoder.encode(searchTerm, "UTF-8"), "UTF-8");

        String signatureKey = apiSecret + "&";
        String oauthSignature = OAuthUtil.sign(signatureBaseString, signatureKey);

        String url = BASE_URL + "?method=foods.search&format=json&search_expression=" +
                java.net.URLEncoder.encode(searchTerm, "UTF-8") +
                "&oauth_consumer_key=" + apiKey + "&oauth_nonce=" + oauthNonce +
                "&oauth_signature_method=HMAC-SHA1&oauth_timestamp=" + oauthTimestamp +
                "&oauth_version=1.0&oauth_signature=" + java.net.URLEncoder.encode(oauthSignature, "UTF-8");

        List<Meal> meals = new ArrayList<>();
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String responseBody = EntityUtils.toString(response.getEntity());
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(responseBody);
                JsonNode foodsNode = rootNode.path("foods").path("food");

                for (JsonNode foodNode : foodsNode) {
                    Meal meal = new Meal();
                    meal.setFoodName(foodNode.path("food_name").asText());
                    meal.setCalories(foodNode.path("food_nutrition").path("calories").asInt());
                    meals.add(meal);
                }
            }
        }
        return meals;
    }
}

