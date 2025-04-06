package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试 Song 类的功能。
 * Test the functionality of the Song class.
 *
 * @author Fan Xinkang
 * @version 6.1
 * @since version 6.1
 */
public class SongTest {

    private Song song;

    @BeforeEach
    public void setUp() {
        System.out.println("Setting up test environment...");
        song = new Song("Song 1", "Artist 1", 200, LocalDate.of(2023, 10, 1));
    }

    @Test
    public void testConstructor() {
        assertEquals("Song 1", song.getSongName(), "Song name should match.");
        assertEquals("Artist 1", song.getSongArtist(), "Song artist should match.");
        assertEquals(200, song.getLength(), "Song length should match.");
        assertEquals(LocalDate.of(2023, 10, 1), song.getPublishDate(), "Song publish date should match.");
    }

    @Test
    public void testGettersAndSetters() {
        song.setSongName("New Song Name");
        song.setSongArtist("New Artist");
        song.setLength(300);
        song.setPublishDate(LocalDate.of(2023, 11, 1));

        assertEquals("New Song Name", song.getSongName(), "Updated song name should match.");
        assertEquals("New Artist", song.getSongArtist(), "Updated song artist should match.");
        assertEquals(300, song.getLength(), "Updated song length should match.");
        assertEquals(LocalDate.of(2023, 11, 1), song.getPublishDate(), "Updated song publish date should match.");
    }

    @Test
    public void testEquals() {
        Song song1 = new Song("Song 1", "Artist 1", 200, LocalDate.of(2023, 10, 1));
        Song song2 = new Song("Song 1", "Artist 1", 200, LocalDate.of(2023, 10, 1));
        Song song3 = new Song("Song 2", "Artist 2", 250, LocalDate.of(2023, 11, 1));

        assertTrue(song1.equals(song2), "Songs with same attributes should be equal.");
        assertFalse(song1.equals(song3), "Songs with different attributes should not be equal.");
        assertFalse(song1.equals(null), "Song should not be equal to null.");
        assertFalse(song1.equals("Not a Song"), "Song should not be equal to a non-Song object.");
    }

    @Test
    public void testToString() {
        String expectedToString = "Song Name: Song 1\n" +
                "Song Artist: Artist 1\n" +
                "Length: 200 seconds\n" +
                "Published Date: 2023-10-01\n";

        assertEquals(expectedToString, song.toString(), "toString method should return the correct string representation.");
    }
}
/*
 * End of models.SongTest Class.
 * Checked by Fan Xinkang on 2025/04/06.
 */