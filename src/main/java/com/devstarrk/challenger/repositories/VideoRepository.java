package com.devstarrk.challenger.repositories;

import com.devstarrk.challenger.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
