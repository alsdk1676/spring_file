package com.app.file.service;

import com.app.file.domain.FileVO;

import java.util.List;

public interface FileService {

//    파일 등록
    public void register (FileVO fileVO);

//    파일 전체 조회
    public List<FileVO> getFiles();
}
