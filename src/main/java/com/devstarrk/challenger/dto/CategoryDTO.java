package com.devstarrk.challenger.dto;

import com.devstarrk.challenger.entities.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;

    @NotBlank(message = "O campo 'name' é obrigatório")
    private String name;

    @NotBlank(message = "O campo 'color' é obrigatório")
    private String color;

    public CategoryDTO(Category entity){
        id = entity.getId();
        name = entity.getName();
        color = entity.getColor();
    }
}
