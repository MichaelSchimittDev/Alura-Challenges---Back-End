package com.devstarrk.challenger.services;

import com.devstarrk.challenger.dto.CategoryDTO;
import com.devstarrk.challenger.entities.Category;
import com.devstarrk.challenger.entities.CopyDtoToEntity;
import com.devstarrk.challenger.repositories.CategoryRepository;
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
public class CategoryService {
    @Autowired
    private CategoryRepository repository;
    @Autowired
    private CopyDtoToEntity copy;
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
    @Transactional
    public ResponseEntity<CategoryDTO> insert(CategoryDTO dto){
        Category category = new Category();
        copy.CopyCategoryDtoToEntity(dto, category);
        category = repository.save(category);
        return ResponseEntity.ok(new CategoryDTO(category));
    }

    @Transactional
    public ResponseEntity<CategoryDTO> update(Long id, CategoryDTO dto){
        Category category = repository.getReferenceById(id);
        copy.CopyCategoryDtoToEntity(dto, category);
        category = repository.save(category);
        return ResponseEntity.ok(new CategoryDTO(category));
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
            throw new DatabaseException("Falha na integridade referencial");
        }
    }

}
