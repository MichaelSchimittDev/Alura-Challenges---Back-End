package com.devstarrk.challenger.services;

import com.devstarrk.challenger.dto.CategoryDTO;
import com.devstarrk.challenger.entities.Category;
import com.devstarrk.challenger.repositories.CategoryRepository;
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

}
