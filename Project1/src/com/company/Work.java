package com.company;

import java.util.Scanner;

public class Work {

    public static void main(String[] args) {
        figure();
    }

    public static void figure() {
        while (true) {
            try {
                System.out.println("Write two numbers");
                metod1();
                break;

            } catch (Exception e) {
                System.out.println("Error");

            }
        }

    }

    public static void metod1() {
        Scanner scan = new Scanner(System.in);

        float x = scan.nextFloat();
        float y = scan.nextFloat();

        if ((1 >= x) && (x >= -1) && (1 >= y) && y >= -1 && ((x * x + y * y <= 1) || y >= 0)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
