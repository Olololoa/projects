package edu.javagroup.seabattle.service.impl;
/**
 * @author Elena Zayceva, Olga Allenova
 * @version 1.0
 * @see PanelService
 * PointElement
 * MinePanelSingleton
 * EnemyPanelSingleton
 */

import edu.javagroup.seabattle.exception.SideNotFoundException;
import edu.javagroup.seabattle.model.HorizontalLine;
import edu.javagroup.seabattle.model.PointElement;
import edu.javagroup.seabattle.service.PanelService;
import edu.javagroup.seabattle.singleton.EnemyPanelSingleton;
import edu.javagroup.seabattle.singleton.ImReadySingleton;
import edu.javagroup.seabattle.singleton.MinePanelSingleton;
import org.springframework.stereotype.Component;

import java.util.List;

import static edu.javagroup.seabattle.constants.Constants.ENEMY;
import static edu.javagroup.seabattle.constants.Constants.MINE;

@Component
public class PanelServiceImpl implements PanelService {
    /**
     * метод получает коллекцию из MinePanelSingleton и считает в нем количество PointElement с value == 0
     * если это количество == 100, возвращает true
     *
     * @return boolean
     */
    @Override
    public boolean isPanelEmpty() {
        int count = valueCount(MINE, 0);
        return count == 100;
    }

    /**
     * метод получает коллекцию из MinePanelSingleton и считает в нем количество PointElement с value == 1
     * если это количество == 20, возвращает true
     *
     * @return boolean
     */
    @Override
    public boolean isFullMinePanel() {
        int count = valueCount(MINE, 1);
        return count == 20;
    }

    /**
     * метод считает количество подбитых палуб на панели игрока либо противника
     *
     * @param side
     * @return int
     */
    @Override
    public int howMuchIsLeft(String side) {
        int count = valueCount(side, 2);
        if (ImReadySingleton.instance(null).getImReady()) {
            return 20 - count;
        } else {
            return 0;
        }
    }

    /**
     * метод считает количество подбитых палуб на панели игрока либо противника
     *
     * @param side(MINE or ENEMY)
     * @return int
     */
    @Override
    public boolean checkEndGame(String side) {
        int count = valueCount(side, 2);
        return count == 20;
    }

    /**
     * метод для подсчета ячеек имеющих value==2
     *
     * @param side(MINE or ENEMY), value
     * @return int
     */
    private int valueCount(String side, int value) {
        List<HorizontalLine> panel;
        if (side.equalsIgnoreCase(MINE)) {
            panel = MinePanelSingleton.instance(null).getPanel();
        } else if (side.equalsIgnoreCase(ENEMY)) {
            panel = EnemyPanelSingleton.instance(null).getPanel();
        } else {
            throw new SideNotFoundException();
        }
        int count = 0;
        for (HorizontalLine horizontalLine : panel) {
            List<PointElement> pointElementList = horizontalLine.getPointElementList();
            for (PointElement pointElement : pointElementList) {
                if (pointElement.getValue() == value) {
                    count++;
                }
            }

        }
        return count;
    }
}