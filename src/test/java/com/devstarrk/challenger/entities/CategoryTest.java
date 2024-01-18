package com.devstarrk.challenger.entities;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {
    @Test
    void testEqualsAndHashCode() {
        Category category1 = new Category(1L, "Category 1", "Color 1", null);
        Category category2 = new Category(1L, "Category 1", "Color 1", null);
        Category category3 = new Category(2L, "Category 2", "Color 2", null);

        assertEquals(category1, category2);
        assertNotEquals(category1, category3);
    }

    @Test
    void testGettersAndSetters() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Category");
        category.setColor("Color");

        assertEquals(1L, category.getId());
        assertEquals("Category", category.getName());
        assertEquals("Color", category.getColor());
    }

    @Test
    void testOneToManyRelationship() {
        Category category = new Category();
        Video video1 = new Video(1L, "Video 1", "Desc 1", "URL 1", category);
        Video video2 = new Video(2L, "Video 2", "Desc 2", "URL 2", category);

        category.setVideos(List.of(video1, video2));

        assertEquals(2, category.getVideos().size());
        assertTrue(category.getVideos().contains(video1));
        assertTrue(category.getVideos().contains(video2));
    }

}
