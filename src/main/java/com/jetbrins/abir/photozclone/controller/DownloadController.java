package com.jetbrins.abir.photozclone.controller;


import com.jetbrins.abir.photozclone.model.Photo;
import com.jetbrins.abir.photozclone.service.PhotozService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DownloadController {

    private final PhotozService photozService;
    public DownloadController(PhotozService photozService){
        this.photozService = photozService;
    }

    @GetMapping("/photoz/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id){
        Photo photo = photozService.get(id);
        if(photo==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        byte[] data = photo.getData();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.valueOf(photo.getContentType()));
        ContentDisposition build = ContentDisposition.builder("attachment")
                .filename(photo.getFileName())
                .build();
        header.setContentDisposition(build);
        return new ResponseEntity<>(data, header, HttpStatus.OK);
    }
}
