package com.calc;

import java.io.IOException;
import java.util.Arrays;

class ExpressionChecking {
    private final static String[] ARABIC = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private final static String[] ROMAN = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    private final static String[] OPERATORS = {"+", "-", "*", "/"};
    private static final boolean[][] flags = new boolean[3][3];

    private static boolean hasTwoArabic;
    private static boolean hasTwoRoman;
    private static boolean hasOneOperator;
    private static boolean hasOneArabic;
    private static boolean hasOneRoman;

    private static boolean correctOperator;
    private static boolean correctArabic;
    private static boolean correctRoman;
    private static ExpressionType exprType;

    private static void checkFlags (String[] args) throws IOException {
        if (args.length != 3) throw new IOException("Incorrect number of input arguments.");

        if (Arrays.asList(ARABIC).contains(args[0])) flags[0][0] = true;
        if (Arrays.asList(ARABIC).contains(args[1])) flags[0][1] = true;
        if (Arrays.asList(ARABIC).contains(args[2])) flags[0][2] = true;
        if (Arrays.asList(OPERATORS).contains(args[0])) flags[1][0] = true;
        if (Arrays.asList(OPERATORS).contains(args[1])) flags[1][1] = true;
        if (Arrays.asList(OPERATORS).contains(args[2])) flags[1][2] = true;
        if (Arrays.asList(ROMAN).contains(args[0])) flags[2][0] = true;
        if (Arrays.asList(ROMAN).contains(args[1])) flags[2][1] = true;
        if (Arrays.asList(ROMAN).contains(args[2])) flags[2][2] = true;
    }

    private static void analyzeFlags () {
        hasTwoArabic = flags[0][0] & (flags[0][1] ^ flags[0][2]) | !flags[0][0] & flags[0][1] & flags[0][2];
        hasTwoRoman = flags[2][0] & (flags[2][1] ^ flags[2][2]) | !flags[2][0] & flags[2][1] & flags[2][2];
        hasOneOperator = !flags[1][0] & (flags[1][1] ^ flags[1][2]) | flags[1][0] & !flags[1][1] & !flags[1][2];
        hasOneArabic = !flags[0][0] & (flags[0][1] ^ flags[0][2]) | flags[0][0] & !flags[0][1] & !flags[0][2];
        hasOneRoman = !flags[2][0] & (flags[2][1] ^ flags[2][2]) | flags[2][0] & !flags[2][1] & !flags[2][2];

        correctOperator = flags[1][1];
        correctArabic = flags[0][0] && flags[0][2];
        correctRoman = flags[2][0] && flags[2][2];
    }

    public static Expression check(String[] args) throws IOException {

        checkFlags(args);
        analyzeFlags();

        if (hasOneArabic && hasOneRoman)
            throw new IOException("Incorrect input of numbers. You can't mix roman and arabic.");
        if (!hasOneOperator)
            throw new IOException("Incorrect input. There should be one operator.");
        if (hasTwoArabic && !correctArabic)
            throw new IOException("Incorrect arabic sequence. Operator should be between numbers.");
        if (hasOneArabic && !correctArabic)
            throw new IOException("Incorrect arabic sequence. Operator should be between numbers and you can only use from 1 to 10.");
        if (hasTwoRoman && !correctRoman)
            throw new IOException("Incorrect roman sequence. Operator should be between numbers.");
        if (hasOneRoman && !correctArabic)
            throw new IOException("Incorrect roman sequence. Operator should be between numbers and you can only use from I to X.");
        if (hasOneArabic && correctOperator)
            throw new IOException("Incorrect arabic number. You can only use from 1 to 10.");
        if (hasOneRoman && correctOperator)
            throw new IOException("Incorrect roman number. You can only use from I to X.");
        if (correctOperator && !hasTwoArabic && !hasTwoRoman)
            throw new IOException("Incorrect input of numbers. You can either use arabic (from 1 to 10) or roman (from I to X).");

        if (correctArabic && correctOperator) exprType = ExpressionType.ARABIC;
        if (correctRoman && correctOperator) exprType = ExpressionType.ROMAN;

        if (exprType == ExpressionType.ROMAN && args[1].charAt(0) == '-')
            throw new IOException("Incorrect operator. Roman numbers can't be negative.");

        return new Expression(exprType, args[0], args[1].charAt(0), args[2]);
    }
}
