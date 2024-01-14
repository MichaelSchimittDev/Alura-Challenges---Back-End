package com.devstarrk.challenger.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VideoTest {
    @Test
    public void testVideoEqualsAndHashCode(){
        Video video1 = new Video(1L,"Title", "Description", "http://example.com/video1");
        Video video2 = new Video(1L,"Title", "Description", "http://example.com/video1");

        assertEquals(video1, video2);

        assertEquals(video1.hashCode(), video2.hashCode());
    }
}
