package com.imageserver.app.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class UploadImageRequest {
    private String file;
    private String latitude;
    private String longitude;
}
