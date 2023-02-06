package edu.javagroup.seabattle.service.impl;

/**
 * @author Denis Tarasevich, Khulup Siarhey
 * @version 1.0
 * @seeStringUtils NumberUtils
 * MyStepSingleton
 * MinePanelSingleton
 * EnemyPanelSingleton
 * ForbiddenCellsSingleton
 * PanelService
 */

import edu.javagroup.seabattle.constants.Constants;
import edu.javagroup.seabattle.exception.SideNotFoundException;
import edu.javagroup.seabattle.model.HorizontalLine;
import edu.javagroup.seabattle.service.PanelService;
import edu.javagroup.seabattle.service.PointService;
import edu.javagroup.seabattle.singleton.EnemyPanelSingleton;
import edu.javagroup.seabattle.singleton.ForbiddenCellsSingleton;
import edu.javagroup.seabattle.singleton.MinePanelSingleton;
import edu.javagroup.seabattle.singleton.MyStepSingleton;
import edu.javagroup.seabattle.util.NumberUtils;
import edu.javagroup.seabattle.util.StringUtils;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;
import java.util.Map;

@Component
public class PointServiceImpl implements PointService {
    private final PanelService panelService;

    public PointServiceImpl(PanelService panelService) {
        this.panelService = panelService;
    }

    /**
     * метод добавляет корабль в указанную точку, если она не занята
     *
     * @param row, col
     * @return void
     */
    @Override
    public void setShipPoint(char row, int col) {
        if (isClearPoint(row, col)) {
            addShipPoint(row, col);
        } else {
            clearShipPoint(row, col);
        }
    }

    /**
     * метод добавляет корабль нужной размерности на ту или иную сторону
     *
     * @param side, row, col, value
     * @return void
     */
    @Override
    public boolean setSidePoint(String side, char row, int col, int value) {
        if (side.equals(Constants.MINE)) {
            List<HorizontalLine> minePanelSingletons = MinePanelSingleton.instance(null).getPanel();
            for (int i = 0; i < minePanelSingletons.size(); i++) {
                if (minePanelSingletons.get(i).getRow() == row) {
                    for (int j = 0; j < minePanelSingletons.size(); j++) {
                        if (minePanelSingletons.get(i).getPointElementList().get(j).getCol() == col) {
                            minePanelSingletons.get(i).getPointElementList().get(j).setValue(value);
                            MinePanelSingleton.instance(minePanelSingletons);
                            return true;
                        }
                    }
                }
            }
        } else if (side.equals(Constants.ENEMY)) {
            List<HorizontalLine> enemyPanelSingletons = EnemyPanelSingleton.instance(null).getPanel();
            for (int i = 0; i < enemyPanelSingletons.size(); i++) {
                if (enemyPanelSingletons.get(i).getRow() == row) {
                    for (int j = 0; j < enemyPanelSingletons.size(); j++) {
                        if (enemyPanelSingletons.get(i).getPointElementList().get(j).getCol() == col) {
                            enemyPanelSingletons.get(i).getPointElementList().get(j).setValue(value);
                            EnemyPanelSingleton.instance(enemyPanelSingletons);
                            return true;
                        }
                    }
                }
            }
        } else {
            throw new SideNotFoundException();
        }
        return false;
    }

    @Override
    public boolean isClearPoint(char row, int col) {
        return isOccupiedCell(row, col, 0);
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
        if (isOccupiedCell(row, col, 0) || isOccupiedCell(row, col, 2)) {
            setSidePoint(Constants.MINE, row, col, 3);
            MyStepSingleton.instance(true);
        }
        if (isOccupiedCell(row, col, 1)) {
            setSidePoint(Constants.MINE, row, col, 2);
            MyStepSingleton.instance(false);
            return true;
        }
        return false;
    }

    /**
     * метод проверяет, правильно ли устанавливается корабль
     *
     * @param row, col
     * @return void
     */
    public void addShipPoint(char row, int col) {
        Map<String, Boolean> forbiddenCellsSingletonMap = ForbiddenCellsSingleton.instance(null).getForbiddenCellsMap();
        String key = row + NumberUtils.currentNumber(col);
        if (forbiddenCellsSingletonMap != null || !forbiddenCellsSingletonMap.get(key)) {
            if (!panelService.isFullMinePanel()) {
                if (setSidePoint(Constants.MINE, row, col, 1)) {
                    setForbiddenCells();
                } else {
                    JOptionPane.showMessageDialog(null, "Нельзя использовать эту ячейку", "Внимание!", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Уже занято допустимое количество ячеек", "Внимание!", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Не удалось использовать эту ячейку", "Внимание!", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * метод для удаления корабля из указанной точки
     *
     * @param row, col
     * @return void
     */
    public void clearShipPoint(char row, int col) {
        setSidePoint(Constants.MINE, row, col, 0);
        setForbiddenCells();
    }

    /**
     * метод для проверки занятости ячейки
     *
     * @param row, col, value
     * @return void
     */
    private boolean isOccupiedCell(char row, int col, int value) {
        List<HorizontalLine> minePanelSingletons = MinePanelSingleton.instance(null).getPanel();
        for (int i = 0; i < minePanelSingletons.size(); i++) {
            if (minePanelSingletons.get(i).getRow() == row) {
                for (int j = 0; j < minePanelSingletons.size(); j++) {
                    if (minePanelSingletons.get(i).getPointElementList().get(j).getCol() == col) {
                        return minePanelSingletons.get(i).getPointElementList().get(j).getValue() == value;
                    }
                }
            }
        }
        return false;
    }

    /**
     * метод для установки запрещенных ячеек
     *
     * @return void
     */
    private void setForbiddenCells() {
        Map<String, Boolean> forbiddenCellsMap = ForbiddenCellsSingleton.instance(null).getForbiddenCellsMap();
        forbiddenCellsMap.clear();
        List<HorizontalLine> horizontalLineList = MinePanelSingleton.instance(null).getPanel();
        for (int i = 0; i < horizontalLineList.size(); i++) {
            for (int j = 0; j < horizontalLineList.size(); j++) {
                if (horizontalLineList.get(i).getPointElementList().get(j).getValue() == 1) {
                    int col = horizontalLineList.get(i).getPointElementList().get(j).getCol();
                    char row = horizontalLineList.get(i).getRow();
                    forbiddenCellsMap.put(row + "" + NumberUtils.currentNumber(col), true);
                    forbiddenCellsMap.put(StringUtils.letterAfter(row) + NumberUtils.numberAfter(col), true);
                    forbiddenCellsMap.put(StringUtils.letterBefore(row) + NumberUtils.numberBefore(col), true);
                    forbiddenCellsMap.put(StringUtils.letterAfter(row) + NumberUtils.numberBefore(col), true);
                    forbiddenCellsMap.put(StringUtils.letterBefore(row) + NumberUtils.numberAfter(col), true);
                }
            }
        }
    }
}