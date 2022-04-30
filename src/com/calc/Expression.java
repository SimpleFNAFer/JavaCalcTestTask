package com.calc;

class Expression {
    private ExpressionType type;
    private final String x1;
    private final String x2;
    private final char operator;

    public Expression(ExpressionType type, String x1, char operator, String x2) {
        this.type = type;
        this.x1 = x1;
        this.x2 = x2;
        this.operator = operator;
    }

    public Expression(String x1, char operator, String x2) {
        this.x1 = x1;
        this.x2 = x2;
        this.operator = operator;
    }

    public String solve() {
        return switch (this.type) {
            case ARABIC -> Arabic.solve(this);
            case ROMAN -> Roman.solve(this);
        };
    }

    public String getX1() {
        return x1;
    }

    public String getX2() {
        return x2;
    }

    public char getOperator() {
        return operator;
    }
}
