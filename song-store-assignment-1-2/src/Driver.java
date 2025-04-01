import controllers.Playlist;
import models.Artist;
import models.Song;
import utils.ScannerInput;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.*;

public class Driver {

    //TODO Define an object of the Playlist here.
    //     It should be declared private.
    private static Playlist playlist;

    /*
      程序入口。
      Main program entry point.
     */
    public static void main(String[] args) {
        new Driver();
        runMenu();
    }

    //TODO Refer to the tutors instructions for building this class and for the menu.
    //     You are free to deviate in any way from the Driver menu that is in the tutors instructions, once you have these included:
    //     (with tests still compiling)
    //      - CRUD on Playlist
    //      - Search facility (for Songs)
    //      - Reports
    //      - Persistence
    // Note: This is the ONLY class that can talk to the user.
    //     i.e. have System.out.print and Scanner reads in it.

    /*
      初始化 Playlist。
      Initialize Playlist.
     */
    public Driver() {
        playlist = new Playlist("My Playlist", "My Playlist Description");
    }

    //----------------------------------------------------------------------------
    // Private methods for displaying the menu and processing the selected options
    //----------------------------------------------------------------------------

    /*
      显示菜单。
      Display menu.
     */
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

    /*
      运行菜单。
      Run menu.
     */
    public static void runMenu() {
        int option = displayMenu();

        while (option != 0) {

            /*
              try-catch 环绕包装。
              Try-catch wrap-around packaging.
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
                        save();
                        break;
                    case 21:
                        load();
                        break;
                    default:
                        System.out.println("Please enter a valid number.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            /*
              虚拟读取，以清除扫描仪类中的缓冲区-错误。
              Dummy read to clear the buffer-bug in Scanner class.
             */
            System.out.println("\nPress enter key to continue... ");
            ScannerInput.readNextLine("");
            option = displayMenu();
        }

        System.out.println("The program has been exited... Bye... ");
    }

    //------------------------------------
    // Private methods for CRUD on Song
    //------------------------------------

    /*
      添加歌曲。
      Add a song.
     */
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

    /*
      列出所有歌曲。
      List all songs.
     */
    private static void listAllSongs() {
        if (playlist == null) {
            System.out.println("Playlist is null.");
        } else {
            System.out.println(playlist.listSongs());
        }
    }

    /*
      更新歌曲。
      Update a song.
     */
    private static void updateSong() {
        int index = ScannerInput.readNextInt("Enter the index of the song to update: ");

        if (!playlist.isValidIndex(index)) {
            System.out.println("Invalid index, so keep the original value.");
            return;
        }

        int songId = ScannerInput.readNextInt("Enter new song ID (1000-9999): ");

        if (songId < 1000 || songId > 9999) {
            System.out.println("Invalid song ID, so keep the original value.");
            return;
        }

        String name = ScannerInput.readNextLine("Enter new song name: ");

        if (name.length() > 20) {
            System.out.println("Invalid song name, so keep the original value.");
            return;
        }

        String artistName = ScannerInput.readNextLine("Enter new artist name: ");

        if (artistName.length() > 15) {
            System.out.println("Invalid artist name, so keep the original value.");
            return;
        }

        Song existingSong = playlist.getSongs().get(index);
        boolean verified = existingSong.isVerified();
        int length = ScannerInput.readNextInt("Enter new song length (1-600): ");

        if (length <= 0 || length > 600) {
            System.out.println("Invalid song length, so keep the original value.");
            return;
        }

        Artist artist = new Artist(artistName, verified);
        Song song = new Song(songId, name, artistName, verified, length);

        playlist.updateSong(index, song);
        System.out.println("Song updated successfully.");
    }


    /*
      删除歌曲。
      Delete a song.
     */
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

    /*
      更新验证状态。
      Update the verified status.
     */
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

    /*
      通过 ID 搜索歌曲。
      Search a song by ID.
     */
    private static void findSongById() {
        int songCode = ScannerInput.readNextInt("Enter the song code to find: ");
        Song song = playlist.findSongByCode(songCode);

        if (song != null) {
            System.out.println("Song found: " + song);
        } else {
            System.out.println("Song not found.");
        }
    }

    /*
      通过名称搜索歌曲。
      Search a song by name.
     */
    private static void searchSongByName() {
        String name = ScannerInput.readNextLine("Enter the song name to search: ");
        System.out.println(playlist.searchSongsByName(name));
    }

    /*
      添加喜欢。
      Add a like.
     */
    private static void addLikeToPlaylist() {
        playlist.addLike();
        System.out.println("Like added to playlist.");
    }

    //-----------------------------
    //  Private methods for Reports
    // ----------------------------

    /*
      列出所有已验证的歌手的曲目。
      List all songs by verified artists.
     */
    private static void listSongsByVerifiedArtists() {
        System.out.println(playlist.listSongsFromVerifiedArtists());
    }

    /*
      列出所有超过给定长度的曲目。
      List all songs longer than a given length.
     */
    private static void listSongsOverGivenLength() {
        int length = ScannerInput.readNextInt("Enter the minimum song length: ");
        System.out.println(playlist.listSongsLongerThan(length));
    }

    /*
      列出给定歌手的曲目。
      List all songs by a given artist.
     */
    private static void listSongsOfGivenArtist() {
        String artistName = ScannerInput.readNextLine("Enter the artist name: ");
        System.out.println(playlist.listOfSongsOfArtist(artistName));
    }

    //---------------------------------
    //  Private methods for Persistence
    // --------------------------------

    /*
      打印平均长度。
      Print the average length of songs in the playlist.
     */
    private static void printAverageLength() {
        int averageLength = playlist.getAverageSongLength();

        if (averageLength == -1) {
            System.out.println("The playlist is empty.");
        } else {
            System.out.println("The average length of songs in the playlist is: " + averageLength + " seconds.");
        }
    }

    /*
      打印播放列表的总长度。
      Print the total length of songs in the playlist.
     */
    private static void printLengthOfPlaylist() {
        int totalLength = playlist.getTotalPlayListLength();

        if (totalLength == -1) {
            System.out.println("The playlist is empty.");
        } else {
            System.out.println("The total length of songs in the playlist is: " + totalLength + " seconds.");
        }
    }

    //TODO Add a method, load().
    //     The return type is void.
    //     This method uses the XStream component to deserialise the playList object and their associated artists from an XML file into the Songs array list.
    @SuppressWarnings("unchecked")
    public static void load() throws Exception {
        XStream xstream = XStreamCreating();
        try (FileInputStream file = new FileInputStream("Store.xml")) {
            playlist = (Playlist) xstream.fromXML(file);
        } catch (Exception e) {
            System.err.println("Error loading playlist: " + e.getMessage());
            throw e;
        }
    }

    //TODO Add a method, save().
    //     The return type is void.
    //     This method uses the XStream component to serialise the playList object and their associated artists to an XML file.
    public static void save() throws Exception {
        XStream xstream = XStreamCreating();
        try (FileOutputStream file = new FileOutputStream("Store.xml")) {
            xstream.toXML(playlist, file);
        } catch (Exception e) {
            System.err.println("Error saving playlist: " + e.getMessage());
            throw e;
        }
    }

    private static XStream XStreamCreating() {
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("song", Song.class);
        xstream.alias("artist", Artist.class);
        xstream.alias("playlist", Playlist.class);
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(new Class[]{Song.class, Artist.class, Playlist.class});
        return xstream;
    }
}