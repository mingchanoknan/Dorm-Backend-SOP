package com.dorm.fileservice.fileservice.service;

import com.dorm.fileservice.fileservice.model.ImageData;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class FileService {

    public String upload(MultipartFile file) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("image", file.getResource());
        ImageData result = WebClient.create()
                .post()
                .uri("https://api.imgur.com/3/image")
                .header("Authorization", "Client-ID e696421c92e5a87")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .bodyToMono(ImageData.class)
                .block();
        return result.getData().getLink();
    }
}
