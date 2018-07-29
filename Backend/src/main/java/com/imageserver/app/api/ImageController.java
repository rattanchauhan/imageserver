package com.imageserver.app.api;

import com.imageserver.app.api.model.*;
import com.imageserver.app.domain.UploadImageRequest;
import com.imageserver.app.entities.ImageListProjection;
import com.imageserver.app.services.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController @Slf4j public class ImageController {

    @Autowired ImageService imageService;

    @GetMapping("/images")
    public ResponseEntity<GetImagesResponse> getAllUploadedData() {
        log.info("getAllUploadedImages called");
        List<ImageListModel> images = imageService.getAllUploadedImages();
        log.info("getAllUploadedImages called {}", images);
        GetImagesResponse response = new GetImagesResponse();
        response.setImages(images);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/images/{imageId}")
    public ResponseEntity<GetImageResponse> getAllUploadedData(@PathVariable("imageId") Long imageId) {
        log.info("getAllUploadedImage called for id [] ", imageId);
        ImageModel image = imageService.getAllUploadedImage(imageId);
        GetImageResponse response = new GetImageResponse();
        response.setImage(image);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/images")
    public ResponseEntity<UploadedFileResponse> uploadImage(@RequestBody UploadImageRequest uploadImageRequest) {
        log.info("uploadImage called with lat : {}  long : {}", uploadImageRequest.getLatitude(), uploadImageRequest.getLongitude());
        Long id = imageService.uploadImage(uploadImageRequest);
        UploadedFileResponse response = new UploadedFileResponse();
        response.setId(id);
        return ResponseEntity.ok(response);
    }
}
