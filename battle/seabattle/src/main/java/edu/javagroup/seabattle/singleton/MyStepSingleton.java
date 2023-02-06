package edu.javagroup.seabattle.singleton;
/**
 * @author Denis Tarasevich, Elena Saponenko,  Elena Zayceva, Olga Allenova, Siarhey Khulup
 * @version 1.0
 * @see HorizontalLine
 */

import lombok.Getter;

public class MyStepSingleton {
    private static MyStepSingleton instance;
    @Getter
    private final Boolean myStep;

    private MyStepSingleton(Boolean myStep) {
        this.myStep = myStep;
    }

    public static MyStepSingleton instance(Boolean myStep) {
        if (instance == null) {
            instance = new MyStepSingleton(true);
        }
        if (myStep != null) {
            instance = new MyStepSingleton(myStep);
        }
        return instance;
    }
}

