package com.app.file.mapper;

import com.app.file.domain.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

//    파일 등록
    public void insert (FileVO fileVO);

//    파일 전체 조회
    public List<FileVO> selectAll();
}
