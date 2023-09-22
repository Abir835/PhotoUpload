package com.jetbrins.abir.photozclone.controller;

import com.jetbrins.abir.photozclone.model.Photo;
import com.jetbrins.abir.photozclone.service.PhotozService;
import jakarta.validation.Valid;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@RestController
public class PhotozController {

    private final PhotozService photozService;

    public PhotozController(PhotozService photozService){
        this.photozService = photozService;
    }

    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/photoz")
    public Iterable<Photo> get(){
        return photozService.get();
    }

    @GetMapping("/photoz/{id}")
    public Photo get(@PathVariable Integer id){
        Photo photo = photozService.get(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }
    @DeleteMapping("/photoz/{id}")
    public void delete(@PathVariable Integer id){
        photozService.remove(id);
    }

//    @PostMapping("/photoz")
//    public void create(@RequestBody @Valid Photo photo){
//        photo.setId(UUID.randomUUID().toString());
//        db.put(photo.getId(), photo);
//    }

    @PostMapping("/photoz/")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        return photozService.save(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }

}
