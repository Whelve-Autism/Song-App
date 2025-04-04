package utils;

import java.util.Scanner;

import static UserInterface.Display.printlnRandomColor;

/**
 * 此类用于从用户输入中读取数据，并进行验证。
 * This class is used to read data from the user and perform validation.
 *
 * @author Siobhan Drohan, Mairead Meagher, Siobhan Roche, Fan Xinkang
 * @version 5.0
 * @since version 1.0
 */
public class ScannerInput {

    /**
     * 读取一个整数，并验证输入是否为整数。
     * Read a single integer from the user and validate that the entered data is actually an integer.
     *
     * @param prompt 在控制台打印的信息。
     *               The information printed to the console for the user to read.
     * @return 从用户那里读取并验证为 int 类型的数字。
     *         The number read from the user and verified as an int.
     * @exception NumberFormatException 如果用户输入无法解析为整数，则抛出此异常。
     *                                  If the user input cannot be parsed as an integer, throw this exception.
     * @author Siobhan Drohan, Mairead Meagher, Siobhan Roche, Fan Xinkang
     * @since version 5.0
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
     * 读取一个浮点数，并验证输入是否为浮点数。
     * Read a single floating-point number from the user and validate that the entered data is actually a floating-point number.
     *
     * @param prompt 在控制台打印的信息。
     *               The information printed to the console for the user to read.
     * @return 从用户那里读取并验证为 double 类型的数字。
     *         The number read from the user and verified as an int.
     * @exception NumberFormatException 如果用户输入无法解析为浮点数，则抛出此异常。
     *                                  If the user input cannot be parsed as a floating-point number, throw this exception.
     * @author Siobhan Drohan, Mairead Meagher, Siobhan Roche, Fan Xinkang
     * @since version 5.0
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
     * 读取一个字符串。
     * Read a single string from the user.
     *
     * @param prompt 在控制台打印的信息。
     *               The information printed to the console for the user to read.
     * @return 从用户那里读取的字符串。
     *         The string read from the user.
     * @author Siobhan Drohan, Mairead Meagher, Siobhan Roche, Fan Xinkang
     * @since version 5.0
     */
    public static String readNextLine(String prompt) {
        Scanner input = new Scanner(System.in);
        printlnRandomColor(prompt);
        return input.nextLine();
    }

    /**
     * 读取一个字符。
     * Read a single char from the user.
     *
     * @param prompt 在控制台打印的信息。
     *               The information printed to the console for the user to read.
     * @return 从用户那里读取的字符。
     *         The char read from the user.
     * @author Siobhan Drohan, Mairead Meagher, Siobhan Roche, Fan Xinkang
     * @since version 5.0
     */
    public static char readNextChar(String prompt) {
        Scanner input = new Scanner(System.in);
        while (true) {
            printlnRandomColor(prompt);
            String inputString = input.nextLine().trim();
            if (inputString.length() == 1) {
                return inputString.charAt(0);
            } else if (inputString.isEmpty()) {
                return ' ';
            } else {
                System.err.println("\tPlease enter exactly one character.");
            }
        }
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
}
/*
 * End of utils.ScannerInput Class.
 * Checked by Fan Xinkang on 2025/04/04.
 */