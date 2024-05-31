package com.example.menuw.service;

import com.example.menuw.Exception.Exception404;
import com.example.menuw.dto.MenuDto;
import com.example.menuw.dto.RecipeDto;
import com.example.menuw.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.DoubleStream;

@Slf4j
@Service
public class RecipeApiService {
    private final WebClient.Builder webClientBuilder;
    private final IngredientRepository ingredientRepository;
    private final WebClient webClient;

    String apiKey = "1f83406b071d4b72928b";
    String url = "https://openapi.foodsafetykorea.go.kr/api/" + apiKey + "/";
    private RestTemplate restTemplate = new RestTemplate();


    // 사용자 프로파일 설정 (임시) - 실제 사용 시에는 사용자 데이터 기반으로 설정
    private final Map<String, Object> userProfile = new HashMap<>() {{
        put("cal", 220.0);
        put("na", 99.0);
        put("조리방식", "찌기");      //찌기,굽기,볶기,기타
        put("요리종류", "후식");      //반찬,국&찌개,후식,일품
    }};


    // 레시피 정보를 벡터로 변환하는 함수
    private double[] recipeToVector(Map<String, Object> recipe) {
        double avgCal = 700.0;  // 평균 칼로리 값
        double avgNa = 700.0;  // 평균 나트륨 값

        Map<String, double[]> wayMap = new HashMap<>() {{
            put("찌기", new double[]{1, 0, 0, 0,0,0});
            put("굽기", new double[]{0, 1, 0, 0,0,0});
            put("볶기", new double[]{0, 0, 1, 0,0,0});
            put("기타", new double[]{0, 0, 0, 1,0,0});
            put("튀기기", new double[]{0, 0, 0, 0,1,0});
            put("끓이기", new double[]{0, 0, 0, 0,0,1});

        }};
        Map<String, double[]> patMap = new HashMap<>() {{
            put("반찬", new double[]{1, 0.4, 0.4, 0.4,0.4,0.4});
            put("국&찌개", new double[]{0.4, 1, 0.4, 0.4,0.4,0.4});
            put("후식", new double[]{0.4, 0.4, 1, 0.4,0.4,0.4});
            put("일품", new double[]{0.4, 0.4, 0.4, 1,0.4,0.4});
            put("밥", new double[]{0.4, 0.4, 0.4, 0.4, 1,0.4});
            put("기타", new double[]{0.4, 0.4, 0.4, 0.4, 0.4,1});
        }};
        double cal = Double.parseDouble(recipe.getOrDefault("cal","0").toString()) / avgCal;
        double na = Double.parseDouble(recipe.getOrDefault("na", "0").toString()) / avgNa;
        double[] wayVector = wayMap.getOrDefault(recipe.get("조리방식"), new double[]{0, 0, 0, 0,0,0});
        double[] patVector = patMap.getOrDefault(recipe.get("요리종류"), new double[]{0, 0, 0, 0,0,0});

        return DoubleStream.concat(
                DoubleStream.of(cal, na),
                DoubleStream.concat(Arrays.stream(wayVector), Arrays.stream(patVector))
        ).toArray();
    }

    // 코사인 유사도 계산 함수
    private double cosineSimilarity(double[] vector1, double[] vector2) {//vec1= 사용자, vec2= 레시피
        double dotProduct = 0.0;
        double norm1 = 0.0;
        double norm2 = 0.0;
        System.out.println("vector1 = " + Arrays.toString(vector1) + " vector2 = " + Arrays.toString(vector2));
        for (int i = 0; i < vector1.length; i++) {
            dotProduct += vector1[i] * vector2[i];
            norm1 += Math.pow(vector1[i], 2);
            norm2 += Math.pow(vector2[i], 2);
        }
        return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }

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

    public String getIngredients(List<Integer> ingredientIds) { //id를 통해 재료 이름을 찾고 문자열로 연결합니다.
        String ingredients = "";
        int lastIndex;
        String toRemove = ",";

        for (Integer id : ingredientIds) {
            ingredients += ingredientRepository.findById(id.intValue()).get().getIngredientName() + ",";
        }

        lastIndex = ingredients.lastIndexOf(",");
        return ingredients.substring(0, lastIndex) + ingredients.substring(lastIndex + toRemove.length());
    }

    public List<MenuDto> getMenu(List<Integer> ingredientIds,
                                 String menuType, String recipe) { //재료기반으로 레시피를 호출
        String query = String.format("COOKRCP01/json/0/1000/RCP_PARTS_DTLS=%s", getIngredients(ingredientIds));

        try {
            String url = this.url + query;
            String jsonString = restTemplate.getForObject(url, String.class);

            List<MenuDto> recipes = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(jsonString);
            if (jsonObject.has("COOKRCP01") && jsonObject.getJSONObject("COOKRCP01").has("row")) {
                JSONArray rows = jsonObject.getJSONObject("COOKRCP01").getJSONArray("row");

                double[] userVector = recipeToVector(userProfile);

                log.info("rows length = {}", rows.length());
                for (int i = 0; i < rows.length(); i++) {
                    try {
                        JSONObject row = rows.getJSONObject(i);
                        String calorieString = (String) row.get("INFO_ENG"); // 문자열 가져오기
                        double cal = Double.parseDouble(calorieString);
                        String naString = (String) row.get("INFO_NA"); // 문자열 가져오기
                        double na = Double.parseDouble(naString);
                        Map<String, Object> recipeInfo = new HashMap<>();
                        //레시피 인포에 4가지 정보 추가

                        recipeInfo.put("cal",cal);
                        recipeInfo.put("na",na);
                        recipeInfo.put("조리방식", row.get("RCP_WAY2"));
                        recipeInfo.put("요리종류", row.get("RCP_PAT2"));
                        double[] recipeVector = recipeToVector(recipeInfo);
                        double similarity = cosineSimilarity(userVector, recipeVector); //유사도 계산
                        if(menuType.equals(row.get("RCP_WAY2"))) //클라에서 카테고리 받은 경우 유사도 증가
                            similarity += 0.25;
                        if(recipe.equals(row.get("RCP_PAT2")))  //클라에서 카테고리 받은 경우 유사도 증가
                            similarity += 0.25;
                        boolean recommend = similarity >= 0.6;  //유사도가 0.6 이상일 경우, true

                        MenuDto menuDto = MenuDto.builder()
                                .menuName(row.getString("RCP_NM"))
                                .menuId(Integer.parseInt(row.getString("RCP_SEQ")))
                                .serving(row.getString("INFO_WGT"))
                                .ingredients(row.getString("RCP_PARTS_DTLS"))
                                .menuImageURL(row.getString("ATT_FILE_NO_MK"))
                                .cal(Double.parseDouble(row.get("INFO_ENG").toString()))
                                .na(Double.parseDouble(row.get("INFO_NA").toString()))
                                .recipe(row.get("RCP_WAY2").toString())
                                .similarity(similarity)
                                .recommend(recommend)
                                .menuType(row.get("RCP_PAT2").toString())
                                .build();
                        /*      recipes 테스트
                        log.info("MenuName = {}", menuDto.getMenuName());
                        log.info("recipe = {}",menuDto.getRecipe());
                        log.info("menuType = {}", menuDto.getMenuType());
                        log.info("similarity = {}", similarity);
                        log.info("recipeVector = {}", recipeVector);
                        log.info("recommend = {}", recommend);
                            */
                        recipes.add(menuDto);
                    }catch (Exception e){
                        log.error("오류다!!!!!!!");
                        e.printStackTrace();
                    }
                }
                //유사도를 기준으로 내림차순 정렬
                recipes.sort(Comparator.comparing(MenuDto::getSimilarity).reversed());
            }
            return recipes;

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}