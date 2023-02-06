package edu.javagroup.seabattle.singleton;

import edu.javagroup.seabattle.common.utils.CommonService;
import edu.javagroup.seabattle.model.HorizontalLine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

/**
 * перед использованием раскомментировать классы
 * <p>
 * test -> edu.javagroup.seabattle.common.utils.CommonService
 * test -> edu.javagroup.seabattle.common.utils.impl.CommonServiceImpl
 *
 * @author kaa
 * @version 1.0
 */
@SpringBootTest
public class SingletonTest {

    @Autowired
    private CommonService commonService;

//    @Test
//    public void enemyPanelSingletonTest() {
//        List<HorizontalLine> list = commonService.getPanel00();
//        Collections.shuffle(list);
//        EnemyPanelSingleton.instance(list);
//        list = EnemyPanelSingleton.instance(null).getPanel();
//        for (char l = 'A', i = 0; l < 'J'; l++, i++) {
//            Assertions.assertEquals(l, list.get(i).getRow());
//        }
//    }

//    @Test
//    public void enemyReadySingletonTest() {
//        Assertions.assertFalse(EnemyReadySingleton.instance(null).getEnemyReady());
//    }
//
//    @Test
//    public void forbiddenCellsSingletonTest() {
//        Map<String, Boolean> map = ForbiddenCellsSingleton.instance(null).getForbiddenCellsMap();
//        Assertions.assertNotNull(map);
//        Assertions.assertEquals(0, map.size());
//        map.put("key", Boolean.TRUE);
//        Assertions.assertEquals(1, map.size());
//    }
//
//    @Test
//    public void imReadySingletonTest() {
//        Assertions.assertFalse(ImReadySingleton.instance(null).getImReady());
//    }

    @Test
    public void minePanelSingletonTest() {
        List<HorizontalLine> list = commonService.getPanel00();
        Collections.shuffle(list);
        MinePanelSingleton.instance(list);
        list = MinePanelSingleton.instance(null).getPanel();
        for (char l = 'A', i = 0; l < 'J'; l++, i++) {
            Assertions.assertEquals(l, list.get(i).getRow());
        }
    }
}

//    @Test
//    public void myStepSingletonTest() {
//        Assertions.assertTrue(MyStepSingleton.instance(null).getMyStep());
//    }
//
//    @Test
//    public void settingsSingletonTest() {
//        Map<String, String> map = SettingsSingleton.instance(null).getSettingsMap();
//        Assertions.assertNotNull(map);
//        Assertions.assertEquals(0, map.size());
//        map.put("key", "value");
//        Assertions.assertEquals(1, map.size());
//        Assertions.assertEquals("value", SettingsSingleton.instance(null).getSettingsByKey("key"));
//    }
//
//    @Test
//    public void shipStorageSingletonTest() {
//        Map<String, Integer> map = ShipStorageSingleton.instance(null).getShipMap();
//        Assertions.assertNotNull(map);
//        Assertions.assertEquals(0, map.size());
//        map.put("key", Integer.MAX_VALUE);
//        Assertions.assertEquals(1, map.size());
//    }
//}
