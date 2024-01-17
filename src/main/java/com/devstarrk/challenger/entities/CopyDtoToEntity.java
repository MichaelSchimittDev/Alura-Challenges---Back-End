package com.devstarrk.challenger.entities;

import com.devstarrk.challenger.dto.CategoryDTO;
import com.devstarrk.challenger.dto.VideoDTO;

public class CopyDtoToEntity {

    public void CopyVideoDtoToEntity(VideoDTO dto, Video entity){
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setUrl(dto.getUrl());
    }

    public void CopyCategoryDtoToEntity(CategoryDTO dto, Category entity){
        entity.setColor(dto.getColor());
        entity.setName(dto.getName());
    }

}
