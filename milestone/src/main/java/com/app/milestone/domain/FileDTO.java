package com.app.milestone.domain;

import com.app.milestone.entity.File;
import com.app.milestone.entity.School;
import com.app.milestone.type.FileType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class FileDTO {
    private String fileName;
    private String filePath;
    private String fileUuid;
    private Long fileSize;
    private FileType fileType;
    private Long userId;

    public File toEntity() {
        return File.builder()
                .fileName(fileName)
                .filePath(filePath)
                .fileUuid(fileUuid)
                .fileSize(fileSize)
                .fileType(fileType)
                .build();
    }

    @QueryProjection
    public FileDTO(String fileName, String filePath, String fileUuid, Long fileSize, FileType fileType, Long userId) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileUuid = fileUuid;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.userId = userId;
    }
}
