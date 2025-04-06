package test.controllers;

import controllers.SongManager;
import models.Album;
import models.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * 测试 SongManager 类的功能。
 * Test the functionality of the SongManager class.
 *
 * @author Fan Xinkang
 * @version 6.1
 * @since version 6.1
 */
public class SongManagerTest {

    private SongManager songManager;

    @Mock
    private utils.ScannerInput scannerInputMock;

    @BeforeEach
    public void setUp() {
        System.out.println("Setting up test environment...");
        MockitoAnnotations.openMocks(this);
        songManager = new SongManager();
        songManager.setAlbums(new ArrayList<>());
    }

    @Test
    public void testHasAlbum() {
        assertFalse(songManager.hasAlbum(), "No albums should be present initially.");

        Album album = new Album("Test Album", "This is a test album.");
        songManager.getAlbums().add(album);
        assertTrue(songManager.hasAlbum(), "One album should be present after adding.");
    }

    @Test
    public void testIsAlbumNameExists() {
        assertFalse(songManager.isAlbumNameExists("Test Album"), "Album name should not exist initially.");

        Album album = new Album("Test Album", "This is a test album.");
        songManager.getAlbums().add(album);
        assertTrue(songManager.isAlbumNameExists("Test Album"), "Album name should exist after adding.");
    }

    @Test
    public void testIsSongNameExists() {
        Album album = new Album("Test Album", "This is a test album.");
        songManager.getAlbums().add(album);

        assertFalse(songManager.isSongNameExists(album, "Song 1"), "Song name should not exist initially.");

        Song song = new Song("Song 1", "Artist 1", 200, LocalDate.of(2023, 10, 1));
        album.getSongs().add(song);
        assertTrue(songManager.isSongNameExists(album, "Song 1"), "Song name should exist after adding.");
    }

    @Test
    public void testAddAlbum() {
        when(scannerInputMock.readNextLine("Enter the name of the new album: ")).thenReturn("Test Album");
        when(scannerInputMock.readNextLine("Enter the description of the new album: ")).thenReturn("This is a test album.");

        assertEquals(0, songManager.getAlbums().size(), "No albums should be present initially.");

        Album album = songManager.addAlbum();
        assertEquals(1, songManager.getAlbums().size(), "One album should be present after adding.");
        assertEquals("Test Album", album.getAlbumName(), "Album name should match.");
        assertEquals("This is a test album.", album.getAlbumDescription(), "Album description should match.");
    }

    @Test
    public void testAddSong() {
        Album album = new Album("Test Album", "This is a test album.");
        songManager.getAlbums().add(album);

        when(scannerInputMock.readNextLine("Enter the song name: ")).thenReturn("Song 1");
        when(scannerInputMock.readNextLine("Enter the artist name: ")).thenReturn("Artist 1");
        when(scannerInputMock.readNextInt("Enter the song length (seconds): ")).thenReturn(200);
        when(scannerInputMock.readNextLine("Enter the published date (yyyy-MM-dd): ")).thenReturn("2023-10-01");

        assertEquals(0, album.getSongs().size(), "No songs should be present initially.");

        songManager.addSong(album);
        assertEquals(1, album.getSongs().size(), "One song should be present after adding.");
        Song song = album.getSongs().get(0);
        assertEquals("Song 1", song.getSongName(), "Song name should match.");
        assertEquals("Artist 1", song.getSongArtist(), "Song artist should match.");
        assertEquals(200, song.getLength(), "Song length should match.");
        assertEquals(LocalDate.of(2023, 10, 1), song.getPublishDate(), "Song publish date should match.");
    }

    @Test
    public void testUpdateSong() {
        Album album = new Album("Test Album", "This is a test album.");
        songManager.getAlbums().add(album);

        Song song = new Song("Song 1", "Artist 1", 200, LocalDate.of(2023, 10, 1));
        songManager.getSongs().add(song);

        when(scannerInputMock.readNextLine("Enter the new song name (press Enter key to skip): ")).thenReturn("Updated Song");
        when(scannerInputMock.readNextLine("Enter the new artist name (press Enter key to skip): ")).thenReturn("Updated Artist");
        when(scannerInputMock.readNextLine("Enter the new song length in seconds (press Enter key to skip): ")).thenReturn("300");
        when(scannerInputMock.readNextLine("Enter the new published date (yyyy-MM-dd) (press Enter key to skip): ")).thenReturn("2023-11-01");

        songManager.updateSong(album);

        assertEquals("Updated Song", song.getSongName(), "Updated song name should match.");
        assertEquals("Updated Artist", song.getSongArtist(), "Updated song artist should match.");
        assertEquals(300, song.getLength(), "Updated song length should match.");
        assertEquals(LocalDate.of(2023, 11, 1), song.getPublishDate(), "Updated song publish date should match.");
    }

    @Test
    public void testDeleteSong() {
        Album album = new Album("Test Album", "This is a test album.");
        songManager.getAlbums().add(album);

        Song song = new Song("Song 1", "Artist 1", 200, LocalDate.of(2023, 10, 1));
        SongManager.getSongs().add(song);

        when(scannerInputMock.readNextInt("Enter your option: ")).thenReturn(1);

        assertEquals(1, album.getSongs().size(), "One song should be present initially.");

        songManager.deleteSong(album);
        assertEquals(0, album.getSongs().size(), "No songs should be present after deleting.");
    }
}
/*
 * End of controllers.SongManagerTest Class.
 * Checked by Fan Xinkang on 2025/04/06.
 */