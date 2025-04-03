package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.Artist;
import models.Song;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * 此类用于控制播放器，并提供 CRUD 操作。
 * This class is used to control the player, and provides CRUD operations.
 *
 * @author Fan Xinkang, Xu Shiyi, Lu Siyu
 * @version 4.4
 * @since version 0.0
 */
public class Playlist {
    private String playlistName = ""; // valid length is 20 - default to the first 20 characters of input.
    private ArrayList<Song> songs = new ArrayList<Song>();  // should start empty
    private String description = ""; // valid length is 30 - default to the first 30 characters of input.
    private int likes = 0;

    /*
      新增判断：验证 descriptionSet 是否被设置过，使得 PlaylistTest 成功编译。
      New judgment: Verify whether descriptionSet has been set, so that PlaylistTest can be successfully compiled.
     */
    private boolean descriptionSet = false;

    /*
      新增判断：验证 playlistNameSet 是否被设置过，使得 PlaylistTest 成功编译。
      New judgment: Verify whether playlistNameSet has been set, so that PlaylistTest can be successfully compiled.
     */
    private boolean playlistNameSet = false;

    /*
      新增判断：验证 addLikeSet 是否被设置过，使得 PlaylistTest 成功编译。
      New judgment: Verify whether addLikeSet has been set, so that PlaylistTest can be successfully compiled.
     */
    private boolean addLikeSet = false;

    //TODO Declare an array list of songs(songs).
    //     This should be empty at the start and does not need to be the constructor.

    //TODO playlist name (String playlistName) of the playlist in the system in the system is entered by the user.
    //     Default value is "".
    //     When creating the playlist, truncate the name to 20 characters.
    //     When updating an existing playlist, only update the name if it is 20 characters or less.

    //TODO The playlist description (String description) of the playlist in the system is entered by the user.
    //     Default value is "".
    //     When creating the playlist, truncate the description to 30 characters.
    //     When updating an existing playlist, only update the description if it is 30 characters or less.

    //TODO The number of likes a playlist has (int likes).
    //     This should start at 0 and not be part of the constructor.

    //TODO Add the constructor, Playlist(String, String), that adheres to the above validation rules.
    //     The order of the fields in the parameter list is the same as the order of fields above.
    //     i.e. playlistName is first, then description.

    /**
     * 构造函数，将 playlistName 和 description 作为参数传入 Playlist。
     * Constructor, passing playlistName and description as parameters to Playlist.
     *
     * @param playlistName 播放器名字。
     *                     Playlist name.
     * @param description 播放器描述。
     *                    Playlist description.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 2.0
     */
    public Playlist(String playlistName, String description) {
        setPlaylistName(playlistName);
        setDescription(description);
    }

    //TODO Add a getter and setter for each field, that adheres to the above validation rules.
    /*
      封装。
      Encapsulation.
     */
    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        if (!playlistNameSet) {
            if (playlistName.length() <= 20) {
                this.playlistName = playlistName;
            } else {
                this.playlistName = playlistName.substring(0, 20);
            }
            playlistNameSet = true;
        } else {
            if (playlistName.length() <= 20) {
                this.playlistName = playlistName;
            }
        }
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (!descriptionSet) {
            if (description.length() <= 30) {
                this.description = description;
            } else {
                this.description = description.substring(0, 30);
            }
            descriptionSet = true;
        } else {
            if (description.length() <= 30) {
                this.description = description;
            }
        }
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        if (!addLikeSet) {
            this.likes = Math.max(likes, 0);
            addLikeSet = true;
        } else {
            if (likes >= 0) {
                this.likes = likes;
            }
        }
    }

    //-------------------------------------
    //  ARRAYLIST CRUD
    //-------------------------------------

    //TODO Add a method, addSong(Song).
    //     The return type is boolean.
    //     This method will add the song object, passed as a parameter to the arraylist of songs.
    //     If the add was successful, return true, otherwise, return false.
    /**
     * 向 songs ArrayList 添加 Song 对象。
     * Add the Song object to songs ArrayList.
     *
     * @param song 想要添加到歌曲 ArrayList 中的 Song 对象。
     *             The Song object you want to add to songs ArrayList.
     * @return 歌曲添加的结果。
     *         The result of adding the song.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 2.0
     */
    public boolean addSong(Song song) {
        return songs.add(song);
    }

    //TODO Add a method, updateSong(int, Song).
    //     The return type is boolean.
    //     This method takes in, as the first parameter, the index of the songs object that you want to update.
    //     If the index is invalid (i.e. there is no song object at that location), return false.
    //     The other parameter is a Song object - that is being updated.
    //     i.e. it holds the new values of id, name, length, and artist.
    //     If the update was successful, then return true.

    /**
     * 更新 songs ArrayList 中的 Song 对象。
     * Update the Song object in songs ArrayList.
     *
     * @param index 歌曲在 ArrayList 中的索引。
     *              The index of the song in the ArrayList.
     * @param song 想要添加到歌曲 ArrayList 中的 Song 对象。
     *             The Song object you want to add to songs ArrayList.
     * @return 歌曲更新的结果。
     *         The result of updating the song.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 3.0
     */
    public boolean updateSong(int index, Song song) {
        if (isValidIndex(index)) {
            Song songToUpdate = songs.get(index);

            if (song.getSongId() > 9999 || song.getSongId() < 1000) {
                return false;
            }

            if (song.getName().length() > 20) {
                return false;
            }

            if (song.getArtist().getArtistName().length() > 15) {
                return false;
            }

            if (song.getLength() <= 0) {
                return false;
            }

            if (songToUpdate.getSongId() == song.getSongId() &&
                    songToUpdate.getName().equals(song.getName()) &&
                    songToUpdate.getLength() == song.getLength() &&
                    songToUpdate.getArtist().getArtistName().equals(song.getArtist().getArtistName())) {
                return false;
            } else {
                songs.set(index, song);
                return true;
            }
        }
        return false;
    }

    //TODO Add a method, deleteSong(int).
    //     The return type is Song.
    //     This method takes in the index of the song object that you want to delete.
    //      - If the index is invalid (i.e. there is no song object at that location), return null.
    //      - If the index is valid, remove the object at that index location.
    //     Return the object you just deleted.
    /**
     * 删除 songs ArrayList 中的 Song 对象。
     * Delete the Song object in songs ArrayList.
     *
     * @param index 歌曲在 ArrayList 中的索引。
     *              The index of the song in the ArrayList.
     * @return 歌曲删除的结果。
     *         The result of deleting the song.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 2.0
     */
    public Song deleteSong(int index) {
        if (isValidIndex(index)) {
            Song deleteSong = songs.get(index);
            songs.remove(index);
            return deleteSong;
        } else {
            return null;
        }
    }

    //TODO  Add a method  addLike() (no parameter) with return type void.
    //      This method simply adds 1 to the likes variable.
    /**
     * 增加歌曲的点赞数。
     * Add a like to the song.
     *
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 2.0
     */
    public void addLike() {
        likes++;
    }

    //-------------------------------------
    //  ARRAYLIST - Utility methods
    //-------------------------------------

//    //TODO Add a method isValidIndex(int) which returns an boolean -
//    //      - returns true if the index is valid for the songs arrayList (in range).
//    //      - returns false otherwise.
//    //      As this method is used inside this class, it should be private.
//    boolean isValidIndex(int index) {
//        return index >= 0 && index < songs.size();
//    }

    /*
      增加验证：我们在 Driver 类中调用，做额外的验证，所以设置成 public。
      Add verification: We call it in the Driver class to do additional verification, so it is set to public.
     */
    /**
     * 判断索引是否有效。
     * Judge whether the index is valid.
     *
     * @param index 歌曲在 ArrayList 中的索引。
     *              The index of the song in the ArrayList.
     * @return index 判断的结果。
     *         The result of the index.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 2.0
     */
    public boolean isValidIndex(int index) {
        return index >= 0 && index < songs.size();
    }

    //TODO  Add a method  findSong(int) which returns a Song object:
    //       - if the supplied index is valid, the Song object at that location is returned
    //       - if the supplied index is invalid, null is returned
    /**
     * 寻找索引对应的歌曲。
     * Find the song corresponding to the index.
     *
     * @param index 歌曲在 ArrayList 中的索引。
     *              The index of the song in the ArrayList.
     * @return 索引对应的歌曲。
     *         The song corresponding to the index.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 2.0
     */
    public Song findSong(int index) {
        if (isValidIndex(index)) {
            return songs.get(index);
        } else {
            return null;
        }
    }

    //TODO  Add a method  findSong(String) which returns a Song object:
    //       - if the supplied string (songName) matches a song name in the songs list, the Song object that matches that name is returned.
    //       - if the supplied string (songName) does not match a song name in the songs list, null is returned.
    // NOTE: If that name appears more than once, it is sufficient to return the first occurence.
    public Song findSong(String songName) {
        for (Song song : songs) {
            if (song.getName().equals(songName)) {
                return song;
            }
        }
        return null;
    }

    //-------------------------------------
    //  ARRAYLIST -  Verified Status Update
    //-------------------------------------

    //TODO Add a method,updateVerifiedStatus(int , boolean ).
    //     The return type is Song.
    //     This method takes in the index of the song object whose artist's verified status you want to update.
    //      - If the index is invalid (i.e. there is no song object at that location), return null.
    //      - If the index is valid, retrieve the object and set the verified status to the parameter value.
    /**
     * 更新歌曲的 verified 状态。
     * Update the verified status of the song.
     *
     * @param index 歌曲在 ArrayList 中的索引。
     *              The index of the song in the ArrayList.
     * @param verified 歌手的验证状态。
     *                 The verified status of the artist.
     * @return 歌手验证状态更新的结果。
     *         The result of updating the verified status of the song.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 2.0
     */
    public Song updateVerifiedStatus(int index, boolean verified) {
        if (isValidIndex(index)) {
            Song song = songs.get(index);
            song.setVerified(verified);
            return song;
        } else {
            return null;
        }
    }

    //-------------------------------------
    //  Counting Methods
    //-------------------------------------

//    //TODO Add a method, numberOfSongs().
//    //     The return type is int.
//    //     This method returns the number of song objects currently stored in the array list.
//    public int numberOfSongs() {
//        return songs.size();
//    }

    /*
      题目勘误：新增 int 类型变量 numSongs，用于返回 songs 数组长度，使得 PlaylistTest 成功编译。
      Title error: The new int variable numSongs is added to return the songs array length, so that PlaylistTest can be successfully compiled.
     */
    /**
     * 返回 songs 数组长度。
     * Return the songs array length.
     *
     * @return songs 数组长度。
     *         The songs array length.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 2.0
     */
    public int numSongs() {
        return songs.size();
    }

    //TODO Add a method, numberOfShortSongs().
    //     The return type is int.
    //     This method returns the number of song objects in the array list that have a length of <= 180.
    public int numberOfShortSongs() {
        int num = 0;

        for (Song song : songs) {
            if (song.getLength() <= 180) {
                num++;
            }
        }
        return num;
    }

    //TODO Add a method getTotalPlayListLength() which returns a integer value of the total time (in seconds) if the there are songs in the playlist.
    //     -1 if playlist is empty.
    /**
     * 返回 songs 数组中所有歌曲的总长度。
     * Return the total length of all songs in the songs array.
     *
     * @return songs 数组中所有歌曲的总长度。
     *         The total length of all songs in the songs array.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 2.0
     */
    public int getTotalPlayListLength() {
        if (songs.isEmpty()) {
            return -1;
        } else {
            int totalLength = 0;

            for (Song song : songs) {
                totalLength += song.getLength();
            }
            return totalLength;
        }
    }

    //TODO Add a method getAverageSongLength() which returns a integer value of the average length of songs on the playlist.
    //     -1 if playlist is empty.
    /**
     * 返回 songs 数组中所有歌曲的平均长度。
     * Return the average length of all songs in the songs array.
     *
     * @return songs 数组中所有歌曲的平均长度。
     *         The average length of all songs in the songs array.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 2.0
     */
    public int getAverageSongLength() {
        if (songs.isEmpty()) {
            return -1;
        } else {
            int totalLength = 0;

            for (Song song : songs) {
                totalLength += song.getLength();
            }
            return totalLength / songs.size();
        }
    }

    //------------------------------------
    // LISTING METHODS - Basic and Advanced
    //------------------------------------

//    //TODO Add a method, listAllSongs().
//    //     The return type is String.
//    //     This method returns a list of the songs stored in the array list.
//    //     Each song should be on a new line and should be preceded by the index number e.g.
//    //        0: song 1 Details
//    //        1: song 2 Details
//    //     If there are no songs stored in the array list, return a string that contains "No songs in playlist.".
//    public String listAllSongs() {
//        if (songs.isEmpty()) {
//            return "No songs in playlist.";
//        }
//        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < songs.size(); i++) {
//            builder.append(i).append(": ").append(songs.get(i).toString()).append("\n");
//        }
//        /*
//         * trim() 方法可以去除字符串中的空格和换行符，使格式更加清晰美观。
//         * The trim() method can remove spaces and line breaks in strings to make the format clearer and more beautiful.
//         */
//        return builder.toString().trim();
//    }

    /*
      题目勘误：新增 String 类型变量 listSongs，用于遍历 songs 数组，使得 PlaylistTest 成功编译。
      Title error: The new String variable listSongs is added to traversal the songs array, so that PlaylistTest can be successfully compiled.
     */
    /**
     * 列出所有歌曲。
     * List all songs.
     *
     * @return 遍历 songs 数组的结果。
     *         The result of traversing the songs array.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 4.3
     */
    public String listSongs() {
        if (songs.isEmpty()) {
            return "No songs in playlist.";
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            builder.append(i).append(": Song{songId=").append(song.getSongId())
                    .append(", name='").append(song.getName()).append('\'')
                    .append(", artistName='").append(song.getArtist().getArtistName()).append('\'')
                    .append(", length=").append(song.getLength()).append("}\n");
        }
        return "Songs from playlist :" + playlistName + "\n" + builder.toString().trim();
    }

    //TODO Add a method, listSongsFromVerifiedArtists().
    //     The return type is String.
    //     This method returns a list of the songs stored in the array list whose song artist is verified.
    //     Each matching song should be on a new line and should be preceded by the index number e.g.
    //        0: song 1 Details
    //        3: song 4 Details
    //      - If there are no such songs stored in the array list, the return string should have "No songs in playlist".
    //      - If there are songs in the playlist but none with a verified artist, the return string should have "There are no songs from verified artists on this playlist".
    /**
     * 列出已验证的歌手的歌曲。
     * List songs from verified artists.
     *
     * @return 已验证歌手的歌曲。
     *         The songs from verified artists.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 4.3
     */
    public String listSongsFromVerifiedArtists() {
        if (songs.isEmpty()) {
            return "No songs in playlist.";
        }

        StringBuilder builder = new StringBuilder();
        boolean isVerifiedSong = false;

        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            if (song != null && song.getArtist() != null && song.getArtist().isVerified()) {
                builder.append(i).append(": ").append(song).append("\n");
                isVerifiedSong = true;
            }
        }

        if (isVerifiedSong) {
            return builder.toString().trim();
        } else {
            return "There are no songs from verified artists on this playlist";
        }
    }


    //TODO Add a method, listSongsLongerThan(int).
    //     The return type is String.
    //     This method returns a list of the songs that are equal or above the length supplied as a parameter.
    //     Each matching song should be on a new line and should be preceded by the index number e.g.
    //        1: song 2 Details
    //        4: song 5 Details
    //      - If there are no songs stored in the array list, return a string that contains "No songs in playlist.".
    //      - If there are songs in the playlist, but none with songs over (or equal to) this length, then "There are no songs on this playlist longer than 'length supplied' " should be returned.
    /**
     * 列出歌曲长度大于指定长度的歌曲。
     * List songs longer than specified length.
     *
     * @param length 指定长度。
     *               Specified length.
     * @return 由歌曲长度大于指定长度的歌曲组成的字符串。
     *         The string composed of songs longer than specified length.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 2.0
     */
    public String listSongsLongerThan(int length) {
        if (songs.isEmpty()) {
            return "No songs in playlist.";
        }

        StringBuilder builder = new StringBuilder();
        boolean isLongerSong = false;

        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);

            if (song.getLength() >= length) {
                builder.append(i).append(": Song{songId=").append(song.getSongId())
                        .append(", name='").append(song.getName()).append('\'')
                        .append(", artistName='").append(song.getArtist().getArtistName()).append('\'')
                        .append(", length=").append(song.getLength()).append("}\n");
                isLongerSong = true;
            }
        }

        if (isLongerSong) {
            return builder.toString().trim();
        } else {
            return "There are no songs on this playlist longer than :" + length + " secs";
        }
    }

    //TODO Add a method, listOfSongsOfArtist(String).
    //     The return type is String.
    //     This method returns a list of all the Songs of an artist (whose name you have supplied as parameter) across all the song objects stored in the array list.
    //     Each song should be on a new line and should contain the song name and code too e.g.
    //        1: song 2 Details
    //        4: song 5 Details
    //      - If there are no songs stored in the array list, return a string that contains "No songs in playlist".
    //      - If there are songs in the playlist, but none by verified artists, then "There are no  songs on this playlist by 'artist supplied' " should be returned.
    /**
     * 列出指定歌手的歌曲。
     * List songs of specified artist.
     *
     * @param artistName 歌手的名字。
     *                   The name of the artist.
     * @return 由指定歌手的歌曲组成的字符串。
     *         The string composed of songs of specified artist.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 2.0
     */
    public String listOfSongsOfArtist(String artistName) {

        if (songs.isEmpty()) {
            return "No songs in playlist.";
        }

        StringBuilder builder = new StringBuilder();
        boolean isArtistSong = false;

        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);

            if (song.getArtist().getArtistName().equals(artistName)) {
                builder.append(i).append(": ").append(song.getName()).append(" (").append(song.getSongId()).append(")\n");
                isArtistSong = true;
            }
        }

        if (isArtistSong) {
            return builder.toString().trim();
        } else {
            return "There are no  songs on this playlist by " + artistName;
        }
    }


    //------------------------------
    //  FINDING METHODS
    //-------------------------------

    /*
      题目勘误：TODO 重复，我们注释了重复内容。
      Title error: TODO is repeated, and we annotated the duplicate content.
     */
//    //TODO Add a method, findSong(int).
//    //     The return type is Song.
//    //     This method returns the song stored at the index that was passed as a parameter.
//    //     However, if the index is not valid, null is returned.
//    public Song findSong(int index) {
//        return null;
//    }

    //TODO Add a method, findSongByCode(int).
    //     The return type is Song.
    //     This method searches the array list for a song with a specific code (passed as a parameter).
    //     When a song is found for this code, it is returned back.
    //     If no song exists for that code, return null.
    // NOTE: The first song encountered is returned, even if more exist with that code.
    //     For extra credit, you could add in validation to ensure that the code is unique when adding a Song.
    /**
     * 根据歌曲编号查找歌曲。
     * Search for a song by song code.
     *
     * @param songCode 要搜索的歌曲ID。
     *                 The song ID to search for.
     * @return 通过歌曲编号查找到的歌曲结果。
     *         The result of searching for a song by song code.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 2.0
     */
    public Song findSongByCode(int songCode) {
        for (Song song : songs) {
            if (song.getSongId() == songCode) {
                return song;
            }
        }
        return null;
    }

    //------------------------------
    //  SEARCHING METHODS
    //-------------------------------

    //TODO Add a method, searchSongsByName(String).  The return type is String.
    //     This method returns a list of the songs whose name contains the string passed as a parameter.
    //     Each matching song should be on a new line and should be preceded by the index number e.g.
    //        1: song 2 Details
    //        4: song 5 Details
    //      - If there are no songs stored in the array list, return a string that contains "No songs".
    //      - If there are no songs whose name contains the supplied string, the return string should have "No songs found".
    /**
     * 根据歌曲名称搜索歌曲。
     * Search songs by song name.
     *
     * @param songName 歌曲名称。
     *                 The song name.
     * @return 通过歌曲名称搜索到的歌曲结果。
     *         The result of searching songs by song name.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 2.0
     */
    public String searchSongsByName(String songName) {
        if (songs.isEmpty()) {
            return "No songs.";
        }

        StringBuilder builder = new StringBuilder();
        boolean isSongName = false;

        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getName().contains(songName)) {
                builder.append(i).append(": ").append(songs.get(i).toString()).append("\n");
                isSongName = true;
            }
        }

        if (isSongName) {
            return builder.toString().trim();
        } else {
            return "No songs found.";
        }
    }

    /*
      提示：该方法未被使用。
      Hint: This method is not used.
     */
    //TODO Add a method, searchSongsByArtistName(String).
    //     The return type is String.
    //     This method returns a list of songs whose artist name contains the string passed as a parameter.
    //     Each song should be on a new line and should contain the song name and code e.g.
    //        Flowers (45343)
    //        Wrecking Ball (65434)
    //      - If there are no songs stored in the array list, return a string that contains "No songs".
    //      - If there are no songs whose name contains the supplied string, the return string should have "No songs found for this artist.
    public String searchSongsByArtistName(String artistName) {
        if (songs.isEmpty()) {
            return "No songs.";
        }

        StringBuilder builder = new StringBuilder();
        boolean isArtistName = false;

        for (Song song : songs) {
            if (song.getArtistName().contains(artistName)) {
                builder.append(song.getName()).append(" (").append(song.getSongId()).append(")\n");
                isArtistName = true;
            }
        }

        if (isArtistName) {
            return builder.toString().trim();
        } else {
            return "No songs found for this artist.";
        }
    }

    //-------------------------
    // HELPER METHODS
    //-------------------------

    /*
      题目勘误：TODO 重复，我们注释了重复内容。
      Title error: TODO is repeated, and we annotated the duplicate content.
     */
//    //TODO Add a method, isValidIndex(int).
//    //     The return type is boolean.
//    //     This method returns true if the value passed as a parameter is a valid index in the arraylist.
//    //     However, if the index is not valid, false is returned.
//    private boolean isValidIndex(int index) {
//        return false;
//    }

    /**
     * 重写 toString() 方法，返回歌单信息。
     * Override the toString() method to return the information of the playlist.
     * @return 歌单信息。
     *         Information of the playlist.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 2.0
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        if (songs.isEmpty()) {
            return "No songs in playlist.";
        } else {
            builder.append("controllers.Playlist Name: ").append(playlistName).append("\n");
            builder.append("controllers.Playlist Description: ").append(description).append("\n");
            builder.append("Likes: ").append(likes).append("\n");
            builder.append("Songs: ").append("\n");

            for (Song song : songs) {
                builder.append(song.toString()).append("\n");
            }
            return builder.toString().trim();
        }
    }
}
/*
 * End of controllers.Playlist Class.
 * Checked by Fan Xinkang on 2025/04/03.
 */