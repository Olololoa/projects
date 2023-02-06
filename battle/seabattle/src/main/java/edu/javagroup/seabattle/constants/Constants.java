package edu.javagroup.seabattle.constants;

/**
 * @author Khulup Siarhey
 * @version 1.0
 * класс констант
 */
public class Constants {
    public final static Integer[] LOCALHOST = {127, 0, 0, 1};
    public static String ENEMY_PROTOCOL = "http";
    public static String ENEMY_IP_ADDRESS = "enemyIpAddress";
    public static String ENEMY_PORT = "8080";
    public static String VERTICAL_COORDINATE = "ABCDEFGHIJ";
    public static String DECK = "deck";
    public static String BUTTON_PREFIX = "jButton";
    public static String MINE = "M";
    public static String ENEMY = "E";
    public static String M_BUTTON_PREFIX = BUTTON_PREFIX.concat(MINE);
    public static String E_BUTTON_PREFIX = BUTTON_PREFIX.concat(ENEMY);
    public static String BUTTON_POSTFIX = "ActionPerformed";
}
