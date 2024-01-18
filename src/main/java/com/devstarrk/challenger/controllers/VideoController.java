package com.devstarrk.challenger.controllers;

import com.devstarrk.challenger.dto.VideoDTO;
import com.devstarrk.challenger.services.VideoService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/videos")
public class VideoController {
    @Autowired
    private VideoService service;
    @GetMapping
    public ResponseEntity<Page<VideoDTO>> findAll(@RequestParam(name = "search", required = false)String search, Pageable pageable){
        Page<VideoDTO> dto;

        if(search != null && !search.isEmpty()){
            dto = service.findByTitle(search, pageable);
        }else {
            dto = service.findAll(pageable);
        }

        return ResponseEntity.ok(dto);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<VideoDTO> findById(@PathVariable Long id){
        VideoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }
    @PostMapping
    public ResponseEntity<VideoDTO> insert(@Valid @RequestBody VideoDTO dto){
        ResponseEntity<VideoDTO> response = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(response.getBody());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<VideoDTO> update(@PathVariable Long id,@Valid @RequestBody VideoDTO dto){
        ResponseEntity<VideoDTO> response = service.update(id, dto);
        return ResponseEntity.ok(response.getBody());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
