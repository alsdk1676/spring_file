package com.app.file.repository;

import com.app.file.domain.FileVO;
import com.app.file.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FileDAO {

    private final FileMapper fileMapper;

//    파일 등록
    public void save(FileVO fileVO) {
        fileMapper.insert(fileVO);
    }

//    파일 전체 조회
    public List<FileVO> selectAll() {
        return fileMapper.selectAll();
    }
}
