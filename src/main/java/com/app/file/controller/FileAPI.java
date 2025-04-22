package com.app.file.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/files/api/*")
public class FileAPI {

    @PostMapping("upload")
    public ResponseEntity<Map<String, Object>> fileUpload(
//            ******************
//            프론트에서 보낸 대로 title, files로 받아야 함 !!!
            @RequestParam("title") String title,
            @RequestParam("files")List<MultipartFile> files
    ) throws IOException {
        Map<String, Object> response = new HashMap<>();
        log.info("title : {}", title);
        for (MultipartFile file : files) {
            log.info("file : {}", file.getOriginalFilename());
        }

        response.put("uuid", UUID.randomUUID().toString());
        response.put("message", "정상적으로 업로드가 완료되었습니다.😎");
//        성공하면 response 보내!
        return ResponseEntity.ok(response);
    }
}
