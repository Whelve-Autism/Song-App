package controllers;

import controllers.Playlist;
import models.Song;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest {
    Playlist playList, emptyPlayList;
    Song validSong1, validSong2, validSong3;
    ArrayList<Song> songs1, songsNoVerified;

    @BeforeEach
    void setUp() {
        emptyPlayList = new Playlist("no songs here", "no songs at all");
        playList = new Playlist("Chill-24", "Chillout songs to study to");
        validSong1 = new Song(1500, "Anti-Hero", "Taylor Swift", true,  123);
        validSong2 = new Song(9999, "Calm Down", "Rema", false, 600);
        validSong3 = new Song(1000, "12345678901234567890", "Taylor Swift", true, 1);
        songs1 = new ArrayList<Song>();
        songs1.add(validSong1);
        songs1.add(validSong2);
        songs1.add(validSong3);
        songsNoVerified = new ArrayList<Song>();
        songsNoVerified.add(validSong2);
        songsNoVerified.add(new Song(123, "Not great", "unverified artist", false, 300 ));

    }

    @AfterEach
    void tearDown() {
        playList = null;
    }

    @Nested
    class Constructors {

        @Test
        void constructorTestingTheSettingOfDefaultValues() {
            //testing for string values over the max length
            Playlist playlist = new Playlist("12345678901234567890XXX", "123456789012345678901234567890sss");
            assertEquals("12345678901234567890", playlist.getPlaylistName());
            assertEquals("123456789012345678901234567890", playlist.getDescription());
        }

        @Test
        void constructorTestingValidValues() {
            //testing for string values on max length
            Playlist playlist1 = new Playlist("12345678901234567890", "123456789012345678901234567890");
            assertEquals("12345678901234567890", playlist1.getPlaylistName());
            assertEquals("123456789012345678901234567890", playlist1.getDescription());
            //testing for string values just below string length
            Playlist playlist2 = new Playlist("1234567890123456789", "12345678901234567890123456789");
            assertEquals("1234567890123456789", playlist2.getPlaylistName());
            assertEquals("12345678901234567890123456789", playlist2.getDescription());
        }

    }
    @Nested
    class GettersAndSetters {

        @Test
        void playlistNameGetAndSetWorkingCorrectly() {
            // playList = new controllers.Playlist("Chill-24", "Chillout songs to study to");
            assertEquals("Chill-24", playList.getPlaylistName());
            playList.setPlaylistName(""); // valid so change should be made
            assertEquals("", playList.getPlaylistName());
            playList.setPlaylistName("Exams-24");// valid so change should be mad
            assertEquals("Exams-24", playList.getPlaylistName());
            playList.setPlaylistName("12345678901234567890XXX"); // too long no change should be made
            assertEquals("Exams-24", playList.getPlaylistName());
        }
        @Test
        void songsGetAndSetWorkingCorrectly() {
           assertTrue(playList.getSongs().isEmpty());
           playList.setSongs(songs1);
           assertEquals(songs1, playList.getSongs());
        }
        @Test
        void descriptionGetAndSetWorkingCorrectly() {
            assertEquals("Chillout songs to study to", playList.getDescription());
            playList.setDescription("Not so Chillout..."); //this change should work
            assertEquals("Not so Chillout...", playList.getDescription());
            playList.setDescription("123456789012345678901234567890"); //this change should work
            assertEquals("123456789012345678901234567890", playList.getDescription());
            playList.setDescription("Not so Chillout..."); //this change should work
            playList.setDescription("123456789012345678901234567890xxx"); //this change should NOT  work - too long
            assertEquals("Not so Chillout...", playList.getDescription());
        }

    }
    @Test
    void likesGetAndSetWorkingCorrectly() {
        assertEquals(0, playList.getLikes());
        playList.setLikes(4);
        assertEquals(4, playList.getLikes());

        playList.setLikes(-5);  // invalid
        assertEquals(4, playList.getLikes());

        playList.setLikes(0);  //valid
        assertEquals(0, playList.getLikes());

        playList.setLikes(-1);  //invalid
        assertEquals(0, playList.getLikes());
    }



        @Test
        void addLike() {
        assertEquals(0, playList.getLikes());
        playList.addLike();
        assertEquals(1, playList.getLikes());
        playList.setLikes(7);
        assertEquals(7, playList.getLikes());
        playList.addLike();
        assertEquals(8, playList.getLikes());

        }

        @Test
        void addSong(){
        assertTrue(emptyPlayList.getSongs().isEmpty());
        emptyPlayList.addSong(validSong1);
        assertEquals(validSong1, emptyPlayList.findSong(0));
        emptyPlayList.addSong(validSong2);
        assertEquals(validSong2, emptyPlayList.findSong(1));
        }

        @Test
        void deleteSong(){
            assertTrue(emptyPlayList.getSongs().isEmpty());
            assertNull(emptyPlayList.deleteSong(0));   // returns null when index not there

            playList.setSongs(songs1); // we will use a populated arraylist
            assertEquals(validSong1, playList.deleteSong(0)); // deletes and return deleted object
            assertEquals(validSong2, playList.findSong(0));  // validSong2 gone to position 0
            assertEquals(validSong3, playList.deleteSong(1)); // deletes and return deleted object
            assertEquals(validSong2, playList.findSong(0));

        }
        @Test
        void updateSong(){
        playList.setSongs(songs1);
        Song updatedSong =     new Song(445, "Top song", "Fine singer", true, 234);
        playList.updateSong(1, updatedSong);
        assertEquals(updatedSong, playList.getSongs().get(1));

        //invalid index returns false
        assertFalse(playList.updateSong(-1, validSong3));
        assertFalse(playList.updateSong(3, validSong3));

        }
        @Test
        void listSongs() {
        assertTrue(emptyPlayList.listSongs().contains("No songs in playlist."));
        playList.setSongs(songs1);
        assertTrue(playList.listSongs().contains("Songs from playlist :"));
        assertTrue(playList.listSongs().contains("Chill-24"));
        assertTrue(playList.listSongs().contains("1500"));
        assertTrue(playList.listSongs().contains("Anti-Hero"));
        assertTrue(playList.listSongs().contains("Taylor Swift"));
        assertTrue(playList.listSongs().contains("123"));
        assertTrue(playList.listSongs().contains("9999"));
        assertTrue(playList.listSongs().contains("Calm Down"));
        assertTrue(playList.listSongs().contains("Rema"));
        assertTrue(playList.listSongs().contains("600"));

        assertTrue(playList.listSongs().contains("1000"));
        assertTrue(playList.listSongs().contains("12345678901234567890"));
        assertTrue(playList.listSongs().contains("Taylor Swift"));
        assertTrue(playList.listSongs().contains("1"));
        }


        @Test
        void listVerifiedSongs() {
            assertTrue(emptyPlayList.listSongsFromVerifiedArtists().contains("No songs in playlist."));

            playList.setSongs(songsNoVerified);

            assertTrue(playList.listSongsFromVerifiedArtists().contains("There are no songs from verified artists on this playlist"));

            playList.setSongs(songs1);
            assertTrue(playList.listSongsFromVerifiedArtists().contains("Anti-Hero"));
            assertTrue(playList.listSongsFromVerifiedArtists().contains("Taylor Swift"));
            assertTrue(playList.listSongsFromVerifiedArtists().contains("12345678901234567890"));

        }

        @Test
        void listSongsLongerThan() {
            ArrayList<Song> songs = new ArrayList<Song>();

            songs.add( new Song(1500, "Anti-Hero", "Taylor Swift", true,  123));
            songs.add(new Song(9999, "Calm Down", "Rema", false, 550));
            songs.add( new Song(1000, "12345678901234567890", "Taylor Swift", true, 50)) ;
            playList.setSongs(songs);


            assertTrue(emptyPlayList.listSongsLongerThan(00).contains("No songs in playlist."));  // check empty list

            assertTrue(playList.listSongsLongerThan(560).contains("There are no songs on this playlist longer than :"));  // no songs longer than 560
            assertTrue(playList.listSongsLongerThan(560).contains("560"));
            assertTrue(playList.listSongsLongerThan(560).contains("secs"));

            assertTrue(playList.listSongsLongerThan(500).contains("9999"));
            assertTrue(playList.listSongsLongerThan(500).contains("Calm Down"));
            assertTrue(playList.listSongsLongerThan(500).contains("Rema"));
            assertTrue(playList.listSongsLongerThan(500).contains("550"));

            assertFalse(playList.listSongsLongerThan(500).contains("Anti-Hero"));  //ensure that song length 123 not listed

            assertFalse(playList.listSongsLongerThan(500).contains("12345678901234567890"));

            assertTrue(playList.listSongsLongerThan(100).contains("9999"));
            assertTrue(playList.listSongsLongerThan(100).contains("Calm Down"));
            assertTrue(playList.listSongsLongerThan(100).contains("Rema"));
            assertTrue(playList.listSongsLongerThan(100).contains("550"));

            assertTrue(playList.listSongsLongerThan(100).contains("1500"));
            assertTrue(playList.listSongsLongerThan(100).contains("Anti-Hero"));
            assertTrue(playList.listSongsLongerThan(100).contains("Taylor Swift"));
            assertTrue(playList.listSongsLongerThan(100).contains("123"));

            assertFalse(playList.listSongsLongerThan(500).contains("12345678901234567890")); //ensure that song length 50 not listed

            assertFalse(playList.listSongsLongerThan(500).contains("Anti-Hero"));  //ensure that song length 123 not listed

            assertFalse(playList.listSongsLongerThan(500).contains("12345678901234567890"));

            assertTrue(playList.listSongsLongerThan(20).contains("12345678901234567890"));

        }

        @Test
        void listOfSongsOfArtist() {
            assertTrue(emptyPlayList.listOfSongsOfArtist("Taylor Swift").contains("No songs in playlist."));  // check empty list
            playList.setSongs(songs1);

            assertTrue(playList.listOfSongsOfArtist("Taylor Swift").contains("Anti-Hero"));  // check are both songs returned
            assertTrue(playList.listOfSongsOfArtist("Taylor Swift").contains("12345678901234567890"));

            assertTrue(playList.listOfSongsOfArtist("Mairead Taylor").contains("There are no  songs on this playlist by"));
            assertTrue(playList.listOfSongsOfArtist("Mairead Taylor").contains("Mairead Taylor"));

        }

        @Test
        void getAverageSongLength() {
        playList.setSongs(songs1);
        assertEquals(241, playList.getAverageSongLength());
        assertEquals(-1, emptyPlayList.getAverageSongLength());  // no songs - should return -1
        }

        @Test
        void findSong() {
        playList.setSongs(songs1);
        assertEquals(validSong1, playList.findSong(0));
        assertEquals(validSong2, playList.findSong(1));
        assertEquals(validSong3, playList.findSong(2));
        assertNull(playList.findSong(-1));
        assertNull(playList.findSong(3));
        }

        @Test
        void isValidIndex() {
        playList.setSongs(songs1);
        assertEquals(3, playList.numSongs());
        assertTrue(playList.isValidIndex(0));
        assertTrue(playList.isValidIndex(1));
        assertTrue(playList.isValidIndex(2));
        assertFalse(playList.isValidIndex(-1));
        assertFalse(playList.isValidIndex(playList.numSongs()));
        }

        @Test
        void testToString() {
            playList.setSongs(songs1);
            assertTrue(playList.toString().contains("controllers.Playlist Name"));
            assertTrue(playList.toString().contains("Chill-24"));  //playlist name
            assertTrue(playList.toString().contains("controllers.Playlist Description"));
            assertTrue(playList.toString().contains("Chillout songs to study to"));
            assertTrue(playList.toString().contains("Songs"));
            assertTrue(playList.toString().contains("Anti-Hero"));// songname
            assertTrue(playList.toString().contains("Taylor Swift"));// artist name
            assertTrue(playList.toString().contains("is a verified artist"));//is artist verified
            assertTrue(playList.toString().contains("Calm Down"));//songname
            assertTrue(playList.toString().contains("Rema"));//artist name
            assertTrue(playList.toString().contains("is not a verified artist"));//is artist verified
            //check empty list
            assertTrue(emptyPlayList.toString().contains("No songs in playlist."));

        }

}