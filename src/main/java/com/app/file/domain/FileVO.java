package com.app.file.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class FileVO {
    private Long id;
    private String fileName;
    private String filePath;
}
