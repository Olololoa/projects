package edu.javagroup.seabattle.util;
/**
 * @author Denis Tarasevich
 * @version 1.0
 * утилитный класс
 */

import java.util.Arrays;

import static edu.javagroup.seabattle.constants.Constants.LOCALHOST;

public class IpAddressUtils {
    /**
     * метод выясняет валидность ip-адреса содержащегося во входящей строке
     *
     * @param lineIpAddress
     * @return boolean
     */
    public static boolean isIpAddress(String lineIpAddress) {
        if (StringUtils.isNotEmpty(lineIpAddress)) {
            return ipCheck(lineIpAddress);
        }
        return false;
    }

    public static boolean ipCheck(String ip) {
        int count = 0;
        for (int i = 0; i < ip.length(); i++) {
            if (ip.charAt(i) == '.' || Character.isDigit(ip.charAt(i))) {
                count++;
            }
        }
        if (count == ip.length()) {
            String[] result = ip.split("\\.");
            for (String s : result) {
                if (StringUtils.isEmpty(s) || ip.endsWith(".") || s.length() > 3) {
                    return false;
                }
            }
            Integer[] array = Arrays.stream(result).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
            if (Arrays.equals(array, LOCALHOST) || array.length != 4) {
                return false;
            }
            for (int i = 0; i < array.length - 1; i++) {
                if ((array[0] > 0 && array[0] <= 255) && (array[1] >= 0 && array[1] <= 255) && (array[2] >= 0 && array[2] <= 255) & (array[3] >= 0 && array[3] <= 255)) {
                    return true;
                }
            }
        }
        return false;
    }
}