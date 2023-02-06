package org.example.operations.impl;

import org.example.operations.Operations;

public class OperationsImpl implements Operations {
    @Override
    public int Addition(int firstsNumb, int secondNumb) {
        return firstsNumb + secondNumb;
    }

    @Override
    public int Subtraction(int firstsNumb, int secondNumb) {
        return firstsNumb - secondNumb;
    }

    @Override
    public int Multiplication(int firstsNumb, int secondNumb) {
        return firstsNumb * secondNumb;
    }

    @Override
    public int Division(int firstsNumb, int secondNumb) {
        return firstsNumb / secondNumb;
    }
}

