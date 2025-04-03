package utils;

import java.util.Scanner;

import static UserInterface.Display.printlnRandomColor;

/**
 * This class provides methods for the robust handling of I/O using Scanner.
 * It creates a new Scanner object for each read from the user, thereby
 * eliminating the Scanner bug (where the buffers don't flush correctly after an int read).
 *
 * The methods also parse the numeric data entered to ensure it is correct. If it isn't correct,
 * the user is prompted to enter it again.
 *
 * @author Siobhan Drohan, Mairead Meagher, Siobhan Roche
 * @version 1.0
 *
 */

public class ScannerInput {

    /**
     * Read an int from the user.  If the entered data isn't actually an int,
     * the user is prompted again to enter the int.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The number read from the user and verified as an int.
     */
    public static int readNextInt(String prompt) {
        do {
            var scanner = new Scanner(System.in);
            try {
                printlnRandomColor(prompt);
                return Integer.parseInt(scanner.next());
            }
            catch (NumberFormatException e) {
                System.err.println("\tEnter a number please.");
            }
        }  while (true);
    }

    /**
     * Read a double from the user.  If the entered data isn't actually a double,
     * the user is prompted again to enter the double.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The number read from the user and verified as a double.
     */
    public static double readNextDouble(String prompt) {
        do {
            var scanner = new Scanner(System.in);
            try{
                printlnRandomColor(prompt);
                return Double.parseDouble(scanner.next());
            }
            catch (NumberFormatException e) {
                System.err.println("\tEnter a number please.");
            }
        }  while (true);
    }

    /**
     * Read a line of text from the user.  There is no validation done on the entered data.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The String read from the user.
     */
    public static String readNextLine(String prompt) {
        Scanner input = new Scanner(System.in);
        printlnRandomColor(prompt);
        return input.nextLine();
    }

    /**
     * Read a single character of text from the user.  There is no validation done on the entered data.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The char read from the user.
     */
    public static char readNextChar(String prompt) {
        Scanner input = new Scanner(System.in);
        printlnRandomColor(prompt);
        return input.next().charAt(0);
    }

    /**
     * 如果用户输入为空，则返回-1，否则尝试解析用户输入为整数，如果解析失败，则提示用户重新输入。
     * If the user input is empty, return -1, otherwise try to parse the user input as an integer, if the parsing fails, prompt the user to reenter.
     *
     * @param prompt 打印到控制台供用户阅读的信息。
     *               The information printed to the console for the user to read.
     * @return 从用户那里读取的整数，并验证它是一个整数。
     *         The number read from the user and verified as an int.
     * @exception NumberFormatException 如果用户输入无法解析为整数，则抛出此异常。
     *                                  If the user input cannot be parsed as an integer, throw this exception.
     * @author Fan Xinkang
     * @since version 5.0
     */
    public static int readNextIntWithSkip(String prompt) {
        do {
            var scanner = new Scanner(System.in);
            try {
                printlnRandomColor(prompt);
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    return -1;
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.err.println("\tEnter a number please.");
            }
        } while (true);
    }

    /**
     * 读取一个字符，并验证输入是否为y或n，也可以按回车跳过。
     * Read a single character of text from the user and validate that the entered data is either 'y' or 'n', or press Enter to skip.
     *
     * @param prompt 打印到控制台供用户阅读的信息。
     *               The information printed to the console for the user to read.
     * @return 从用户那里读取的字符。
     *         The character read from the user.
     * @exception IllegalArgumentException 如果用户输入不是'y'或'n'，则抛出此异常。
     *                                     If the user input is not 'y' or 'n', throw this exception.
     * @author Fan Xinkang
     * @since version 5.0
     */
    public static char readNextCharForUpdate(String prompt) {
        while (true) {
            Scanner input = new Scanner(System.in);
            printlnRandomColor(prompt);
            String inputString = input.nextLine().trim().toLowerCase();
            if (inputString.isEmpty()) {
                return '\0';
            } else if (inputString.equals("y") || inputString.equals("n")) {
                return inputString.charAt(0);
            } else {
                printlnRandomColor("Invalid input. Please enter 'y' or 'n'.");
            }
        }
    }
}
/*
 * End of utils.ScannerInput Class.
 * Checked by Fan Xinkang on 2025/04/03.
 */