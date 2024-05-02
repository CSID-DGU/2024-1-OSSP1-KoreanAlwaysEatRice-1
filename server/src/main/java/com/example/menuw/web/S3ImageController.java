package com.example.menuw.web;

import com.example.menuw.service.S3ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class S3ImageController {
    private final S3ImageService s3ImageService;

    @PostMapping
    public String saveFile(@RequestParam("file") MultipartFile multipartFile) {
        try {
            return s3ImageService.saveFile(multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
            return "파일 업로드 중 오류가 발생했습니다.";
        }
    }
}
