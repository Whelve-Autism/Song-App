package UserInterface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 此类用于美化显示界面。
 * This class is used to beautify the display interface.
 *
 * @author Fan Xinkang
 * @version 5.0
 * @since version 5.0
 */
public class Display {

    /*
      构造一个存放不同颜色的数组，用于随机选择颜色。
      Create an array to store different colors, used to select a color randomly.
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
      初始化随机数，用于随机选择颜色。
      Initialize a random number to select a color.
     */
    private static final Random random = new Random();


    /**
     * 打印随机颜色（同行）。
     * Print random color (same line).
     *
     * @param word 待打印的字符串。
     *             The string to be printed.
     * @author Fan Xinkang
     * @since version 5.0
     */
    public static void printRandomColor(String word) {
        String randomColor = COLOURS[random.nextInt(COLOURS.length)];
        System.out.print(randomColor + word + "\u001B[0m");
    }


    /**
     * 打印随机颜色（换行）。
     * Print random color (new line).
     *
     * @param wordln 待打印的字符串。
     *               The string to be printed.
     * @author Fan Xinkang
     * @since version 5.0
     */
    public static void printlnRandomColor(String wordln) {
        String randomColor = COLOURS[random.nextInt(COLOURS.length)];
        System.out.println(randomColor + wordln + "\u001B[0m");
    }

    /**
     * 打印随机颜色（自定义图案）。
     * Print random color for pattern.
     *
     * @param pattern 待打印的图案。
     *                The pattern to be printed.
     * @author Fan Xinkang
     * @since version 5.0
     */
    public static void printRandomColorForPattern(String pattern) {
        StringBuilder result = new StringBuilder();
        for (char signal : pattern.toCharArray()) {
            String randomColor = COLOURS[random.nextInt(COLOURS.length)];
            result.append(randomColor).append(signal).append("\u001B[0m");
        }
        System.out.println(result);
    }

    /**
     * 打印短斜杠，作为分隔符。
     * Print short length slash as separator.
     *
     * @author Fan Xinkang
     * @since version 5.0
     */
    public static void shortSlash() {
        for (int i = 0; i < 41; i++){
            printRandomColor("/");
        }
        printlnRandomColor("/");
    }

    /**
     * 打印中等长度的斜杠，作为分隔符。
     * Print middle length slash as separator.
     *
     * @author Fan Xinkang
     * @since version 5.0
     */
    public static void middleSlash() {
        for (int i = 0; i < 47; i++){
            printRandomColor("/");
        }
        printlnRandomColor("/");
    }

    /**
     * 打印长斜杠，作为分隔符。
     * Print long length slash as separator.
     *
     * @author Fan Xinkang
     * @since version 5.0
     */
    public static void longSlash() {
        for (int i = 0; i < 107; i++){
            printRandomColor("/");
        }
        printlnRandomColor("/");
    }

    /**
     * 获取当前时间。
     * Get current time.
     *
     * @return 当前时间。
     *         The current time.
     * @author Fan Xinkang
     * @since version 5.0
     */
    public static String getLocalDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 显示开始界面。
     * Display the start interface.
     *
     * @author Fan Xinkang
     * @since version 5.0
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
          模拟延迟3秒。
          Simulation delay of 3 seconds.
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

    /**
     * 显示结束界面。
     * Display the end interface.
     *
     * @author Fan Xinkang
     * @since version 5.0
     */
    public static void endDisplay() {
        shortSlash();
        printlnRandomColor("Exiting System...bye... ");
        printlnRandomColor("Thank you for using our Song App.");
        printlnRandomColor("The program ends at: " + getLocalDateTime() + ".");
        shortSlash();
    }
}
/*
 * End of UserInterface.Display Class.
 * Checked by Fan Xinkang on 2025/04/03.
 */