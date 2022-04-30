package com.calc;

import java.util.Map;

class Roman {

    private static final Map<Character,Integer> NUMS = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100
    );

    public static String fromRomanToArabic (String roman) {
        int current;
        int last = 0;
        int arabic = 0;

        for (char c : roman.toCharArray()) {
            current = NUMS.get(c);

            if (last < current) arabic += current - 2*last;
            if (last >= current) arabic += current;

            last = current;
        }

        return Integer.toString(arabic);
    }

    public static String fromArabicToRoman (String arabic) {
        String roman = "";

        int arabicInt = Integer.parseInt(arabic);

        int decade = arabicInt / 10;
        int unit = arabicInt % 10;

        switch (decade) {
            case 1 -> roman += "X";
            case 2 -> roman += "XX";
            case 3 -> roman += "XXX";
            case 4 -> roman += "XL";
            case 5 -> roman += "L";
            case 6 -> roman += "LX";
            case 7 -> roman += "LXX";
            case 8 -> roman += "LXXX";
            case 9 -> roman += "XC";
            case 10 -> roman += "C";
        }

        switch (unit) {
            case 1 -> roman += "I";
            case 2 -> roman += "II";
            case 3 -> roman += "III";
            case 4 -> roman += "IV";
            case 5 -> roman += "V";
            case 6 -> roman += "VI";
            case 7 -> roman += "VII";
            case 8 -> roman += "VIII";
            case 9 -> roman += "IX";
        }


        return roman;
    }

    public static String solve (Expression expression) throws ArithmeticException {
        String arabicX1 = fromRomanToArabic(expression.getX1());
        String arabicX2 = fromRomanToArabic(expression.getX2());

        if (expression.getOperator() == '/' && Integer.parseInt(arabicX1) < Integer.parseInt(arabicX2))
            throw new ArithmeticException("Incorrect result. Roman solution can't be less than 1.");

        Expression arabicExpression = new Expression(arabicX1, expression.getOperator(), arabicX2);

        return fromArabicToRoman(Arabic.solve(arabicExpression));
    }
}
