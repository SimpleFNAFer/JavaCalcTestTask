package com.calc;

class Arabic {
    public static String solve (Expression expression) {
        int output = 0;
        int x1 = Integer.parseInt(expression.getX1());
        int x2 = Integer.parseInt(expression.getX2());
        char operator = expression.getOperator();

        switch (operator) {
            case '+' -> output = (x1 + x2);
            case '-' -> output = (x1 - x2);
            case '*' -> output = (x1 * x2);
            case '/' -> output = (x1 / x2);
        }

        return Integer.toString(output);
    }
}
