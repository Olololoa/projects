package edu.javagroup.seabattle.service;

/**
 * @author Denis Tarasevich, Khulup Siarhey
 * @version 1.0
 */
public interface PointService {
    void setShipPoint(char row, int col);

    boolean setSidePoint(String side, char row, int col, int value);

    boolean isClearPoint(char row, int col);

    boolean getBomb(char row, int col);
}
