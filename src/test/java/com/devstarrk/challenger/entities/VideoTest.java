package com.devstarrk.challenger.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class VideoTest {
    @Test
    void testEqualsAndHashCode() {
        Video video1 = new Video(1L, "Title 1", "Description 1", "URL 1", new Category());
        Video video2 = new Video(1L, "Title 1", "Description 1", "URL 1", new Category());
        Video video3 = new Video(2L, "Title 2", "Description 2", "URL 2", new Category());

        assertEquals(video1, video2);
        assertNotEquals(video1, video3);
    }
    @Test
    void testGettersAndSetters() {
        Video video = new Video();
        video.setId(1L);
        video.setTitle("Title");
        video.setDescription("Description");
        video.setUrl("URL");

        assertEquals(1L, video.getId());
        assertEquals("Title", video.getTitle());
        assertEquals("Description", video.getDescription());
        assertEquals("URL", video.getUrl());
    }
}
