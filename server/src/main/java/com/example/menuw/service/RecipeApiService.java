package com.example.menuw.service;

import com.example.menuw.dto.RecipeDto;
import com.example.menuw.repository.IngredientRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeApiService {
    private final WebClient.Builder webClientBuilder;
    private final IngredientRepository ingredientRepository;
    private final WebClient webClient;

    public RecipeApiService(WebClient.Builder webClientBuilder, IngredientRepository ingredientRepository) {
        this.webClientBuilder = webClientBuilder;
        this.ingredientRepository = ingredientRepository;
        this.webClient = webClientBuilder.baseUrl("https://openapi.foodsafetykorea.go.kr/api/").build();
    }

    public String getIngredients(List<Long> ingredientIds) { //id를 통해 재료 이름을 찾고 문자열로 연결합니다.
        String ingredients = "";
        int lastIndex;
        String toRemove = ", ";

        for (Long id : ingredientIds) {
            ingredients += ingredientRepository.findById(id.intValue()).get().getIngredientName() + ", ";
        }

        lastIndex = ingredients.lastIndexOf(", ");
        return ingredients.substring(0, lastIndex) + ingredients.substring(lastIndex + toRemove.length());
    }

    public Mono<List<RecipeDto>> fetchRecipes(String ingredients) {
        System.out.println(ingredients);
        String apiKey = "1f83406b071d4b72928b";
        String query = String.format("COOKRCP01/json/0/100/RCP_PARTS_DTLS=%s", ingredients);

        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path(apiKey + "/" + query).build())
                .retrieve()
                .bodyToMono(String.class)
                .map(jsonString -> {
                    List<RecipeDto> recipes = new ArrayList<>();
                    org.json.JSONObject jsonObject = new org.json.JSONObject(jsonString);
                    JSONArray rows = jsonObject.getJSONObject("COOKRCP01").getJSONArray("row");
                    for (int i = 0; i < rows.length(); i++) {
                        JSONObject row = rows.getJSONObject(i);
                        String recipeName = row.getString("RCP_NM");
                        String recipeIngredients = row.getString("RCP_PARTS_DTLS");
                        String imageUrl = row.getString("ATT_FILE_NO_MK");
                        recipes.add(RecipeDto.builder()
                                .recipeName(recipeName)
                                .recipeIngredients(recipeIngredients)
                                .imageUrl(imageUrl)
                                .build());
                    }
                    return recipes;
                })
                .onErrorResume(WebClientResponseException.class, ex -> Mono.just(new ArrayList<>()));
    }
}