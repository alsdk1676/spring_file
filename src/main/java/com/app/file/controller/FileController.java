package com.app.file.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/file/*")
public class FileController {

    private String getPath() { return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")); }

    @PostMapping("upload")
    @ResponseBody
//    input에서 반복문 돌렸기 때문에 List<MultipartFile>
    public List<String> upload(@RequestParam("uploadFile")List<MultipartFile> uploadFiles) throws IOException {
        String rootPath = "C:/upload/" + getPath();

        log.info("rootPath : {}", rootPath);
//        log.info("uploadFiles : {}", uploadFiles);

        List<String> uuids = new ArrayList<>();

//        파일 만들기
        File file = new File(rootPath);
        if(!file.exists()){
            file.mkdirs();
        }

//        파일 업로드 시키기
        for(int i = 0; i < uploadFiles.size(); i++){
            uuids.add(UUID.randomUUID().toString());
            uploadFiles.get(i).transferTo(new File(rootPath, uuids.get(i) + "_" + uploadFiles.get(i).getOriginalFilename()));

//            썸네일
            if(uploadFiles.get(i).getContentType().startsWith("image")){
                FileOutputStream out = new FileOutputStream(new File(rootPath, "t_" + uuids.get(i) + "_" + uploadFiles.get(i).getOriginalFilename()));
                Thumbnailator.createThumbnail(uploadFiles.get(i).getInputStream(), out,100, 100);
                out.close();
            }
        }
//        uuid 보내기
        log.info("uuids : {}", uuids.toString());
        return uuids; // 프론트로 보냄

    }
}
