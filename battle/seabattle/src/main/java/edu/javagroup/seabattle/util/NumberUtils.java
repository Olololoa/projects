package edu.javagroup.seabattle.util;

/**
 * @author Siarhey Khulup
 * @version 1.0
 * утилитный класс
 */

public class NumberUtils {
    /**
     * метод проверяет, содержит ли строка целочисленное, положительное значение (включая нуль)
     *
     * @param line
     * @return boolean
     */
    public static boolean isNumber(String line) {
        if (StringUtils.isEmpty(line)) {
            return false;
        }
        for (int i = 0; i < line.length(); i++) {
            if (!Character.isDigit(line.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param number
     * @return "number" в строковом представлении
     */
    public static String currentNumber(int number) {
        return number < 10 ? "0" + number : String.valueOf(number);
    }

    /**
     * @param number
     * @return предыдущее число в строковом представлении, если оно меньше 10, то с лидирующим нулем
     */
    public static String numberBefore(int number) {
        return number <= 10 ? "0" + (number - 1) : String.valueOf(number - 1);
    }

    /**
     * @param number
     * @return следующее число в строковом представлении, если оно меньше 10, то с лидирующим нулем
     */
    public static String numberAfter(int number) {
        return number < 9 ? "0" + (number + 1) : String.valueOf(number + 1);
    }
}

