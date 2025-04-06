package userInterface;

import static userInterface.Display.*;
import static utils.ScannerInput.readNextInt;
import static utils.ScannerInput.readNextLine;

import musicPlayer.player.MyPlayer;
import controllers.EmailManager;
import controllers.SongSearchManager;
import controllers.SongManager;
import models.Album;
import models.Song;

import java.io.File;
import java.util.ArrayList;

/**
 * 此类用于显示菜单，并执行相应操作。
 * This class is used to display the menu and execute the corresponding operation.
 *
 * @author Fan Xinkang
 * version 6.0
 * @since version 6.0
 */
public class Menu {
    private static SongManager songManager = new SongManager();
    private static SongSearchManager songSearchManager = new SongSearchManager();

    /**
     * 显示菜单。
     * Display menu.
     *
     * @return 根据菜单内容返回的选项。
     * Return the selected option according to the menu.
     * @author Fan Xinkang
     * @since version 6.0
     */
    private static int displayMenu() {
        printlnRandomColor("""
                  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                  *                          SONGS APP                          *
                  * SONG MENU                                                   *
                  *    1) Add a Song                                            *
                  *    2) Update a Song                                         *
                  *    3) Delete a Song                                         *
                  *    4) List all Songs                                        *
                  * SEARCH MENU                                                 *
                  *    5) Search for a song                                     *
                  *    6) Search for a singer                                   *
                  *    7) Get the music rankings                                *
                  *    8) Request a song to be played                           *
                  * SETTING MENU                                                *
                  *    9) Send the album information to the mailbox             *
                  *    0) Exit                                                  *
                  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                ==>>""");
        return readNextInt("Please enter an option: ");
    }

    /**
     * 运行菜单，根据选项执行操作。
     * Run menu, process the selected options.
     *
     * @author Fan Xinkang
     * @since version 6.0
     */
    public static void runMenu() {
        beginDisplay();

        int option = displayMenu();

        while (option != 0) {

            try {
                switch (option) {
                    case 1:
                        addSong();
                        break;
                    case 2:
                        updateSong();
                        break;
                    case 3:
                        deleteSong();
                        break;
                    case 4:
                        listAllSongs();
                        break;
                    case 5:
                        searchForASong();
                        break;
                    case 6:
//                        searchForAArtist();
                        break;
                    case 7:
//                        getTheMusicRankings();
                        break;
                    case 8:
                        playASong();
                        break;
                    case 9:
                        SendAnEmail();
                        break;
                    default:
                        printlnRandomColor("Please enter a valid number.");
                }
            } catch (Exception e) {
                printlnRandomColor("Error: " + e.getMessage());
            }

            printlnRandomColor("\nPress Enter key to continue... ");
            readNextLine("");
            option = displayMenu();
        }
        endDisplay();
    }

    /**
     * 添加歌曲。
     * Add a song.
     *
     * @author Fan Xinkang
     * @since version 6.0
     */
    private static void addSong() {
        if (!songManager.hasAlbum()) {
            printlnRandomColor("No album found. Creating a new album.");
            Album album = songManager.addAlbum();
            songManager.addSong(album);
        } else {
            String response;
            do {
                printlnRandomColor("Do you want to create a new album? (y/n)");
                response = readNextLine("").trim().toLowerCase();
                if (!response.equals("y") && !response.equals("n")) {
                    printlnRandomColor("Invalid input. Please enter 'y' or 'n'.");
                }
            } while (!response.equals("y") && !response.equals("n"));

            Album album;
            if (response.equals("y")) {
                album = songManager.addAlbum();
            } else {
                album = songManager.chooseAlbum();
            }
            songManager.addSong(album);
        }
    }

    /**
     * 更新歌曲。
     * Update a song.
     *
     * @author Fan Xinkang
     * @since version 6.0
     */
    private static void updateSong() {
        if (!songManager.hasAlbum()) {
            printlnRandomColor("No albums found.");
        } else {
            Album album = songManager.chooseAlbum();
            songManager.updateSong(album);
        }
    }

    /**
     * 删除歌曲。
     * Delete a song.
     *
     * @author Fan Xinkang
     * @since version 6.0
     */
    private static void deleteSong() {
        if (!songManager.hasAlbum()) {
            printlnRandomColor("No albums found.");
        } else {
            Album album = songManager.chooseAlbum();
            songManager.deleteSong(album);
        }
    }

    /**
     * 列出所有歌曲。
     * List all songs.
     *
     * @author Fan Xinkang
     * @since version 6.0
     */
    private static void listAllSongs() {
        ArrayList<Album> allAlbums = songManager.getAlbums();
        if (allAlbums.isEmpty()) {
            printlnRandomColor("No albums found.");
        } else {
            int albumNumber = 1;
            for (Album album : allAlbums) {
                printlnRandomColor(albumNumber + ". Album: " + album.getAlbumName());
                if (album.getSongs().isEmpty()) {
                    printlnRandomColor("  No songs found in this album.");
                } else {
                    int songNumber = 1;
                    for (Song song : album.getSongs()) {
                        printlnRandomColor("  " + songNumber + ". " + song.toString());
                        songNumber++;
                    }
                }
                albumNumber++;
            }
        }
    }

    /**
     * 搜索歌曲。
     * Search for a song.
     *
     * @author Fan Xinkang
     * @since version 6.0
     */
    private static void searchForASong() {
        String songName = readNextLine("Please enter a song name：");
        songSearchManager.searchForSong(songName);
    }

    /**
     * 发送邮件。
     * Send an email.
     *
     * @author Fan Xinkang
     * @since version 6.0
     */
    private static void SendAnEmail() {
        if (!songManager.hasAlbum()) {
            printlnRandomColor("No albums found.");
        } else {
            ArrayList<String> recipients = new ArrayList<>();
            String input;
            do {
                input = readNextLine("Enter a receiver's QQ number (type 'done' to finish): ");
                if (!input.equalsIgnoreCase("done")) {
                    recipients.add(input + "@qq.com");
                }
            } while (!input.equalsIgnoreCase("done"));

            if (recipients.isEmpty()) {
                printlnRandomColor("No email addresses entered.");
            } else {
                EmailManager emailManager = new EmailManager();
                emailManager.sendEmail(songManager.getAlbums(), recipients);
            }
        }
    }

    private static void playASong() {
        File resourcesDir = new File("src/MusicPlayer/resources");
        File[] files = resourcesDir.listFiles((dir, name) -> name.endsWith(".txt"));

        if (files == null || files.length == 0) {
            printlnRandomColor("No songs found.");
            return;
        }

        printlnRandomColor("Available songs:");
        for (int i = 0; i < files.length; i++) {
            printlnRandomColor((i + 1) + ") " + files[i].getName());
        }

        int choice = readNextInt("Please select a song to play: ");
        if (choice < 1 || choice > files.length) {
            printlnRandomColor("Invalid choice.");
            return;
        }

        String selectedFile = files[choice - 1].getName();
        try {
            MyPlayer player = new MyPlayer();
            player.setFile("musicPlayer/resources/" + selectedFile);
            player.play();
        } catch (Exception e) {
            printlnRandomColor("Error playing song: " + e.getMessage());
        }
    }


}
/*
 * End of userInterface.Menu Class.
 * Checked by Fan Xinkang on 2025/04/05.
 */
