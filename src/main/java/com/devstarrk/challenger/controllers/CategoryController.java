package com.devstarrk.challenger.controllers;

import com.devstarrk.challenger.dto.CategoryDTO;
import com.devstarrk.challenger.services.CategoryService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias")
public class CategoryController {
    @Autowired
    private CategoryService service;
    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> findAll(Pageable pageable){
        Page<CategoryDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }
}
