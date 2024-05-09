package com.example.menuw.web;

import com.example.menuw.dto.IngredientDto;
import com.example.menuw.service.IngredientService;
import com.example.menuw.service.S3ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("Image")
@RequiredArgsConstructor
public class S3ImageController {
    private final S3ImageService s3ImageService;
    private final IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<List<IngredientDto>> showAllIngredient() {
        List<IngredientDto> ingredientDtos = ingredientService.findAllIngredient();

        return  ResponseEntity.ok(ingredientDtos);
    }

    @PostMapping("/upload")
    public String saveFile(@RequestParam("file") MultipartFile multipartFile) {
        try {
            return s3ImageService.saveFile(multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
            return "파일 업로드 중 오류가 발생했습니다.";
        }
    }
}
