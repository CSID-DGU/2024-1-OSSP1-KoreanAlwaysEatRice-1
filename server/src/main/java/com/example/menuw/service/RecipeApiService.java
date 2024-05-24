package com.example.menuw.service;

import com.example.menuw.Exception.Exception404;
import com.example.menuw.dto.MenuDto;
import com.example.menuw.dto.RecipeDto;
import com.example.menuw.repository.IngredientRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeApiService {
    private final WebClient.Builder webClientBuilder;
    private final IngredientRepository ingredientRepository;
    private final WebClient webClient;

    String apiKey = "1f83406b071d4b72928b";
    String url = "https://openapi.foodsafetykorea.go.kr/api/" + apiKey + "/";
    private RestTemplate restTemplate = new RestTemplate();
  

    public RecipeApiService(WebClient.Builder webClientBuilder, IngredientRepository ingredientRepository) {
        this.webClientBuilder = webClientBuilder;
        this.ingredientRepository = ingredientRepository;
        this.webClient = webClientBuilder.baseUrl("https://openapi.foodsafetykorea.go.kr/api/").build();
    }

    public RecipeDto getRecipes(String menuName) { //메뉴명으로 레시피를 호출
        String query = String.format("COOKRCP01/json/0/1000/RCP_NM=%s", menuName);
        try {
            String url = this.url + query; // 예시 URL입니다. 실제 URL로 변경해주세요.
            String jsonString = restTemplate.getForObject(url, String.class);

            List<String> recipeList = new ArrayList<>();
            List<String> recipeImageList = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray rows = jsonObject.getJSONObject("COOKRCP01").getJSONArray("row");
            for (int i = 0; i < rows.length(); i++) {
                JSONObject row = rows.getJSONObject(i);

                for (int j = 1; j <= 20; j++) {
                    String key = String.format("MANUAL%02d", j);
                    String recipe = row.getString(key);
                    key = String.format("MANUAL_IMG%02d", j);
                    String recipeImage = row.getString(key);

                    if (!recipe.isBlank() && !recipeImage.isBlank()) {
                        recipeList.add(recipe);
                        recipeImageList.add(recipeImage);
                    }
                }
            }

            return RecipeDto.builder()
                    .recipeList(recipeList)
                    .recipeImageList(recipeImageList)
                    .build();
        } catch (Exception e) {
            throw new Exception404("해당 메뉴를 찾을 수 없습니다");
        }
    }

    public String getIngredients(List<Long> ingredientIds) { //id를 통해 재료 이름을 찾고 문자열로 연결합니다.
        String ingredients = "";
        int lastIndex;
        String toRemove = ",";

        for (Long id : ingredientIds) {
            ingredients += ingredientRepository.findById(id.intValue()).get().getIngredientName() + ",";
        }

        lastIndex = ingredients.lastIndexOf(",");
        return ingredients.substring(0, lastIndex) + ingredients.substring(lastIndex + toRemove.length());
    }
  
    public List<MenuDto> getMenu(List<Long> ingredientIds) { //재료기반으로 레시피를 호출
        String query = String.format("COOKRCP01/json/0/1000/RCP_PARTS_DTLS=%s", getIngredients(ingredientIds));

        try {
            String url = this.url + query;
            String jsonString = restTemplate.getForObject(url, String.class);

            List<MenuDto> recipes = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray rows = jsonObject.getJSONObject("COOKRCP01").getJSONArray("row");
            for (int i = 0; i < rows.length(); i++) {
                JSONObject row = rows.getJSONObject(i);
                String recipeName = row.getString("RCP_NM");
                String recipeIngredients = row.getString("RCP_PARTS_DTLS");
                String imageUrl = row.getString("ATT_FILE_NO_MK");
            }
            return recipes;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}