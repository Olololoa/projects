package org.example.converter;

import java.util.TreeMap;

public class Converter {
    private final TreeMap<Character, Integer> romanMap = new TreeMap<>();
    private final TreeMap<Integer, String> arabianMap = new TreeMap<>();


    public Converter() {
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);

        arabianMap.put(100, "C");
        arabianMap.put(90, "XC");
        arabianMap.put(50, "L");
        arabianMap.put(40, "XL");
        arabianMap.put(10, "X");
        arabianMap.put(9, "IX");
        arabianMap.put(5, "V");
        arabianMap.put(4, "IV");
        arabianMap.put(1, "I");
    }

    public boolean isRoman(String number) {
        return romanMap.containsKey(number.charAt(0));
    }

    public String intToRoman(int number) {
        StringBuilder roman = new StringBuilder();
        int rom;
        do {
            rom = arabianMap.floorKey(number);
            roman.append(arabianMap.get(rom));
            number -= rom;
        } while (number != 0);
        return roman.toString();
    }

    public int romanToInt(String s) {
        int end = s.length() - 1;
        char[] toChar = s.toCharArray();
        int rom;
        int result = romanMap.get(toChar[end]);
        for (int i = end - 1; i >= 0; i--) {
            rom = romanMap.get(toChar[i]);
            if (rom < romanMap.get(toChar[i + 1])) {
                result = result - rom;
            } else {
                result = result + rom;
            }
        }
        return result;
    }
}