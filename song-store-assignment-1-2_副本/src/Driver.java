import controllers.Playlist;
import models.Artist;
import models.Song;
import utils.ScannerInput;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.*;

import static UserInterface.Display.*;

/**
 * 此类用于运行 Playlist 应用程序来管理歌曲。
 * This class runs the Playlist application to manage songs.
 *
 * @author Fan Xinkang, Xu Shiyi, Lu Siyu
 * @version 5.0
 * @since version 5.0
 */
public class Driver {

    private static Playlist playlist;

    /**
     * 程序入口。
     * Main program entry point.
     *
     * @param args 命令行参数。
     *             The command line arguments.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 2.0
     */
    public static void main(String[] args) {
        new Driver();
        runMenu();
    }

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

    /**
     * 显示菜单。
     * Display menu.
     *
     * @return 根据菜单内容返回的选项。
     *         Return the selected option according to the menu.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 2.0
     */
    private static int displayMenu() {
        printlnRandomColor("""
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

    /**
     * 运行菜单，根据选项执行操作。
     * Run menu, process the selected options.
     *
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 3.1
     */
    public static void runMenu() {
        beginDisplay();

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
                        printlnRandomColor("Please enter a valid number.");
                }
            } catch (Exception e) {
                printlnRandomColor("Error: " + e.getMessage());
            }

            /*
              虚拟读取，以清除扫描仪类中的缓冲区-错误。
              Dummy read to clear the buffer-bug in Scanner class.
             */
            printlnRandomColor("\nPress enter key to continue... ");
            ScannerInput.readNextLine("");
            option = displayMenu();
        }
        endDisplay();
    }

    //------------------------------------
    // Private methods for CRUD on Song
    //------------------------------------

    /**
     * 验证传入的 Song。
     * Validate Song object.
     *
     * @param existingSong 要验证和更新的 Song 对象。
     *                     The Song object to be validated and updated.
     * @param isAddingNew 判断是否为新增歌曲。
     *                    Judge whether the song is being added as new.
     * @return 验证并更新后的 Song 对象。
     *         The validated and updated Song object.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 5.0
     */
    private static Song validateSong(Song existingSong, boolean isAddingNew) {
        Song song = new Song(
                existingSong.getSongId(),
                existingSong.getName(),
                existingSong.getArtist().getArtistName(),
                existingSong.getArtist().isVerified(),
                existingSong.getLength()
        );

        /*
          验证 songId。
          Validate songId.
         */
        int songId;
        String songIdSet = "Invalid";
        while (songIdSet.equals("Invalid")) {
            String prompt;
            if (isAddingNew) {
                prompt = "Enter song ID (1000-9999).";
            } else {
                prompt = "Enter song ID (1000-9999) (or press Enter to skip).";
            }
            String songIdInput = ScannerInput.readNextLine(prompt);

            if (songIdInput.isEmpty()) {
                if (!isAddingNew) {
                    songIdSet = "Valid";
                } else {
                    printlnRandomColor("Invalid enter. Please re-enter.");
                }
            } else {
                try {
                    songId = Integer.parseInt(songIdInput);
                    if (songId < 1000 || songId > 9999) {
                        printlnRandomColor("Invalid song ID. Please enter a song ID between 1000 and 9999.");
                    } else {
                        song.setSongId(songId);
                        songIdSet = "Valid";
                    }
                } catch (NumberFormatException e) {
                    printlnRandomColor("Invalid song ID. Please enter a song ID between 1000 and 9999.");
                }
            }
        }

        /*
          验证 name。
          Validate name.
         */
        String name;
        String nameSet = "Invalid";
        while (nameSet.equals("Invalid")) {
            String prompt;
            if (isAddingNew) {
                prompt = "Enter song name.";
            } else {
                prompt = "Enter song name (or press Enter to skip).";
            }
            String nameInput = ScannerInput.readNextLine(prompt);

            if (!nameInput.isEmpty()) {
                if (nameInput.length() > 20) {
                    printlnRandomColor("Invalid song name. Please enter a name with 20 characters or less.");
                } else {
                    name = nameInput;
                    song.setName(name);
                    nameSet = "Valid";
                }
            } else {
                if (!isAddingNew) {
                    nameSet = "Valid";
                } else {
                    printlnRandomColor("Invalid enter. Please re-enter.");
                }
            }
        }

        /*
          验证 artistName。
          Validate artistName.
         */
        String artistName;
        String artistNameSet = "Invalid";
        while (artistNameSet.equals("Invalid")) {
            String prompt;
            if (isAddingNew) {
                prompt = "Enter artist name.";
            } else {
                prompt = "Enter artist name (or press Enter to skip).";
            }
            String artistNameInput = ScannerInput.readNextLine(prompt);

            if (!artistNameInput.isEmpty()) {
                if (artistNameInput.length() > 15) {
                    printlnRandomColor("Invalid artist name. Please enter a name with 15 characters or less.");
                } else {
                    artistName = artistNameInput;
                    song.getArtist().setArtistName(artistName);
                    artistNameSet = "Valid";
                }
            } else {
                if (!isAddingNew) {
                    artistNameSet = "Valid";
                } else {
                    printlnRandomColor("Invalid enter. Please re-enter.");
                }
            }
        }

        /*
          验证 verified。
          Validate verified.
         */
        boolean verified;
        String verifiedSet = "Invalid";
        while (verifiedSet.equals("Invalid")) {
            String prompt;
            if (isAddingNew) {
                prompt = "Set verified status (y/n).";
            } else {
                prompt = "Set verified status (y/n) (or press Enter to skip).";
            }
            char verifiedInput = ScannerInput.readNextChar(prompt);

            if (verifiedInput == ' ') {
                if (!isAddingNew) {
                    verifiedSet = "Valid";
                } else {
                    printlnRandomColor("Invalid enter. Please re-enter.");
                }
            } else if (verifiedInput == 'y' || verifiedInput == 'n') {
                verified = verifiedInput == 'y';
                song.getArtist().setVerified(verified);
                verifiedSet = "Valid";
            } else {
                printlnRandomColor("Invalid input. Please enter 'y' or 'n'.");
            }
        }

        /*
          验证 length。
          Validate length.
         */
        int length = -1;
        String lengthSet = "Invalid";
        while (lengthSet.equals("Invalid")) {
            String prompt;
            if (isAddingNew) {
                prompt = "Enter song length.";
            } else {
                prompt = "Enter song length (or press Enter to skip).";
            }
            String lengthInput = ScannerInput.readNextLine(prompt);

            if (!lengthInput.isEmpty()) {
                try {
                    length = Integer.parseInt(lengthInput);
                    if (length < 1 || length > 600) {
                        printlnRandomColor("Invalid enter. Please re-enter.");
                    } else {
                        song.setLength(length);
                        lengthSet = "Valid";
                    }
                } catch (NumberFormatException e) {
                    printlnRandomColor("Invalid song length. Please enter a number between 1 and 600.");
                }
            } else {
                if (!isAddingNew) {
                    lengthSet = "Valid";
                } else {
                    printlnRandomColor("Invalid enter. Please re-enter.");
                }
            }
        }
        return song;
    }

    /**
     * 添加歌曲。
     * Add a song.
     *
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 5.0
     */
    private static void addSong() {

        /*
          初始化 existingSong，并调用 validateSong() 验证歌曲。
          Initialize existingSong and call validateSong() to validate the song.
         */
        Song existingSong = new Song(0, "", "", false, 0);
        Song song = validateSong(existingSong, true);

        if (playlist.addSong(song)) {
            printlnRandomColor("Song added successfully.");
        } else {
            printlnRandomColor("Failed to add song.");
        }
    }

    /**
     * 列出所有的歌曲。
     * List all songs.
     *
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 5.0
     */
    private static void listAllSongs() {
        if (playlist == null) {
            printlnRandomColor("Playlist is null.");
        } else {
            printlnRandomColor(playlist.listSongs());
        }
    }

    /**
     * 更新歌曲。
     * Update a song.
     *
     * @author Fan Xinkang.
     * @since version 5.0
     */
    private static void updateSong() {

        /*
          如果播放列表为空，则返回。
          If the playlist is empty, return.
         */
        if (playlist == null || playlist.getSongs().isEmpty()) {
            printlnRandomColor("No songs to update.");
            return;
        }

        printlnRandomColor("Songs available to update:");
        printlnRandomColor(playlist.listSongs());

        /*
          如果输入的索引无效，则返回。
          If the input index is invalid, return.
         */
        int index = ScannerInput.readNextIntWithSkip("Enter the index of the song to update (or press Enter to skip): ");
        if (index == -1) {
            printlnRandomColor("Skipping update.");
            return;
        }

        /*
          实例化 existingSong 和 originalSong，为后续比较使用。
          Instantiate existingSong and originalSong for comparison purposes.
         */
        Song existingSong = playlist.getSongs().get(index);
        Song originalSong = new Song(
                existingSong.getSongId(),
                existingSong.getName(),
                existingSong.getArtist().getArtistName(),
                existingSong.getArtist().isVerified(),
                existingSong.getLength()
        );

        /*
          验证并更新歌曲信息。
          Validate and update song information.
         */
        Song updatedSong = validateSong(existingSong, false);

        /*
          更新 playlist 中的歌曲。
          Update the song in the playlist.
         */
        if (updatedSong.equals(originalSong)) {
            printlnRandomColor("The modified result is the same as the original song..");
        } else {
            playlist.updateSong(index, updatedSong);
            printlnRandomColor("Song updated successfully.");
        }
    }

    /**
     * 删除歌曲。
     * Delete a song.
     *
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 5.0
     */
    private static void deleteSong() {

        if (playlist == null || playlist.getSongs().isEmpty()) {
            printlnRandomColor("No songs to delete.");
            return;
        }

        printlnRandomColor("Songs available to delete:");
        printlnRandomColor(playlist.listSongs());

        int index = ScannerInput.readNextIntWithSkip("Enter the index of the song to delete (or press Enter to skip): ");
        if (index == -1) {
            printlnRandomColor("Skipping delete.");
            return;
        }

        Song deletedSong = playlist.deleteSong(index);

        if (deletedSong != null) {
            printlnRandomColor("Song deleted successfully: " + deletedSong);
        } else {
            printlnRandomColor("Failed to delete song.");
        }
    }


    //-----------------------------------------------------------------
    //  Private methods for Search facility
    //-----------------------------------------------------------------

    /*
      提示：该方法已经含在 updateSong 中，所以在运行时不需要再单独运行。
      Hint: This method is already included in updateSong, so it does not need to be run separately.
     */
    /**
     * 更新验证状态。
     * Update the verified status.
     *
     * @author Fan Xinkang
     * @since version 3.0
     */
    private static void setVerifiedStatus() {
        int index = ScannerInput.readNextInt("Enter the index of the song to update verified status: ");
        boolean verified = ScannerInput.readNextChar("Set verified status (y/n): ") == 'y';
        Song updatedSong = playlist.updateVerifiedStatus(index, verified);

        if (updatedSong != null) {
            printlnRandomColor("Verified status updated successfully: " + updatedSong);
        } else {
            printlnRandomColor("Failed to update verified status.");
        }
    }

    /**
     * 通过 ID 搜索歌曲。
     * Search a song by ID.
     *
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 3.0
     */
    private static void findSongById() {
        int songCode = ScannerInput.readNextInt("Enter the song code to find: ");
        Song song = playlist.findSongByCode(songCode);

        if (song != null) {
            printlnRandomColor("Song found: " + song);
        } else {
            printlnRandomColor("Song not found.");
        }
    }

    /**
     * 通过歌曲名称搜索歌曲。
     * Search a song by name.
     *
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 2.0
     */
    private static void searchSongByName() {
        String name = ScannerInput.readNextLine("Enter the song name to search: ");
        printlnRandomColor(playlist.searchSongsByName(name));
    }

    /**
     * 添加喜欢。
     * Add a like.
     *
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 2.0
     */
    private static void addLikeToPlaylist() {
        playlist.addLike();
        printlnRandomColor("Like added to playlist.");
    }

    //-----------------------------
    //  Private methods for Reports
    // ----------------------------

    /**
     * 列出所有已验证的歌手的曲目。
     * List all songs by verified artists.
     *
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 2.0
     */
    private static void listSongsByVerifiedArtists() {
        printlnRandomColor(playlist.listSongsFromVerifiedArtists());
    }

    /**
     * 列出所有超过给定长度的曲目。
     * List all songs longer than a given length.
     *
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 2.0
     */
    private static void listSongsOverGivenLength() {
        int length = ScannerInput.readNextInt("Enter the minimum song length: ");
        printlnRandomColor(playlist.listSongsLongerThan(length));
    }

    /**
     * 列出给定歌手的曲目。
     * List all songs by a given artist.
     *
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 2.0
     */
    private static void listSongsOfGivenArtist() {
        String artistName = ScannerInput.readNextLine("Enter the artist name: ");
        printlnRandomColor(playlist.listOfSongsOfArtist(artistName));
    }

    //---------------------------------
    //  Private methods for Persistence
    // --------------------------------

    /**
     * 打印平均长度。
     * Print the average length of songs in the playlist.
     *
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 3.0
     */
    private static void printAverageLength() {
        int averageLength = playlist.getAverageSongLength();

        if (averageLength == -1) {
            printlnRandomColor("The playlist is empty.");
        } else {
            printlnRandomColor("The average length of songs in the playlist is: " + averageLength + " seconds.");
        }
    }

    /**
     * 打印播放列表的总长度。
     * Print the total length of songs in the playlist.
     *
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 3.0
     */
    private static void printLengthOfPlaylist() {
        int totalLength = playlist.getTotalPlayListLength();

        if (totalLength == -1) {
            printlnRandomColor("The playlist is empty.");
        } else {
            printlnRandomColor("The total length of songs in the playlist is: " + totalLength + " seconds.");
        }
    }

    /**
     * 创建 XStream 对象，并设置别名。
     * Create an XStream object and set aliases.
     *
     * @return XStream 对象。
     *         XStream object.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 3.1.1
     */
    private static XStream XStreamCreating() {
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("song", Song.class);
        xstream.alias("artist", Artist.class);
        xstream.alias("playlist", Playlist.class);
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(new Class[]{Song.class, Artist.class, Playlist.class});
        return xstream;
    }

    /**
     * 将播放列表加载到 Storage.xml 文件中。
     * Load the playlist from the Storage.xml file.
     *
     * @throws Exception 如果加载失败，则抛出异常。
     *                   If loading fails, an exception is thrown.
     * @see #XStreamCreating()
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 3.1.1
     */
    public static void load() throws Exception {
        XStream xstream = XStreamCreating();
        try (FileInputStream file = new FileInputStream("Store.xml")) {
            printlnRandomColor("Loading playlist...");
            playlist = (Playlist) xstream.fromXML(file);
        } catch (Exception e) {
            System.err.println("Error loading playlist: " + e.getMessage());
            throw e;
        }
    }

    /**
     * 将播放列表保存到 Storage.xml 文件中。
     * Save the playlist to the Storage.xml file.
     *
     * @throws Exception 如果保存失败，则抛出异常。
     *                   If saving fails, an exception is thrown.
     * @see #XStreamCreating()
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 3.1.1
     */
    public static void save() throws Exception {
        XStream xstream = XStreamCreating();
        try (FileOutputStream file = new FileOutputStream("Store.xml")) {
            printlnRandomColor("Saving playlist...");
            xstream.toXML(playlist, file);
        } catch (Exception e) {
            System.err.println("Error saving playlist: " + e.getMessage());
            throw e;
        }
    }
}
/*
 * End of Driver Class.
 * Checked by Fan Xinkang on 2025/04/03.
 */