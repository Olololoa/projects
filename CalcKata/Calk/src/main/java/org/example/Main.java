package org.example;

import org.example.calcExeption.CalcException;
import org.example.operations.Operations;
import org.example.converter.Converter;
import org.example.operations.impl.OperationsImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение из 2х чисел (Арабских или Римских): ");
        String exp = scanner.nextLine().replaceAll("\\s+", "");
        Operations operations = new OperationsImpl();
        Converter converter = new Converter();
        String symbol;
        if (exp.contains("+")) {
            symbol = "\\+";
        } else if (exp.contains("-")) {
            symbol = "-";
        } else if (exp.contains("*")) {
            symbol = "\\*";
        } else if (exp.contains("/")) {
            symbol = "/";
        } else {
            throw new CalcException("Неорректная математическая операция");
        }

        String[] numbers = exp.split(symbol);
        if (numbers.length != 2)
            throw new CalcException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

        if (converter.isRoman(numbers[0]) == converter.isRoman(numbers[1])) {
            int firstsNumb;
            int secondNumb;
            if (converter.isRoman(numbers[0])) {
                firstsNumb = converter.romanToInt(numbers[0]);
                secondNumb = converter.romanToInt(numbers[1]);
            } else {
                firstsNumb = Integer.parseInt(numbers[0]);
                secondNumb = Integer.parseInt(numbers[1]);
            }
            if (firstsNumb > 10 || secondNumb > 10) {
                throw new CalcException("Числа должны быть от 1 до 10");
            }
            int result;
            switch (symbol) {
                case "\\+":
                    result = operations.Addition(firstsNumb, secondNumb);
                    break;
                case "-":
                    result = operations.Subtraction(firstsNumb, secondNumb);
                    break;
                case "\\*":
                    result = operations.Multiplication(firstsNumb, secondNumb);
                    break;
                default:
                    result = operations.Division(firstsNumb, secondNumb);
                    break;
            }
            if (converter.isRoman(numbers[0])) {
                if (result <= 0) {
                    throw new CalcException("в римской системе нет отрицательных чисел");
                }
                System.out.println(converter.intToRoman(result));
            } else {
                System.out.println(result);
            }
        } else {
            throw new CalcException("используются одновременно разные системы счисления");
        }
    }
}
