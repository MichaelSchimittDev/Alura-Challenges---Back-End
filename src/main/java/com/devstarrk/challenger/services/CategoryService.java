package com.devstarrk.challenger.services;

import com.devstarrk.challenger.dto.CategoryDTO;
import com.devstarrk.challenger.entities.Category;
import com.devstarrk.challenger.repositories.CategoryRepository;
import com.devstarrk.challenger.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;
    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAll(Pageable pageable){
        Page<Category> result = repository.findAll(pageable);
        return result.map(CategoryDTO::new);
    }
    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id){
        Category category = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Categoria com o ID " + id + " nao encontrada"));
        return new CategoryDTO(category);
    }

}
