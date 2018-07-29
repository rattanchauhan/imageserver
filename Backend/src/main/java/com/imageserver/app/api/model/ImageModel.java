package com.imageserver.app.api.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImageModel {
    private Long id;
    private LocalDateTime creation;
    private String latitude;
    private String longitude;
    private String image;
}
