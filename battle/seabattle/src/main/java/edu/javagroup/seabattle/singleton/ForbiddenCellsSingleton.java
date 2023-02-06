package edu.javagroup.seabattle.singleton;
/**
 * @author Denis Tarasevich, Elena Saponenko,  Elena Zayceva, Olga Allenova, Siarhey Khulup
 * @version 1.0
 * @see HorizontalLine
 */

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


public class ForbiddenCellsSingleton {
    private static ForbiddenCellsSingleton instance;
    @Getter
    private final Map<String, Boolean> forbiddenCellsMap;

    private ForbiddenCellsSingleton(Map<String, Boolean> forbiddenCellsMap) {
        this.forbiddenCellsMap = forbiddenCellsMap;
    }

    public static ForbiddenCellsSingleton instance(Map<String, Boolean> forbiddenCellsMap) {
        if (instance == null) {
            instance = new ForbiddenCellsSingleton(new HashMap<>(0));
        }
        if (forbiddenCellsMap != null) {
            instance = new ForbiddenCellsSingleton(forbiddenCellsMap);
        }
        return instance;
    }
}
