//package edu.javagroup.seabattle.constants;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Arrays;
//import java.util.stream.IntStream;
//
//import static edu.javagroup.seabattle.constants.Constants.*;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
///**
// * @author kaa
// * @version 2.0
// */
//@SpringBootTest
//public class ConstantsTest {
//
//    // смешной тест констант, не имеет смысла, но пришлось (тут должен быть тяжелый вздох)
//    @Test
//    void constantsTest() {
//        String all = Arrays.toString(LOCALHOST) + ENEMY_PROTOCOL + ENEMY_IP_ADDRESS + ENEMY_PORT + VERTICAL_COORDINATE
//                + DECK + BUTTON_PREFIX + MINE + ENEMY + M_BUTTON_PREFIX + E_BUTTON_PREFIX + BUTTON_POSTFIX;
//        assertThat(IntStream.range(0, all.length()).map(all::charAt).sum() == 7962).isTrue();
//    }
//}
