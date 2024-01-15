package com.devstarrk.challenger.dto;

import com.devstarrk.challenger.entities.Video;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VideoDTO {
    private Long id;

    private String title;
    private String description;
    private String url;

    public VideoDTO(Video entity){
        id = entity.getId();
        title = entity.getTitle();
        description = entity.getDescription();
        url = entity.getUrl();
    }
}
