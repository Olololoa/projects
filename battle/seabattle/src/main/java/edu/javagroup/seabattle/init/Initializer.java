package edu.javagroup.seabattle.init;
/**
 * @author Elena Saponenko, Elena Zayceva, Olga Allenova
 * @version 1.0
 * @see HorizontalLine,
 * SettingsSingleton,
 * MyStepSingleton,
 * ImReadySingleton,
 * EnemyPanelSingleton,
 * ForbiddenCellsSingleton,
 */

import edu.javagroup.seabattle.model.HorizontalLine;
import edu.javagroup.seabattle.singleton.*;

import java.util.*;

import static edu.javagroup.seabattle.constants.Constants.DECK;
import static edu.javagroup.seabattle.constants.Constants.VERTICAL_COORDINATE;

public class Initializer {
    public void init() {
        SettingsSingleton.instance(new HashMap<>(0));
        initPanels();
    }

    /**
     * метод для создания панели инициализации
     *
     * @return void
     */
    public void initPanels() {
        MyStepSingleton.instance(true);
        ImReadySingleton.instance(false);
        EnemyReadySingleton.instance(false);
        ForbiddenCellsSingleton.instance(new HashMap<>(0));
        Map<String, Integer> deckMapInitPanels = new HashMap<>();
        for (String s : Arrays.asList("1", "2", "3", "4")) {
            deckMapInitPanels.put(s + DECK, 0);
        }
        ShipStorageSingleton.instance(deckMapInitPanels);
        List<HorizontalLine> minePanel = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            minePanel.add(new HorizontalLine(VERTICAL_COORDINATE.charAt(i)));
        }
        MinePanelSingleton.instance(minePanel);
        List<HorizontalLine> enemyPanel = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            enemyPanel.add(new HorizontalLine(VERTICAL_COORDINATE.charAt(i)));
        }
        EnemyPanelSingleton.instance(enemyPanel);
    }
}