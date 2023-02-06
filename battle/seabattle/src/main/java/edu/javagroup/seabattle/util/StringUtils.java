package edu.javagroup.seabattle.util;

/**
 * @author Siarhey Khulup
 * @version 1.0
 * утилитный класс
 */
public class StringUtils {
    public StringUtils(char row) {

    }

    /**
     * метод выясняет, является ли пришедшая строка null или ее длина равна нулю
     *
     * @param line
     * @return boolean
     */

    public static boolean isEmpty(CharSequence line) {
        return line == null || line.length() == 0;
    }

    /**
     * @param line
     * @return инвертированный ответ метода isEmpty
     */
    public static boolean isNotEmpty(CharSequence line) {
        return !isEmpty(line);
    }

    /**
     * @param row
     * @return предыдущий символ от пришедшего
     */
    public static char letterBefore(char row) {
        return (char) (row - 1);
    }

    /**
     * @param row
     * @return следующий символ от пришедшего
     */
    public static char letterAfter(char row) {
        return (char) (row + 1);
    }
}
