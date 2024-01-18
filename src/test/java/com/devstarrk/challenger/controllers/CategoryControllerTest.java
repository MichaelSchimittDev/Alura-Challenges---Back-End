package com.devstarrk.challenger.controllers;

import com.devstarrk.challenger.dto.CategoryDTO;
import com.devstarrk.challenger.dto.VideoDTO;
import com.devstarrk.challenger.services.CategoryService;
import com.devstarrk.challenger.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAll() {
        // Mock data
        Page<CategoryDTO> mockPage = mock(Page.class);
        when(categoryService.findAll(any(Pageable.class))).thenReturn(mockPage);

        // Call the controller method
        ResponseEntity<Page<CategoryDTO>> responseEntity = categoryController.findAll(mock(Pageable.class));

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockPage, responseEntity.getBody());
    }

    @Test
    void testFindById() {
        // Mock data
        CategoryDTO mockCategoryDTO = mock(CategoryDTO.class);
        when(categoryService.findById(anyLong())).thenReturn(mockCategoryDTO);

        // Call the controller method
        ResponseEntity<?> responseEntity = categoryController.findById(1L);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockCategoryDTO, responseEntity.getBody());
    }

    @Test
    void testFindVideosByCategoryId() {
        // Mock data
        List<VideoDTO> mockVideoDTOList = Collections.singletonList(mock(VideoDTO.class));
        when(categoryService.findVideosByCategoryId(anyLong())).thenReturn(mockVideoDTOList);

        // Call the controller method
        ResponseEntity<List<VideoDTO>> responseEntity = categoryController.findVideosByCategoryId(1L);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockVideoDTOList, responseEntity.getBody());
    }

    @Test
    void testInsert() {
        CategoryDTO mockCategoryDTO = mock(CategoryDTO.class);
        when(categoryService.insert(any(CategoryDTO.class))).thenReturn(ResponseEntity.ok(mockCategoryDTO));

        ResponseEntity<CategoryDTO> responseEntity = categoryController.insert(mock(CategoryDTO.class));

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockCategoryDTO, responseEntity.getBody());

        URI expectedUri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(mockCategoryDTO.getId()).toUri();
        assertEquals(expectedUri, responseEntity.getHeaders().getLocation());
    }

    @Test
    void testUpdate() {
        // Mock data
        CategoryDTO mockCategoryDTO = mock(CategoryDTO.class);
        when(categoryService.update(anyLong(), any(CategoryDTO.class))).thenReturn(ResponseEntity.ok(mockCategoryDTO));

        // Call the controller method
        ResponseEntity<CategoryDTO> responseEntity = categoryController.update(1L, mock(CategoryDTO.class));

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockCategoryDTO, responseEntity.getBody());
    }

    @Test
    void testDelete() {
        // Call the controller method
        ResponseEntity<Void> responseEntity = categoryController.delete(1L);

        // Verify
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(categoryService, times(1)).delete(anyLong());
    }

    @Test
    void testDelete_ResourceNotFoundException() {
        // Mocking the service to throw ResourceNotFoundException
        doThrow(new ResourceNotFoundException("Category not found")).when(categoryService).delete(anyLong());

        // Call the controller method and expect an exception
        assertThrows(ResourceNotFoundException.class, () -> categoryController.delete(1L));
    }

}
