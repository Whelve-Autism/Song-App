package controllers;

import models.Album;
import models.Song;
import utils.ScannerInput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static userInterface.Display.printlnRandomColor;

/**
 * 此类用于处理歌曲相关的操作，如添加、更新、删除等。
 * This class is used to handle operations related to songs, such as adding, updating and deleting.
 *
 * @author Fan Xinkang
 * @version 6.0
 * @since version 6.0
 */
public class SongManager {
    private ArrayList<Album> albums = new ArrayList<>();

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

    /**
     * 判断是否存在专辑。
     * Determine whether there are albums.
     *
     * @return 判断的结果。
     *         The result of the check.
     * @author Fan Xinkang
     * @since version 6.0
     */
    public boolean hasAlbum() {
        return !albums.isEmpty();
    }

    /**
     * 判断专辑名称是否存在。
     * Determine whether the album name exists.
     *
     * @param name 专辑名称。
     *             Album name.
     * @return 判断的结果。
     *         The result of the check.
     * @author Fan Xinkang
     * @since version 6.0
     */
    private boolean isAlbumNameExists(String name) {
        for (Album album : albums) {
            if (album.getAlbumName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断歌曲名称是否存在。
     * Determine whether the song name exists.
     *
     * @param album 专辑。
     *              Album.
     * @param songName 歌曲名称。
     *                 Song name.
     * @return 判断的结果。
     *         The result of the check.
     * @author Fan Xinkang
     * @since version 6.0
     */
    private boolean isSongNameExists(Album album, String songName) {
        for (Song song : album.getSongs()) {
            if (song.getSongName().equalsIgnoreCase(songName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 选择一个专辑。
     * Choose an album.
     *
     * @return 选择的专辑。
     *         The chosen album.
     * @author Fan Xinkang
     * @since version 6.0
     */
    public Album chooseAlbum() {
        if (albums.size() == 1) {
            return albums.getFirst();
        } else {
            printlnRandomColor("Choose an album:");
            for (int i = 0; i < albums.size(); i++) {
                printlnRandomColor((i + 1) + ") " + albums.get(i).getAlbumName());
            }
            int option = ScannerInput.readNextInt("Enter your option: ");
            return albums.get(option - 1);
        }
    }

    /**
     * 添加一个专辑。
     * Add an album.
     *
     * @return 添加的专辑。
     *         The added album.
     * @author Fan Xinkang
     * @since version 6.0
     */
    public Album addAlbum() {
        printlnRandomColor("Attention: Once created, it cannot be modified or deleted.");

        /*
          专辑名称不能为空，并且不能重复。
          Album name cannot be empty and cannot be repeated.
         */
        String name;
        do {
            name = ScannerInput.readNextLine("Enter the name of the new album: ").trim();
            if (name.isEmpty()) {
                printlnRandomColor("Album name cannot be empty. Please enter a valid album name.");
            } else if (isAlbumNameExists(name)) {
                printlnRandomColor("Album name already exists. Please enter a different name.");
            }
        } while (name.isEmpty() || isAlbumNameExists(name));

        /*
          专辑描述可以为空。
          Album description can be empty.
         */
        String description = ScannerInput.readNextLine("Enter the description of the new album: ");
        Album album = new Album(name, description);
        albums.add(album);
        printlnRandomColor("Album created: " + name);
        return album;
    }

    /**
     * 添加歌曲到专辑中。
     * Add a song to an album.
     *
     * @param album 专辑。
     *              Album.
     * @author Fan Xinkang
     * @since version 6.0
     */
    public void addSong(Album album) {

        /*
          歌曲名称不能为空，并且不能重复。
          Song name cannot be empty and cannot be repeated.
         */
        String songName;
        do {
            songName = ScannerInput.readNextLine("Enter the song name: ");
            if (songName.trim().isEmpty()) {
                printlnRandomColor("Song name cannot be empty. Please enter a valid song name.");
            }
        } while (songName.trim().isEmpty());

        /*
          歌手名称不能为空。
          Artist name cannot be empty.
         */
        String songArtist;
        do {
            songArtist = ScannerInput.readNextLine("Enter the artist name: ");
            if (songArtist.trim().isEmpty()) {
                printlnRandomColor("Artist name cannot be empty. Please enter a valid artist name.");
            }
        } while (songArtist.trim().isEmpty());

        /*
          歌曲长度不能为空，并且必须为正整数。
          Song length cannot be empty and must be a positive integer.
         */
        int length;
        do {
            length = ScannerInput.readNextInt("Enter the song length (seconds): ");
            if (length <= 0) {
                printlnRandomColor("Song length must be a positive number. Please enter a valid song length.");
            }
        } while (length <= 0);

        /*
          歌曲发布日期不能为空，并且必须为有效的日期格式。
          Song published date cannot be empty and must be a valid date format.
         */
        LocalDate publishedDate;
        do {
            String inputDate = ScannerInput.readNextLine("Enter the published date (yyyy-MM-dd): ");
            try {
                publishedDate = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (Exception e) {
                printlnRandomColor("Invalid date format. Please enter a valid date (yyyy-MM-dd)");
                publishedDate = null;
            }
        } while (publishedDate == null);

        Song song = new Song(songName, songArtist, length, publishedDate);
        album.getSongs().add(song);
        printlnRandomColor("Song added to album: " + songName);
    }

    /**
     * 列出一个专辑中的所有歌曲。
     * List all songs in a given album.
     *
     * @param album 专辑。
     *              Album.
     * @return 专辑。
     *         Album.
     * @author Fan Xinkang
     * @since version 6.0
     */
    private int ListAllSongsInAGivenAlbum(Album album) {
        for (int i = 0; i < album.getSongs().size(); i++) {
            printlnRandomColor((i + 1) + ") " + album.getSongs().get(i).getSongName());
        }
        int option;
        boolean validOption = false;
        do {
            option = ScannerInput.readNextInt("Enter your option: ");
            if (option < 1 || option > album.getSongs().size()) {
                printlnRandomColor("Invalid option. Please enter a valid number between 1 and " + album.getSongs().size() + ".");
            } else {
                validOption = true;
            }
        } while (!validOption);
        return option;
    }

    /**
     * 更新专辑中的歌曲。
     * Update a song in an album.
     *
     * @param album 专辑。
     *              Album.
     * @author Fan Xinkang
     * @since version 6.0
     */
    public void updateSong(Album album) {
        if (album.getSongs().isEmpty()) {
            printlnRandomColor("No songs found in the album.");
        } else {
            printlnRandomColor("Choose a song to update:");
            int option = ListAllSongsInAGivenAlbum(album);

            /*
              创建一个新歌曲对象，用于存储用户输入的新歌曲信息。
              Create a new song object to store the user input for the new song.
             */
            Song newSong = album.getSongs().get(option - 1);

            /*
              创建一个原始歌曲对象，用于比较更新后的歌曲是否与原始歌曲相同。
              Create an original song object to compare whether the updated song is the same as the original song.
             */
            Song originalSong = new Song(newSong.getSongName(), newSong.getSongArtist(), newSong.getLength(), newSong.getPublishDate());

            /*
              更新歌曲名称，不能与专辑中的其他歌曲名称重复（可以按回车键跳过）。
              Update song name, new song name cannot be repeated in the album (press Enter key to skip).
             */
            String newSongName;
            boolean validSongName = false;
            do {
                newSongName = ScannerInput.readNextLine("Enter the new song name (press Enter key to skip): ").trim();
                if (!newSongName.isEmpty() && !newSongName.equalsIgnoreCase(newSong.getSongName()) && isSongNameExists(album, newSongName)) {
                    printlnRandomColor("Song name already exists in this album. Please enter the new song name.");
                } else {
                    validSongName = true;
                }
            } while (!validSongName);

            if (newSongName.isEmpty()) {
                newSongName = newSong.getSongName();
            }

            /*
              更新歌手名称（可以按回车键跳过）。
              Update artist name (press Enter key to skip).
             */
            String newSongArtist = ScannerInput.readNextLine("Enter the new artist name (press Enter key to skip): ").trim();
            if (newSongArtist.isEmpty()) {
                newSongArtist = newSong.getSongArtist();
            }

            /*
              更新歌曲长度, 新歌曲长度必须为正整数（可以按回车键跳过）。
              Update song length, new song length must be a positive integer (press Enter key to skip).
             */
            int newLength = newSong.getLength();
            boolean validLength = false;
            do {
                String lengthInput = ScannerInput.readNextLine("Enter the new song length in seconds (press Enter key to skip): ").trim();
                if (lengthInput.isEmpty()) {
                    validLength = true;
                } else {
                    try {
                        newLength = Integer.parseInt(lengthInput);
                        if (newLength > 0) {
                            validLength = true;
                        } else {
                            printlnRandomColor("Song length must be a positive number. Please enter a valid song length.");
                        }
                    } catch (NumberFormatException e) {
                        printlnRandomColor("Invalid input. Song length must be a positive number. Please enter a valid song length.");
                    }
                }
            } while (!validLength);

            /*
              更新发布日期（可以按回车键跳过）。
              Update published date (press Enter key to skip).
             */
            LocalDate newPublishDate = newSong.getPublishDate();
            boolean validDate = false;
            do {
                String inputDate = ScannerInput.readNextLine("Enter the new published date (yyyy-MM-dd) (press Enter key to skip): ");
                if (inputDate.trim().isEmpty()) {
                    validDate = true;
                } else {
                    try {
                        newPublishDate = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        validDate = true;
                    } catch (DateTimeParseException e) {
                        printlnRandomColor("Invalid date format. Please enter a valid date (yyyy-MM-dd).");
                    }
                }
            } while (!validDate);

            newSong.setSongName(newSongName);
            newSong.setSongArtist(newSongArtist);
            newSong.setLength(newLength);
            newSong.setPublishDate(newPublishDate);

            if (originalSong.equals(newSong)) {
                printlnRandomColor("No changes made to the song.");
            } else {
                printlnRandomColor("Song updated: " + newSongName);
            }
        }
    }

    /**
     * 删除专辑中的歌曲。
     * Delete a song from the album.
     * @param album 专辑。
     *              Album.
     * @author Fan Xinkang
     * @since version 6.0
     */
    public void deleteSong(Album album) {
        if (album.getSongs().isEmpty()) {
            printlnRandomColor("No songs found in the album.");
        } else {
            printlnRandomColor("Choose a song to delete:");
            int option = ListAllSongsInAGivenAlbum(album);
            Song removedSong = album.getSongs().remove(option - 1);
            printlnRandomColor("Song deleted from album: " + removedSong.getSongName());
        }
    }
}
/*
 * End of controllers.SongManager Class.
 * Checked by Fan Xinkang on 2025/04/05.
 */