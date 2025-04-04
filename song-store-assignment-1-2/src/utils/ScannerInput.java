package utils;

import java.util.Scanner;

/**
 * 此类用于从用户输入中读取数据，并进行验证。
 * This class is used to read data from the user and perform validation.
 *
 * @author Siobhan Drohan, Mairead Meagher, Siobhan Roche, Fan Xinkang
 * @version 4.5.3
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
     * @author Siobhan Drohan, Mairead Meagher, Siobhan Roche
     * @since version 1.0
     */
    public static int readNextInt(String prompt) {
        do {
            var scanner = new Scanner(System.in);
            System.out.print(prompt);
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                System.out.println("Skipping the operation.");
                return -1;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.err.println("\tEnter a number please.");
            }
        } while (true);
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
     * @author Siobhan Drohan, Mairead Meagher, Siobhan Roche
     * @since version 1.0
     */
    public static double readNextDouble(String prompt) {
        do {
            var scanner = new Scanner(System.in);
            try{
                System.out.print(prompt);
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
     * @param prompt  在控制台打印的信息。
     *                The information printed to the console for the user to read.
     * @return 从用户那里读取的字符串。
     *         The string read from the user.
     * @author Siobhan Drohan, Mairead Meagher, Siobhan Roche
     * @since version 1.0
     */
    public static String readNextLine(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        return input.nextLine();
    }

    /**
     * 读取字符串中的第一个字符。
     * Read the first character from a string.
     *
     * @param prompt  在控制台打印的信息。
     *                The information printed to the console for the user to read.
     * @return 从用户那里读取的字符。
     *         The char read from the user.
     * @author Siobhan Drohan, Mairead Meagher, Siobhan Roche
     * @since version 1.0
     */
    public static char readNextChar(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        return input.next().charAt(0);
    }
}
/*
 * End of utils.ScannerInput Class.
 * Checked by Fan Xinkang on 2025/04/04.
 */