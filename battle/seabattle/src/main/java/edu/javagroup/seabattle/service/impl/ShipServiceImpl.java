package edu.javagroup.seabattle.service.impl;
/**
 * @author Denis Tarasevich, Khulup Siarhey
 * @version 1.0
 * @see SettingsSingleton
 * ImReadySingleton
 * ForbiddenCellsSingleton
 * PanelService
 * PointService
 */

import edu.javagroup.seabattle.model.HorizontalLine;
import edu.javagroup.seabattle.model.PointElement;
import edu.javagroup.seabattle.model.ShipPoint;
import edu.javagroup.seabattle.service.ShipService;
import edu.javagroup.seabattle.singleton.MinePanelSingleton;
import edu.javagroup.seabattle.singleton.ShipStorageSingleton;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static edu.javagroup.seabattle.constants.Constants.*;


@Component
public class ShipServiceImpl implements ShipService {
    private List<ShipPoint> coordinateList;
    /**
     * метод для сбора кораблей
     *
     * @param list

     */
    public void getCoordinateList(List<HorizontalLine> list) {
        coordinateList = new ArrayList<>(220);
        coordinateList.addAll(getHorizontalCoordinateList(list));
        coordinateList.addAll(getVerticalCoordinateList(list));
        List<ShipPoint> shipPoints = new ArrayList<>();
        for (int i = 0; i < coordinateList.size() - 1; i++) {
            if (coordinateList.get(i).getValue() == 0 && coordinateList.get(i + 1).getValue() == 0) {
                shipPoints.add(coordinateList.get(i));
            }
        }
        coordinateList.removeAll(shipPoints);
        Collections.sort(coordinateList);
    }

    /**
     * метод для сбора кораблей по горизонтали
     *
     * @param horizontalLineList
     * @return List<ShipPoint>
     */
    private List<ShipPoint> getHorizontalCoordinateList(List<HorizontalLine> horizontalLineList) {
        int numVar = 1;
        List<ShipPoint> shipPoints = new ArrayList<>(110);
        for (HorizontalLine line : horizontalLineList) {
            List<PointElement> pointElements = line.getPointElementList();
            for (PointElement el : pointElements) {
                shipPoints.add(new ShipPoint(numVar, el.getValue()));
                numVar++;
            }
            shipPoints.add(new ShipPoint(numVar, 0));
            numVar++;
        }
        return shipPoints;
    }

    /**
     * метод для сбора кораблей по вертикали
     *
     * @param horizontalLineList
     * @return List<ShipPoint>
     */
    private List<ShipPoint> getVerticalCoordinateList(List<HorizontalLine> horizontalLineList) {
        int numVar = 111;
        List<ShipPoint> shipPoints = new ArrayList<>(110);
        for (int i = 0; i < VERTICAL_COORDINATE.length(); i++) {
            for (int j = 0; j < VERTICAL_COORDINATE.length(); j++) {
                char symbol = VERTICAL_COORDINATE.charAt(j);
                HorizontalLine horizontalLine = horizontalLineList.stream().filter(el -> el.getRow() == symbol).collect(Collectors.toList()).get(0);
                PointElement pointElement = horizontalLine.getPointElementList().get(i);
                shipPoints.add(new ShipPoint(numVar, pointElement.getValue()));
                numVar++;
            }
            shipPoints.add(new ShipPoint(numVar, 0));
            numVar++;
        }
        return shipPoints;
    }

    /**
     * метод для проверки количества кораблей для каждой возможной размерности
     *
     * @return boolean
     */
    public boolean checkShipCount() {
        getCoordinateList(MinePanelSingleton.instance(null).getPanel());
        Map<String, Integer> shipMap = new HashMap<>();
        shipMap.put("4" + DECK, findShipDeck(4));
        shipMap.put("3" + DECK, findShipDeck(3));
        shipMap.put("2" + DECK, findShipDeck(2));
        shipMap.put("1" + DECK, findShipDeck(1));
        ShipStorageSingleton.instance(shipMap);
        return (shipMap.get("4" + DECK) == 1) && (shipMap.get("3" + DECK) == 2) && (shipMap.get("2" + DECK) == 3) && (shipMap.get("1" + DECK) == 4);
    }

    public int checkShipCount(int numberOfDecks) {
        // Надо вернуть количество кораблей указанной размерности (параметр метода)
        //примечание: реализация этого метода необязательна, см. task #16
        return 0;
    }

    /**
     * метод возвращает количество кораблей указанной размерности
     *
     * @param numShipDeck
     * @return int
     */
    private int findShipDeck(int numShipDeck) {
        String line = "";
        int count;
        for (ShipPoint point : coordinateList) {
            line = line + point.getValue();
        }
        String[] stringSplit = line.split("0");
        count = (int) Arrays.stream(stringSplit).filter(str -> str.length() == numShipDeck).count();
        if (numShipDeck == 1) {
            count = count / 6;
        }
        return count;
    }
}
