package edu.javagroup.seabattle.model;
/**
 * @author Denis Tarasevich
 * @version 1.0
 * @see ModelValue
 * Comparable
 */

import edu.javagroup.seabattle.model.parent.ModelValue;
import lombok.Getter;



public class PointElement extends ModelValue implements Comparable<PointElement> {
    @Getter
    private final int col;

    public PointElement(int col, int value) {
        super(value);
        this.col = col;
    }

    @Override
    public int compareTo(PointElement o) {
        return this.col - o.col;
    }
}
