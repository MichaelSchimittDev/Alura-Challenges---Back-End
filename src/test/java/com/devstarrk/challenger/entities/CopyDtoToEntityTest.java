package com.devstarrk.challenger.entities;

import com.devstarrk.challenger.dto.CategoryDTO;
import com.devstarrk.challenger.dto.VideoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CopyDtoToEntityTest {
    @InjectMocks
    private CopyDtoToEntity copyDtoToEntity;

    @Mock
    private VideoDTO videoDTO;

    @Mock
    private Video videoEntity;

    @Mock
    private CategoryDTO categoryDTO;

    @Mock
    private Category categoryEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCopyVideoDtoToEntity() {
        // Setup
        when(videoDTO.getTitle()).thenReturn("Video Title");
        when(videoDTO.getDescription()).thenReturn("Video Description");
        when(videoDTO.getUrl()).thenReturn("Video URL");

        // Test
        copyDtoToEntity.CopyVideoDtoToEntity(videoDTO, videoEntity);

        // Verify
        verify(videoEntity).setTitle("Video Title");
        verify(videoEntity).setDescription("Video Description");
        verify(videoEntity).setUrl("Video URL");
    }

    @Test
    void testCopyCategoryDtoToEntity() {
        // Setup
        when(categoryDTO.getName()).thenReturn("Category Name");
        when(categoryDTO.getColor()).thenReturn("Category Color");

        // Test
        copyDtoToEntity.CopyCategoryDtoToEntity(categoryDTO, categoryEntity);

        // Verify
        verify(categoryEntity).setName("Category Name");
        verify(categoryEntity).setColor("Category Color");
    }
}
