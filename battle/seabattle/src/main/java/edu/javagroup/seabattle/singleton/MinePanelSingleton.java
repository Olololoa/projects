package edu.javagroup.seabattle.singleton;
/**
 * @author Denis Tarasevich, Elena Saponenko,  Elena Zayceva, Olga Allenova, Siarhey Khulup
 * @version 1.0
 * @see HorizontalLine
 */

import edu.javagroup.seabattle.model.HorizontalLine;
import lombok.Getter;

import java.util.*;

public final class MinePanelSingleton {
    private static MinePanelSingleton instance;
    @Getter
    private final List<HorizontalLine> panel;

    private MinePanelSingleton(List<HorizontalLine> panel) {
        this.panel = panel;
        Collections.sort(panel);
    }

    public static MinePanelSingleton instance(List<HorizontalLine> panel) {
        if (instance == null) {
            instance = new MinePanelSingleton(new ArrayList<>(0));
        }
        if (panel != null) {
            instance = new MinePanelSingleton(panel);
        }
        return instance;
    }
}


