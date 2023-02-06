package edu.javagroup.seabattle.singleton;
/**
 * @author Denis Tarasevich, Elena Saponenko,  Elena Zayceva, Olga Allenova, Siarhey Khulup
 * @version 1.0
 * @see HorizontalLine
 */

import lombok.Getter;

public class EnemyReadySingleton {
    private static EnemyReadySingleton instance;
    @Getter
    private final Boolean enemyReady;

    private EnemyReadySingleton(Boolean enemyReady) {
        this.enemyReady = enemyReady;
    }

    public static EnemyReadySingleton instance(Boolean enemyReady) {
        if (instance == null) {
            instance = new EnemyReadySingleton(false);
        }
        if (enemyReady != null) {
            instance = new EnemyReadySingleton(enemyReady);
        }
        return instance;
    }
}
