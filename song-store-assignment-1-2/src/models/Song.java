package models;

import java.util.Objects;

/**
 * 此类用于创建并存储歌曲对象。
 * This class is used to create and store song objects.
 *
 * @author Fan Xinkang, Xu Shiyi, Lu Siyu
 * @version 4.4
 * @since version 0.0
 */
public class Song {

    //TODO The song id (int songId) is between 1000 to 9999(both inclusive).
    //     Default is 9999.
    private int songId = 9999;

    //TODO The song name (String name).
    //     Default value is "".
    //     When creating the song, truncate the name to 20 characters.
    //     When updating an existing song, only update the name if it is 20 characters or less.
    private String name;

    //TODO The song's artist (Artist artist).
    //     You should have already written the Artist class.
    //     When creating the song, you should have the artist object as a parameter.
    private Artist artist;

    /*
      题目勘误：新增 String 类型变量 artistName，用于存储 artistName 字段，使得 SongTest 成功编译。
      Title error: The new string variable artistName is added to store the artistName field, so that SongTest can be successfully compiled.
     */
    private String artistName;

    /*
      题目勘误：新增 boolean 类型变量 verified，用于存储 verified 字段，使得 SongTest 成功编译。
      Title error: The new boolean variable verified is added to store the verified field, so that SongTest can be successfully compiled.
     */
    private boolean verified;

    /*
      新增判断：验证 name 是否被设置过，使得 SongTest 成功编译。
      New judgment: Verify whether name has been set, so that the SongTest can be successfully compiled.
     */
    private boolean nameSet = false;

    //TODO The length of the song in seconds (int length) is between 1 and 600.
    //     Default is 1.
    private int length = 1;

    /*
      题目勘误：修改为 String 类型变量 artistName，用于存储 artistName 字段，使得 SongTest 成功编译。
      Title error: The new string variable artistName is added to store the artistName field, so that SongTest can be successfully compiled.
     */
    //TODO Add the constructor, Song(int, String, Artist), that adheres to the above validation rules.
    /**
     * 构造函数，将 songId, name, artistName, verified 和 length 作为参数传入 Artist。
     * Constructor, passing songId, name, artistName, verified and length as parameters to Artist.
     *
     * @param songId 歌曲ID。
     *               Song ID.
     * @param name  歌曲名称。
     *              Song name.
     * @param artistName 歌手的名字。
     *                   Artist name.
     * @param verified 验证是否为 verified 字段，使得 SongTest 成功编译。
     *                 Verify whether verified is a field, so that SongTest can be successfully compiled.
     * @param length 歌曲长度。
     *               Song length.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 3.0
     */
    public Song(int songId, String name, String artistName, boolean verified, int length) {
        setSongId(songId);
        setName(name);
        setArtist(new Artist(artistName, verified));
        setLength(length);
    }

    //TODO Add a getter and setter for each field, that adheres to the above validation rules.
    /*
      封装。
      Encapsulation.
     */
    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        if (songId >= 1000 && songId <= 9999) {
            this.songId = songId;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!nameSet) {
            if (name.length() <= 20) {
                this.name = name;
                nameSet = true;
            } else {
                this.name = name.substring(0, 20);
                nameSet = true;
            }
        } else {
            if (name.length() <= 20) {
                this.name = name;
            }
        }
    }

    /*
      将新增的方法封装。
      Title error: The new method is added to get the artistName field.
     */
    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        if (length >= 1 && length <= 600) {
            this.length = length;
        }
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    //TODO Add a generated equals method.
    /**
     * 重写 equals() 方法，比较两个对象是否相等。
     * Override the equals() method to compare whether two objects are equal.
     *
     * @param object 传入的对象。
     *               Passed in object.
     * @return 两个对象是否相等。
     *         Whether the two objects are equal.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 3.0
     */
    @Override
    public boolean equals(Object object) {

        /*
          如果是同一个对象引用，直接返回 true。
          If it is the same object, return true directly.
         */
        if (this == object) {
            return true;
        }

        /*
          如果传入的对象为 null 或者类型不匹配，直接返回 false。
          If the passed in object is null or the type does not match, return false directly.
         */
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        /*
          进行类型转换。
          Convert the passed in object to Song type.
         */
        Song song = (Song) object;

        /*
          判断字段是否相等。
          Determine whether the field is equal.
         */
        return songId == song.songId &&
                length == song.length &&
                Objects.equals(name, song.name) &&
                Objects.equals(artistName, song.artistName) &&
                verified == song.verified;
    }

    //TODO The toString should return the string containing each of the field values including the use of the artist's toString()
    /**
     * 重写 toString() 方法，返回歌单信息。
     * Override the toString() method to return the information of playlist.
     *
     * @return 歌单信息。
     *         Information of playlist.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 3.0
     */
    @Override
    public String toString() {
        return "Song{" +
                "\n    songId=" + songId +
                ",\n    name='" + name + '\'' +
                ",\n    artist=" + artist +
                ",\n    length=" + length +
                "\n}";
    }
}
/*
 * End of models.Song Class.
 * Checked by Fan Xinkang on 2025/04/03.
 */