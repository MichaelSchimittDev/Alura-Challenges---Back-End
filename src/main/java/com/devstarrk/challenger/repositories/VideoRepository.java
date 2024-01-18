package com.devstarrk.challenger.repositories;

import com.devstarrk.challenger.entities.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {

    Page<Video> findByTitleContainingIgnoreCase(String title, Pageable pageable);

}
