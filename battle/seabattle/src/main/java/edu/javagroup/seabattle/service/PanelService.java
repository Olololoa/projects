package edu.javagroup.seabattle.service;

/**
 * @author Elena Saponenko, Elena Zayceva, Olga Allenova
 * @version 1.0
 */
public interface PanelService {

    boolean isPanelEmpty();

    boolean isFullMinePanel();

    int howMuchIsLeft(String side);

    boolean checkEndGame(String side);
}
