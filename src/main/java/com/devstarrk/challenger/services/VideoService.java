package com.devstarrk.challenger.services;

import com.devstarrk.challenger.dto.VideoDTO;
import com.devstarrk.challenger.entities.Video;
import com.devstarrk.challenger.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
}
