package com.devstarrk.challenger.services;

import com.devstarrk.challenger.dto.VideoDTO;
import com.devstarrk.challenger.entities.CopyDtoToEntity;
import com.devstarrk.challenger.entities.Video;
import com.devstarrk.challenger.repositories.VideoRepository;
import com.devstarrk.challenger.services.exceptions.DatabaseException;
import com.devstarrk.challenger.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VideoService {
    @Autowired
    private VideoRepository repository;
    @Autowired
    private CopyDtoToEntity copy;
    @Transactional(readOnly = true)
    public Page<VideoDTO> findAll(Pageable pageable){
        Page<Video> result = repository.findAll(pageable);
        return result.map(VideoDTO::new);
    }
    @Transactional(readOnly = true)
    public VideoDTO findById(Long id){
        Video video = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso nao encontrado"));
        return new VideoDTO(video);
    }

    @Transactional(readOnly = true)
    public Page<VideoDTO> findByTitle(String title, Pageable pageable){
        Page<Video> result = repository.findByTitleContainingIgnoreCase(title, pageable);
        return result.map(VideoDTO::new);
    }
    @Transactional
    public ResponseEntity<VideoDTO> insert(VideoDTO dto){
        Video entity = new Video();
        copy.CopyVideoDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return ResponseEntity.ok(new VideoDTO(entity));
    }

    @Transactional
    public ResponseEntity<VideoDTO> update(Long id, VideoDTO dto){
        Video entity = repository.getReferenceById(id);
        copy.CopyVideoDtoToEntity(dto,entity);
        entity = repository.save(entity);
        return ResponseEntity.ok(new VideoDTO(entity));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso nao encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha na integridade referencial   ");
        }
    }
}
