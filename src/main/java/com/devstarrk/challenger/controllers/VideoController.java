package com.devstarrk.challenger.controllers;

import com.devstarrk.challenger.dto.VideoDTO;
import com.devstarrk.challenger.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/videos")
public class VideoController {
    @Autowired
    private VideoService service;
    @GetMapping
    public ResponseEntity<Page<VideoDTO>> findAll(Pageable pageable){
        Page<VideoDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<VideoDTO> findById(@PathVariable Long id){
        VideoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }
}
