package UserInterface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/*
 * 此类用于美化显示界面。
 * This class is used to beautify the display interface.
 */
public class Display {

    /*
     * 构造一个存放不同颜色的数组，用于随机选择颜色。
     * Create an array to store different colors, used to select a color randomly.
     */
    private static final String[] COLOURS = {
            "\u001B[91m", // 红色 (Bright Red)
            "\u001B[92m", // 绿色 (Bright Green)
            "\u001B[93m", // 黄色 (Bright Yellow)
            "\u001B[94m", // 蓝色 (Bright Blue)
            "\u001B[95m", // 紫色 (Bright Purple)
            "\u001B[96m", // 青色 (Bright Cyan)
            "\u001B[97m"  // 白色 (Bright White)
    };

    /*
     * 初始化随机数，用于随机选择颜色。
     * Initialize a random number to select a color.
     */
    private static Random random = new Random();


    /*
     * 打印随机颜色（同行）。
     * Print random color (same line).
     */
    public static void printRandomColor(String word) {
        String randomColor = COLOURS[random.nextInt(COLOURS.length)];
        System.out.print(randomColor + word + "\u001B[0m");
    }


    /*
     * 打印随机颜色（换行）。
     * Print random color (new line).
     */
    public static void printlnRandomColor(String wordln) {
        String randomColor = COLOURS[random.nextInt(COLOURS.length)];
        System.out.println(randomColor + wordln + "\u001B[0m");
    }

    /*
     * 打印随机颜色（自定义图案）。
     * Print random color for pattern.
     */
    public static void printRandomColorForPattern(String pattern) {
        StringBuilder result = new StringBuilder();
        for (char signal : pattern.toCharArray()) {
            String randomColor = COLOURS[random.nextInt(COLOURS.length)];
            result.append(randomColor).append(signal).append("\u001B[0m");
        }
        System.out.println(result);
    }

    /*
     * 打印不同长度的斜杠，作为分隔符。
     * Print different length slash as separator.
     */
    public static void shortSlash() {
        for (int i = 0; i < 41; i++){
            printRandomColor("/");
        }
        printlnRandomColor("/");
    }

    public static void middleSlash() {
        for (int i = 0; i < 47; i++){
            printRandomColor("/");
        }
        printlnRandomColor("/");
    }

    public static void longSlash() {
        for (int i = 0; i < 107; i++){
            printRandomColor("/");
        }
        printlnRandomColor("/");
    }

    public static String getLocalDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /*
     * 显示开始界面。
     * UserInterface.Display the start interface.
     */
    public static void beginDisplay() {
        middleSlash();
        printlnRandomColor("Song App V4.1.");
        printlnRandomColor("Developed by Fan Xinkang, Xu Shiyi and Lu Siyu.");
        printlnRandomColor("The program starts at: " + getLocalDateTime() + ".");
        middleSlash();
        System.out.println();
        printRandomColor("Please wait while the system loads");

        /*
         * 模拟延迟3秒。
         * Simulation delay of 3 seconds.
         */
        try {
            for (int i = 0; i < 10; i++) {
                printRandomColor(".");
                TimeUnit.MILLISECONDS.sleep(300);
            }
        } catch (Exception e) {
            System.err.println("Thread was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        System.out.println();
        System.out.println();
        longSlash();
        printRandomColorForPattern("***************        ***************        ***************        ***************        ***************");
        printRandomColorForPattern("***************        ***************        ***************        ***************        ***************");
        printRandomColorForPattern("***                          ***              ***         ***        ***         ***              ***");
        printRandomColorForPattern("***                          ***              ***         ***        ***         ***              ***");
        printRandomColorForPattern("***                          ***              ***         ***        ***         ***              ***");
        printRandomColorForPattern("***************              ***              ***************        ***************              ***");
        printRandomColorForPattern("***************              ***              ***************        ***************              ***");
        printRandomColorForPattern("            ***              ***              ***         ***        *******                      ***");
        printRandomColorForPattern("            ***              ***              ***         ***        ***  ****                    ***");
        printRandomColorForPattern("            ***              ***              ***         ***        ***    ****                  ***");
        printRandomColorForPattern("***************              ***              ***         ***        ***      ****                ***");
        printRandomColorForPattern("***************              ***              ***         ***        ***        ****              ***");
        longSlash();
    }

    /*
     * 显示结束界面。
     * UserInterface.Display the end interface.
     */
    public static void endDisplay() {
        shortSlash();
        printlnRandomColor("Exiting System...bye... ");
        printlnRandomColor("Thank you for using our Song App.");
        printlnRandomColor("The program ends at: " + getLocalDateTime() + ".");
        shortSlash();
    }
}
