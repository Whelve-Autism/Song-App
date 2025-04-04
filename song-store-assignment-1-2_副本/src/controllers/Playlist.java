package controllers;

import models.Song;
import java.util.ArrayList;

/**
 * 此类用于控制播放器，并提供 CRUD 操作。
 * This class is used to control the player, and provides CRUD operations.
 *
 * @author Fan Xinkang, Xu Shiyi, Lu Siyu
 * @version 5.0
 * @since version 5.0
 */
public class Playlist {

    private String playlistName = ""; // valid length is 20 - default to the first 20 characters of input.

    private ArrayList<Song> songs = new ArrayList<Song>();  // should start empty

    private String description = ""; // valid length is 30 - default to the first 30 characters of input.

    private int likes = 0;

    /*
      新增判断：验证 description 是否被设置过，使得 PlaylistTest 成功编译。
      New judgment: Verify whether description has been set, so that PlaylistTest can be successfully compiled.
     */
    private boolean descriptionSet = false;

    /*
      新增判断：验证 playlistName 是否被设置过，使得 PlaylistTest 成功编译。
      New judgment: Verify whether playlistName has been set, so that PlaylistTest can be successfully compiled.
     */
    private boolean playlistNameSet = false;

    /*
      新增判断：验证 addLike 是否被设置过，使得 PlaylistTest 成功编译。
      New judgment: Verify whether addLike has been set, so that PlaylistTest can be successfully compiled.
     */
    private boolean addLikeSet = false;

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

    /*
      提示：该方法验证逻辑已经在 Driver 类中体现，此处验证数据的代码是冗余的。
      Hint: The validation logic in this method is already reflected in the Driver class, the code for validating the data is redundant.
     */
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

    //-------------------------------------
    //  ARRAYLIST -  Verified Status Update
    //-------------------------------------

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
                    .append(", length=").append(song.getLength()).append("seconds}\n");
        }
        return "Songs from playlist :" + "\n" + playlistName + "\n" + builder.toString().trim();
    }

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

    //-------------------------
    // HELPER METHODS
    //-------------------------

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