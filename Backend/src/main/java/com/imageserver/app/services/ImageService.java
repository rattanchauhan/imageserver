package com.imageserver.app.services;

import com.imageserver.app.api.model.ImageListModel;
import com.imageserver.app.api.model.ImageModel;
import com.imageserver.app.domain.UploadImageRequest;
import com.imageserver.app.entities.ImageEntity;
import com.imageserver.app.exceptions.ApiException;
import com.imageserver.app.repositories.ImageRepository;
import com.imageserver.app.util.ImageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    public List<ImageListModel> getAllUploadedImages() {
        List<Object[]> imageList = imageRepository.getImageIdAndCreation();
        return imageList
        .stream().map(row -> {
            ImageListModel model = new ImageListModel();
            model.setId((Long) row[0]);
            model.setCreation((LocalDateTime) row[1]);
            return model;
        }).collect(Collectors.toList());
    }

    public Long uploadImage(UploadImageRequest request) {
        Assert.hasLength(request.getFile(), "File must be provided");
        Assert.hasLength(request.getLongitude(), "Longitude must be provided");
        Assert.hasLength(request.getLatitude(), "Latitude must be provided");
        ImageEntity entity = new ImageEntity();
        entity.setImage(request.getFile().getBytes());
        entity.setLatitude(request.getLatitude());
        entity.setLongitude(request.getLongitude());
        entity.setCreation(LocalDateTime.now());
        entity = imageRepository.save(entity);
        return entity.getId();
    }

    public ImageModel getAllUploadedImage(Long imageId) {
        Optional<ImageEntity> image = imageRepository.findById(imageId);
        ImageEntity imageEntity = image.orElseThrow(() -> new ApiException("File could not read"));
        return ImageMapper.mapToImageModel(imageEntity);
    }
}
