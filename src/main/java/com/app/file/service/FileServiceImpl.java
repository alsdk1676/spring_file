package com.app.file.service;

import com.app.file.domain.FileVO;
import com.app.file.repository.FileDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileDAO fileDAO;

    @Override
    public void register(FileVO fileVO) {
        fileDAO.save(fileVO);
    }

    @Override
    public List<FileVO> getFiles() {
        return fileDAO.selectAll();
    }
}
