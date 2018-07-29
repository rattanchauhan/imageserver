package com.imageserver.app.api.model;

import lombok.Data;

import java.util.List;

@Data
public class GetImagesResponse {
    private List<ImageListModel> images;
}
