package com.jetbrins.abir.photozclone.repository;

import com.jetbrins.abir.photozclone.model.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotozRepository extends CrudRepository<Photo, Integer> {
}
