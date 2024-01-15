package com.devstarrk.challenger.services;

import com.devstarrk.challenger.dto.VideoDTO;
import com.devstarrk.challenger.entities.Video;
import com.devstarrk.challenger.repositories.VideoRepository;
import com.devstarrk.challenger.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VideoService {
    @Autowired
    private VideoRepository repository;
    @Transactional(readOnly = true)
    public Page<VideoDTO> findAll(Pageable pageable){
        Page<Video> result = repository.findAll(pageable);
        return result.map(VideoDTO::new );
    }
    @Transactional(readOnly = true)
    public VideoDTO findById(Long id){
        Video video = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso nao encontrado"));
        return new VideoDTO(video);
    }
    @Transactional
    public ResponseEntity<VideoDTO> insert(VideoDTO dto){
        Video entity = new Video();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return ResponseEntity.ok(new VideoDTO(entity));
    }

    @Transactional
    public ResponseEntity<VideoDTO> update(Long id, VideoDTO dto){
        Video entity = repository.getReferenceById(id);
        copyDtoToEntity(dto,entity);
        entity = repository.save(entity);
        return ResponseEntity.ok(new VideoDTO(entity));
    }

    private void copyDtoToEntity(VideoDTO dto, Video entity) {
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setUrl(dto.getUrl());
    }
}
