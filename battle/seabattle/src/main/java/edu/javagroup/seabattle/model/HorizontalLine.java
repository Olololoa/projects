package edu.javagroup.seabattle.model;
/**
 * @author Elena Saponenko
 * @version 1.0
 * @see ModelRow
 * PointElement
 * Comparable
 */

import edu.javagroup.seabattle.model.parent.ModelRow;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HorizontalLine extends ModelRow implements Comparable<HorizontalLine> {

    @Setter
    @Getter
    private List<PointElement> pointElementList;

    public HorizontalLine(char row) {
        super(row);
        pointElementList = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> new PointElement(i, 0))
                .collect(Collectors.toList());
    }

    @Override
    public int compareTo(HorizontalLine o) {
        return Character.compare(getRow(), o.getRow());
    }
}



