package edu.javagroup.seabattle.singleton;
/**
 * @author Denis Tarasevich, Elena Saponenko,  Elena Zayceva, Olga Allenova, Siarhey Khulup
 * @version 1.0
 * @see HorizontalLine
 */

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ShipStorageSingleton {
    private static ShipStorageSingleton instance;
    private final Map<String, Integer> shipMap;

    private ShipStorageSingleton(Map<String, Integer> shipMap) {
        this.shipMap = shipMap;
    }

    public static ShipStorageSingleton instance(Map<String, Integer> shipMap) {
        if (instance == null) {
            instance = new ShipStorageSingleton(new HashMap<>(0));
        }
        if (shipMap != null) {
            instance = new ShipStorageSingleton(shipMap);
        }
        return instance;
    }
}
