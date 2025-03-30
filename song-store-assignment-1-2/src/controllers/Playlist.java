package controllers;

import models.Song;
import java.util.ArrayList;

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
    public Playlist(String playlistName, String description) {
        setPlaylistName(playlistName);
        setDescription(description);
    }

    //TODO Add a getter and setter for each field, that adheres to the above validation rules.
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
    public boolean updateSong(int index, Song song) {
        if (isValidIndex(index)) {
            Song songToUpdate = songs.get(index);

            if (song.getSongId() > 9999 || song.getSongId() < 1000) {
                return false;
            }

            if (song.getName().length() > 20) {
                return false;
            }

            if (song.getArtistName().length() > 15) {
                return false;
            }

            if (song.getLength() <= 0) {
                return false;
            }

            if (songToUpdate.getSongId() == song.getSongId() &&
                    songToUpdate.getName().equals(song.getName()) &&
                    songToUpdate.getLength() == song.getLength() &&
                    songToUpdate.getArtistName().equals(song.getArtistName())) {
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
    public boolean isValidIndex(int index) {
        return index >= 0 && index < songs.size();
    }

    //TODO  Add a method  findSong(int) which returns a Song object:
    //       - if the supplied index is valid, the Song object at that location is returned
    //       - if the supplied index is invalid, null is returned
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
        return "Songs from playlist :" + builder.toString().trim();
    }

    //TODO Add a method, listSongsFromVerifiedArtists().
    //     The return type is String.
    //     This method returns a list of the songs stored in the array list whose song artist is verified.
    //     Each matching song should be on a new line and should be preceded by the index number e.g.
    //        0: song 1 Details
    //        3: song 4 Details
    //      - If there are no such songs stored in the array list, the return string should have "No songs in playlist".
    //      - If there are songs in the playlist but none with a verified artist, the return string should have "There are no songs from verified artists on this playlist".
    public String listSongsFromVerifiedArtists() {
        if (songs.isEmpty()) {
            return "No songs in playlist.";
        }

        StringBuilder builder = new StringBuilder();
        boolean isVerifiedSong = false;

        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i) != null && songs.get(i).getArtist() != null && songs.get(i).getArtist().isVerified()) {
                builder.append(i).append(": ").append(songs.get(i).toString()).append("\n");
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
                        .append(", artistName='").append(song.getArtistName()).append('\'')
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
            return "There are no songs on this playlist by " + artistName;
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

    /*
      添加了toString()方法，使得 PlaylistTest 的编译通过。
      The toString() method was added, so that the compilation of PlaylistTest was passed.
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