package com.app.file.controller;

import com.app.file.domain.FileVO;
import com.app.file.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member/*")
public class MemberController {

    private final FileService fileService;

    private String getPath() { return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")); }

    @GetMapping("my-page")
    public void goToMyPage() {;}

    @PostMapping("my-page")
    public RedirectView myPage(@RequestParam("uuid")List<String> uuids, @RequestParam("uploadFile")List<MultipartFile> uploadFiles) {
//        log.info(uuids.toString());
//        log.info("{}", uploadFile.toString());
        int count = 0;
        for(int i = 0; i < uploadFiles.size(); i++) {
            if(uploadFiles.get(i).isEmpty()){ count++; continue; }
            FileVO fileVO = new FileVO();
            fileVO.setFileName(uuids.get(i - count) + "_" + uploadFiles.get(i).getOriginalFilename());
            fileVO.setFilePath(getPath()); // 일자별 폴더
            fileService.register(fileVO);
//            memberId 필요 (constraint) // where memberId~
        }
//        새로고침 할 때마다 사진 새로 저장 X => 반복문 끝난 후에 리다이렉트 처리
        return new RedirectView("/member/detail");
    }

    @GetMapping("detail")
    public void goToDetail(Model model) {
        model.addAttribute("files", fileService.getFiles());
    }

}
