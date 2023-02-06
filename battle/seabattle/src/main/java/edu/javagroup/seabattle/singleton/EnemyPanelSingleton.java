package edu.javagroup.seabattle.singleton;
/**
 * @author Denis Tarasevich, Elena Saponenko
 * @version 1.0
 * @see HorizontalLine
 */

import edu.javagroup.seabattle.model.HorizontalLine;
import lombok.Getter;

import java.util.*;

@Getter
public class EnemyPanelSingleton {
    private static EnemyPanelSingleton instance;
    public final List<HorizontalLine> panel;

    private EnemyPanelSingleton(List<HorizontalLine> panel) {
        this.panel = panel;
        Collections.sort(panel);
    }

    public static EnemyPanelSingleton instance(List<HorizontalLine> panel) {
        if (instance == null) {
            instance = new EnemyPanelSingleton(new ArrayList<>(0));
        }
        if (panel != null) {
            instance = new EnemyPanelSingleton(panel);
        }
        return instance;
    }
}