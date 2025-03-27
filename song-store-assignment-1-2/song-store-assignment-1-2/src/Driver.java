import controllers.Playlist;
import models.Artist;
import models.Song;
import utils.ScannerInput;

import java.util.Scanner;

public class Driver {

    //TODO Define an object of the Playlist here.
    //     It should be declared private.
    private static Playlist playlist;

    public static void main(String[] args) {
        new Driver();
        runMenu();
    }

    //TODO Refer to the tutors instructions for building this class and for the menu.
    //     You are free to deviate in any way from the Driver menu that is in the tutors instructions, once you have these included:
    //     (with tests still compiling)
    //       - CRUD on Playlist
    //       - Search facility (for Songs)
    //       - Reports
    //       - Persistence
    // Note:  This is the ONLY class that can talk to the user
    //     i.e. have System.out.print and Scanner reads in it.
    public Driver() {
        playlist = new Playlist("My Playlist", "My Playlist Description");
    }

    //----------------------------------------------------------------------------
    // Private methods for displaying the menu and processing the selected options
    //----------------------------------------------------------------------------
    private static int displayMenu() {
        System.out.println("""
      * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
      *                          SONGS APP                          *
      * SONG MENU                                                   *
      *    1) Add a Song                                            *
      *    2) List all Songs                                        *
      *    3) Update a Song                                         *
      *    4) Delete a Song                                         *
      *    5) Set verified status of a specific song's artist       *
      *    6) Find a specific song (by code)                        *
      *    7) Search for a specific song (by name)                  *
      *    8) Add a like to playlist                                *
      * REPORT MENU                                                 *
      *    9) List all Songs by verified artists                    *
      *    10) List all Songs over a given length                   *
      *    11) List all Songs by a given artist                     *
      *    12) Print the average length of songs in the playlist    *
      *    13) Print the total length of songs in the playlist      *
      * SETTING MENU                                                *
      *    20) Save                                                 *
      *    21) Load                                                 *
      *    0) Exit                                                  *
      * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    ==>>""");
        return ScannerInput.readNextInt("Please enter an option: ");
    }

    public static void runMenu() {
        int option = displayMenu();
        while (option != 0) {

            /*
             * try-catch 环绕包装。
             * Try-catch wrap-around packaging.
             */
            try {
                switch (option) {
                    case 1:
                        addSong();
                        break;
                    case 2:
                        listAllSongs();
                        break;
                    case 3:
                        updateSong();
                        break;
                    case 4:
                        deleteSong();
                        break;
                    case 5:
                        setVerifiedStatus();
                        break;
                    case 6:
                        findSongById();
                        break;
                    case 7:
                        searchSongByName();
                        break;
                    case 8:
                        addLikeToPlaylist();
                        break;
                    case 9:
                        listSongsByVerifiedArtists();
                        break;
                    case 10:
                        listSongsOverGivenLength();
                        break;
                    case 11:
                        listSongsOfGivenArtist();
                        break;
                    case 12:
                        printAverageLength();
                        break;
                    case 13:
                        printLengthOfPlaylist();
                        break;
                    case 20:
//                    save();
                        break;
                    case 21:
//                    load();
                        break;
                    default:
                        System.out.println("Please enter a valid number.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            /*
             * 虚拟读取，以清除扫描仪类中的缓冲区-错误。
             * Dummy read to clear the buffer-bug in Scanner class.
             */
            System.out.println("\nPress enter key to continue... ");
            ScannerInput.readNextLine("");
            option = displayMenu();
        }

        /* 选择选项0后，显示结束界面。
         * UserInterface.Display the end interface after selecting option 0.
         */
        System.out.println("The program has been exited... Bye... ");
    }

    //------------------------------------
    // Private methods for CRUD on Song
    //------------------------------------
    private static void addSong() {
        int songId = ScannerInput.readNextInt("Enter song ID (1000-9999): ");
        String name = ScannerInput.readNextLine("Enter song name: ");
        String artistName = ScannerInput.readNextLine("Enter artist name: ");
        boolean verified = ScannerInput.readNextChar("Is the artist verified? (y/n): ") == 'y';
        int length = ScannerInput.readNextInt("Enter song length (1-600): ");
        Song song = new Song(songId, name, artistName, verified, length);
        if (playlist.addSong(song)) {
            System.out.println("Song added successfully.");
        } else {
            System.out.println("Failed to add song.");
        }
    }

    private static void listAllSongs() {
        if (playlist == null) {
            System.out.println("Playlist is null.");
        } else {
            System.out.println(playlist.listSongs());
        }
    }

    private static void updateSong() {
        int index = ScannerInput.readNextInt("Enter the index of the song to update: ");
        int songId = ScannerInput.readNextInt("Enter new song ID (1000-9999): ");
        String name = ScannerInput.readNextLine("Enter new song name: ");
        String artistName = ScannerInput.readNextLine("Enter new artist name: ");
        boolean verified = ScannerInput.readNextChar("Is the new artist verified? (y/n): ") == 'y';
        int length = ScannerInput.readNextInt("Enter new song length (1-600): ");
        Song song = new Song(songId, name, artistName, verified, length);
        if (playlist.updateSong(index, song)) {
            System.out.println("Song updated successfully.");
        } else {
            System.out.println("Failed to update song.");
        }
    }

    private static void deleteSong() {
        int index = ScannerInput.readNextInt("Enter the index of the song to delete: ");
        Song deletedSong = playlist.deleteSong(index);
        if (deletedSong != null) {
            System.out.println("Song deleted successfully: " + deletedSong);
        } else {
            System.out.println("Failed to delete song.");
        }
    }

    //-----------------------------------------------------------------
    //  Private methods for Search facility
    //-----------------------------------------------------------------
    private static void setVerifiedStatus() {
        int index = ScannerInput.readNextInt("Enter the index of the song to update verified status: ");
        boolean verified = ScannerInput.readNextChar("Set verified status (y/n): ") == 'y';
        Song updatedSong = playlist.updateVerifiedStatus(index, verified);
        if (updatedSong != null) {
            System.out.println("Verified status updated successfully: " + updatedSong);
        } else {
            System.out.println("Failed to update verified status.");
        }
    }

    private static void findSongById() {
        int songCode = ScannerInput.readNextInt("Enter the song code to find: ");
        Song song = playlist.findSongByCode(songCode);
        if (song != null) {
            System.out.println("Song found: " + song);
        } else {
            System.out.println("Song not found.");
        }
    }

    private static void searchSongByName() {
        String name = ScannerInput.readNextLine("Enter the song name to search: ");
        System.out.println(playlist.searchSongsByName(name));
    }

    private static void addLikeToPlaylist() {
        playlist.addLike();
        System.out.println("Like added to playlist.");
    }

    //-----------------------------
    //  Private methods for Reports
    // ----------------------------
    private static void listSongsByVerifiedArtists() {
        System.out.println(playlist.listSongsFromVerifiedArtists());
    }

    private static void listSongsOverGivenLength() {
        int length = ScannerInput.readNextInt("Enter the minimum song length: ");
        System.out.println(playlist.listSongsLongerThan(length));
    }

    private static void listSongsOfGivenArtist() {
        String artistName = ScannerInput.readNextLine("Enter the artist name: ");
        System.out.println(playlist.listOfSongsOfArtist(artistName));
    }

    //---------------------------------
    //  Private methods for Persistence
    // --------------------------------
    private static void printAverageLength() {
        int averageLength = playlist.getAverageSongLength();
        if (averageLength == -1) {
            System.out.println("The playlist is empty.");
        } else {
            System.out.println("The average length of songs in the playlist is: " + averageLength + " seconds.");
        }
    }

    private static void printLengthOfPlaylist() {
        int totalLength = playlist.getTotalPlayListLength();
        if (totalLength == -1) {
            System.out.println("The playlist is empty.");
        } else {
            System.out.println("The total length of songs in the playlist is: " + totalLength + " seconds.");
        }
    }

    //TODO Add a method, load().  The return type is void.
    //    This method uses the XStream component to deserialise the playList object and their associated artists from
    //    an XML file into the Songs array list.


    //TODO Add a method, save().  The return type is void.
    //    This method uses the XStream component to serialise the playList object and their associated artists to
    //    an XML file.


}