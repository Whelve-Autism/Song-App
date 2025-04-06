package test;

import models.Album;
import models.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试 Album 类的功能。
 * Test the functionality of the Album class.
 *
 * @author Fan Xinkang
 * @version 6.1
 * @since version 6.1
 */
public class AlbumTest {

    private Album album;

    @BeforeEach
    public void setUp() {
        System.out.println("Setting up test environment...");
        album = new Album("Test Album", "This is a test album.");
    }

    @Test
    public void testConstructor() {
        assertEquals("Test Album", album.getAlbumName(), "Album name should match.");
        assertEquals("This is a test album.", album.getAlbumDescription(), "Album description should match.");
        assertNotNull(album.getSongs(), "Songs list should not be null.");
        assertTrue(album.getSongs().isEmpty(), "Songs list should be empty initially.");
    }

    @Test
    public void testAddSong() {
        Song song1 = new Song("Song 1", "Artist 1", 200, LocalDate.of(2023, 10, 1));
        Song song2 = new Song("Song 2", "Artist 2", 250, LocalDate.of(2023, 11, 1));

        album.addSong(song1);
        album.addSong(song2);

        assertEquals(2, album.getSongs().size(), "Two songs should be added.");
        assertEquals(song1, album.getSongs().get(0), "First song should match.");
        assertEquals(song2, album.getSongs().get(1), "Second song should match.");
    }

    @Test
    public void testRemoveSong() {
        Song song1 = new Song("Song 1", "Artist 1", 200, LocalDate.of(2023, 10, 1));
        Song song2 = new Song("Song 2", "Artist 2", 250, LocalDate.of(2023, 11, 1));

        album.addSong(song1);
        album.addSong(song2);

        album.removeSong(song1);

        assertEquals(1, album.getSongs().size(), "One song should remain after removal.");
        assertEquals(song2, album.getSongs().get(0), "Remaining song should match.");
    }

    @Test
    public void testRemoveNonExistentSong() {
        Song song1 = new Song("Song 1", "Artist 1", 200, LocalDate.of(2023, 10, 1));
        Song song2 = new Song("Song 2", "Artist 2", 250, LocalDate.of(2023, 11, 1));

        album.addSong(song1);

        assertFalse(album.getSongs().remove(song2), "Non-existent song should not be removed.");
        assertEquals(1, album.getSongs().size(), "Song count should remain unchanged.");
        assertEquals(song1, album.getSongs().get(0), "Original song should still exist.");
    }

    @Test
    public void testGettersAndSetters() {
        album.setAlbumName("New Album Name");
        album.setAlbumDescription("New Album Description");

        assertEquals("New Album Name", album.getAlbumName(), "Updated album name should match.");
        assertEquals("New Album Description", album.getAlbumDescription(), "Updated album description should match.");

        ArrayList<Song> newSongs = new ArrayList<>();
        album.setSongs(newSongs);
        assertEquals(newSongs, album.getSongs(), "Updated songs list should match.");
    }

    @Test
    public void testAddDuplicateSongs() {
        Song song1 = new Song("Song 1", "Artist 1", 200, LocalDate.of(2023, 10, 1));
        Song song2 = new Song("Song 1", "Artist 1", 200, LocalDate.of(2023, 10, 1)); // Duplicate song

        album.addSong(song1);
        album.addSong(song2);

        assertEquals(2, album.getSongs().size(), "Duplicate songs should both be added.");
        assertEquals(song1, album.getSongs().get(0), "First song should match.");
        assertEquals(song2, album.getSongs().get(1), "Second song (duplicate) should match.");
    }
}
/*
 * End of models.AlbumTest Class.
 * Checked by Fan Xinkang on 2025/04/06.
 */