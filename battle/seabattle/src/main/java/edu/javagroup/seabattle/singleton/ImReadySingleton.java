package edu.javagroup.seabattle.singleton;
/**
 * @author Denis Tarasevich, Elena Saponenko,  Elena Zayceva, Olga Allenova, Siarhey Khulup
 * @version 1.0
 * @see HorizontalLine
 */

import lombok.Getter;


public class ImReadySingleton {
    private static ImReadySingleton instance;
    @Getter
    private final Boolean imReady;

    private ImReadySingleton(Boolean imReady) {
        this.imReady = imReady;
    }

    public static ImReadySingleton instance(Boolean imReady) {
        if (instance == null) {
            instance = new ImReadySingleton(false);
        }
        if (imReady != null) {
            instance = new ImReadySingleton(imReady);
        }
        return instance;
    }
}
