package com.imageserver.app.api.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImageListModel {
    private Long id;
    private LocalDateTime creation;
}
