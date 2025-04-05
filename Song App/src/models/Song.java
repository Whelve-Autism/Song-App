package models;

import java.time.LocalDate;

/**
 * 此类用于存储歌曲信息。
 * This class is used to store song information.
 *
 * @author Fan Xinkang
 * @version 6.0
 * @since version 6.0
 */
public class Song {

    private String songName;
    private String songArtist;
    private int length;
    LocalDate publishedDate;

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public LocalDate getPublishDate() {
        return publishedDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishedDate = publishDate;
    }

    /**
     * 构造函数，用于创建Song对象。
     * Constructor used to create a Song object.
     *
     * @param songName 歌曲名称。
     *                 Song name.
     * @param songArtist 歌手名称。
     *                   Artist name.
     * @param length 歌曲长度（以秒为单位）。
     *               Song length (in seconds).
     * @param publishDate 歌曲发布日期。
     *                    Song publish date.
     * @author Fan Xinkang
     * @since version 6.0
     */
    public Song(String songName, String songArtist, int length, LocalDate publishDate) {
        this.songName = songName;
        this.songArtist = songArtist;
        this.length = length;
        this.publishedDate = publishDate;
    }

    /**
     * 重写equals方法，用于比较两个Song对象是否相等。
     * Override equals method to compare whether two Song objects are equal.
     *
     * @param object 要比较的对象。
     *               Object to compare.
     * @return 比较的结果。
     *         Result of comparison.
     * @author Fan Xinkang
     * @since version 6.0
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Song song = (Song) object;
        return length == song.length &&
                songName.equals(song.songName) &&
                songArtist.equals(song.songArtist) &&
                publishedDate.equals(song.publishedDate);
    }

    /**
     * 重写toString方法，用于返回Song对象的字符串表示。
     * Override toString method to return the string representation of a Song object.
     *
     * @return Song对象的字符串表示。
     *         String representation of a Song object.
     * @author Fan Xinkang
     * @since version 6.0
     */
    @Override
    public String toString() {
        return "Song Name: " + songName + "\n" +
               "Song Artist: " + songArtist + "\n" +
               "Length: " + length + " seconds" + "\n" +
               "Published Date: " + publishedDate + "\n";
    }
}
/*
 * End of models.Song Class.
 * Checked by Fan Xinkang on 2025/04/05.
 */