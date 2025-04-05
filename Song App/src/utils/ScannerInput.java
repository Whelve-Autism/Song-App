package utils;

import java.util.Scanner;

import static userInterface.Display.printlnRandomColor;

/**
 * 此类用于从用户输入中读取数据，并进行验证。
 * This class is used to read data from the user and perform validation.
 *
 * @author Siobhan Drohan, Mairead Meagher, Siobhan Roche, Fan Xinkang
 * @version 6.0
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
                System.err.println("Enter a number please.");
            }
        }  while (true);
    }

    /**
     * 读取一个长整数，并验证输入是否为长整数。
     * Read a single long integer from the user and validate that the entered data is actually a long integer.
     *
     * @param prompt 在控制台打印的信息。
     *               The information printed to the console for the user to read.
     * @return 从用户那里读取并验证为 long 类型的数字。
     *         The number read from the user and verified as a long.
     * @exception NumberFormatException 如果用户输入无法解析为长整数，则抛出此异常。
     *                                  If the user input cannot be parsed as a long integer, throw this exception.
     * @author Fan Xinkang
     * @since version 6.0
     */
    public static long readNextLong(String prompt) {
        do {
            var scanner = new Scanner(System.in);
            try {
                printlnRandomColor(prompt);
                return Long.parseLong(scanner.next());
            }
            catch (NumberFormatException e) {
                System.err.println("Enter a number please.");
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
}
/*
 * End of utils.ScannerInput Class.
 * Checked by Fan Xinkang on 2025/04/05.
 */