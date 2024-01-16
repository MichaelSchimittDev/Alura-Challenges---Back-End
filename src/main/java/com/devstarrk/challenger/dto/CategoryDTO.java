package com.devstarrk.challenger.dto;

import com.devstarrk.challenger.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private String color;

    public CategoryDTO(Category entity){
        id = entity.getId();
        name = entity.getName();
        color = entity.getColor();
    }
}
