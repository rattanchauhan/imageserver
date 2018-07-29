package com.imageserver.app.repositories;

import com.imageserver.app.entities.ImageEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository public interface ImageRepository extends CrudRepository<ImageEntity, Long> {

    List<ImageEntity> findByIdIn(List<Long> ids);

    @Query("Select image.id, image.creation from ImageEntity image")
    List<Object[]> getImageIdAndCreation();
}
