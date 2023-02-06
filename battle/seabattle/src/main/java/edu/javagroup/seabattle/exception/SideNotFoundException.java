package edu.javagroup.seabattle.exception;

/**
 * @author Elena Saponenko
 * @version 1.0
 * класс исключений
 */
public class SideNotFoundException extends RuntimeException {
    public SideNotFoundException() {
        this("Уточните сторону (MINE or ENEMY)");
    }

    public SideNotFoundException(String message) {
        super(message);
    }
}

