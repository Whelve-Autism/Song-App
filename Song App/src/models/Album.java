package models;

import java.util.ArrayList;

/**
 * 此类用于创建和储存专辑信息。
 * This class is used to create and store albums information.
 *
 * @author Fan Xinkang
 * @version 6.0
 * @since version 6.0
 */
public class Album {

    private String albumName;
    private String albumDescription;
    private ArrayList<Song> songs;

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumDescription() {
        return albumDescription;
    }

    public void setAlbumDescription(String albumDescription) {
        this.albumDescription = albumDescription;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    /**
     * 构造函数，用于创建专辑。
     * Constructor used to create an album.
     *
     * @param albumName 专辑名称。
     *                  Album name.
     * @param playlistDescription 专辑描述。
     *                            Album description.
     * @author Fan Xinkang
     * @since version 6.0
     */
    public Album(String albumName, String playlistDescription) {
        this.albumName = albumName;
        this.albumDescription = playlistDescription;
        this.songs = new ArrayList<>();
    }
}
/*
 * End of models.Album Class.
 * Checked by Fan Xinkang on 2025/04/05.
 */