package com.imageserver.app.util;

import com.imageserver.app.api.model.ImageListModel;
import com.imageserver.app.api.model.ImageModel;
import com.imageserver.app.entities.ImageEntity;



public class ImageMapper {

    public static ImageListModel mapToImageListModel(ImageEntity imageEntity) {
        ImageListModel mapped = new ImageListModel();
        mapped.setId(imageEntity.getId());
        mapped.setCreation(imageEntity.getCreation());
        return mapped;
    }

    public static ImageModel mapToImageModel(ImageEntity imageEntity) {
        ImageModel mapped = new ImageModel();
        mapped.setId(imageEntity.getId());
        mapped.setCreation(imageEntity.getCreation());
        mapped.setLatitude(imageEntity.getLatitude());
        mapped.setLongitude(imageEntity.getLongitude());
        mapped.setImage(new String(imageEntity.getImage()));
        return mapped;
    }
}
