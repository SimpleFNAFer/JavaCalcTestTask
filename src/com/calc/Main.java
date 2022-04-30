package com.calc;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        try {
            System.out.println(calc(input));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String calc (String input) throws IOException{

        String[] args = input.split(" ");
        Expression expression = ExpressionChecking.check(args);

        return expression.solve();
    }
}
