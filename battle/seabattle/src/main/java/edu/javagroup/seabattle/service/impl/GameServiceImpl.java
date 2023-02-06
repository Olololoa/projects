package edu.javagroup.seabattle.service.impl;
/**
 * @author Elena Saponenko
 * @version 1.0
 * @see SettingsSingleton
 * ImReadySingleton
 * ForbiddenCellsSingleton
 * PanelService
 * PointService
 * ShipService
 */

import edu.javagroup.seabattle.service.GameService;
import edu.javagroup.seabattle.service.PanelService;
import edu.javagroup.seabattle.service.PointService;
import edu.javagroup.seabattle.service.ShipService;
import edu.javagroup.seabattle.singleton.EnemyReadySingleton;
import edu.javagroup.seabattle.singleton.ForbiddenCellsSingleton;
import edu.javagroup.seabattle.singleton.ImReadySingleton;
import edu.javagroup.seabattle.singleton.SettingsSingleton;
import edu.javagroup.seabattle.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.HashMap;

import static edu.javagroup.seabattle.constants.Constants.ENEMY_IP_ADDRESS;

@Component
@AllArgsConstructor
public class GameServiceImpl implements GameService {
    private final PanelService panelService;
    private final PointService pointService;
    private final ShipService shipService;

    /**
     * метод выясняет готовность игрока к бою
     *
     * @return boolean
     */
    @Override
    public boolean imReady() {
        if (isFullMinePanel()) {
            if (!checkShipCount()) {
                JOptionPane.showMessageDialog(null, "Корабли расставлены неправильно", "Внимание!", JOptionPane.WARNING_MESSAGE);
            } else {
                String ipAddress = SettingsSingleton.instance(null).getSettingsByKey(ENEMY_IP_ADDRESS);
                if (StringUtils.isNotEmpty(ipAddress)) {
                    ImReadySingleton.instance(true);
                    ForbiddenCellsSingleton.instance(new HashMap<>());
                } else {
                    JOptionPane.showMessageDialog(null, "Не указан ip-address врага", "Внимание!", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Еще не все корабли расставлены", "Внимание!", JOptionPane.WARNING_MESSAGE);
        }
        return ImReadySingleton.instance(null).getImReady();
    }

    /**
     * метод выясняет готовность противника к бою
     *
     * @return boolean
     */
    @Override
    public boolean enemyReady() {
        EnemyReadySingleton.instance(true);
        boolean imReady = JOptionPane.showConfirmDialog(null, "Клятый враг спрашивает, готов ли ты быть поверженным?", "Окно подтверждения", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0;
        return imReady() && imReady;
    }

    /**
     * метод добавляет корабль в указанную точку, если она не занята
     *
     * @param row, col
     * @return void
     */
    @Override
    public void setShipPoint(char row, int col) {
        pointService.setShipPoint(row, col);
    }

    @Override
    public boolean isFullMinePanel() {
        return panelService.isFullMinePanel();
    }

    /**
     * если, в ячейке нет корабля или он был подбит ранее, с помощью метода передаем промах
     * если ячейка занята, то корабль подбит
     *
     * @param row, col
     * @return void
     */
    @Override
    public boolean getBomb(char row, int col) {
        return pointService.getBomb(row, col);
    }

    /**
     * метод добавляет корабль нужной размерности на ту или иную сторону
     *
     * @param side, row, col, value
     * @return void
     */
    @Override
    public void setSidePoint(String side, char row, int col, int value) {
        pointService.setSidePoint(side, row, col, value);
    }

    @Override
    public boolean checkShipCount() {
        return shipService.checkShipCount();
    }

    @Override
    public int checkShipCount(int deckCount) {
        return shipService.checkShipCount(deckCount);
    }

    @Override
    public int howMuchIsLeft(String side) {
        return panelService.howMuchIsLeft(side);
    }

    @Override
    public boolean checkEndGame(String side) {
        return panelService.checkEndGame(side);
    }
}


