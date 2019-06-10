package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Sort {
    public static List<Integer> list = new ArrayList<Integer>();
    public static List<Integer> tcotni = new ArrayList<Integer>();
    public static List<Integer> netcotni = new ArrayList<Integer>();
    public static boolean isTrue = true;

    public static void main(String[] args) {
        main1();
        choise();
        sort();
        order_netcotni();
        order_tcotni();
    }

    //start
    public static void main1() {
        System.out.println("Write what you want");
        System.out.println("When you write '1'-you will be write number what you want ");
        System.out.println("When you write '2'-we use number what we have.");
        System.out.println("When you write '3'-computer choose random number.");
    }

    //first work
    public static void choise() {

        while (true) {
            try {
                System.out.println("Make your choose");
                done();
                break;
            } catch (Exception e) {
                System.out.println("You write no number");
            }
        }
    }

    public static void done() {
        while (true) {
            Scanner scan = new Scanner(System.in);
            int x = scan.nextInt();
            if (x == 1) {
                main2();
                break;
            } else {
                if (x == 2) {
                    have();
                    break;
                } else {
                    if (x == 3) {
                        random();
                        break;
                    } else {
                        System.out.println("Repead pleas");
                    }
                }

            }
        }

    }

    public static void have() {
        Collections.addAll(list, 23, 23, 23, 324, 43, 24, 5435, 35, 3543, 534, 43, 212, 435, 21, 545, 3215, 5);
    }

    public static void random() {
        for (int i = 0; i < 20; i++) {
            int a = (int) (Math.random() * 1001);
            list.add(a);
        }
    }

    //Don}'t write
    public static void main2() {
        System.out.println("Write number.");
        while (isTrue) {
            try {
                use();

            } catch (Exception e) {
                System.out.println("You write no number");
            }


        }
    }

    public static boolean use() {

        Scanner scan = new Scanner(System.in);
        int j = scan.nextInt();

        if (j == 0) {
            isTrue = false;
        } else {
            list.add(j);
        }
        return isTrue;
    }

    public static void sort() {
        for (int i = 0; i < list.size(); i++) {
            int j = list.get(i);
            if (j % 2 == 0) {
                tcotni.add(j);
            } else {
                netcotni.add(j);
            }

        }
    }

    //Third work
    public static void show_tcotni() {
        if (tcotni.size() > 0) {
            System.out.println("Numbers are pair");
            for (int i = 0; i + 1 < tcotni.size(); i++) {
                System.out.print(tcotni.get(i) + ",");
            }
            System.out.println(tcotni.get(tcotni.size() - 1));
        } else {
            System.out.print("We haven't pair number");
        }
    }

    //Fourth work
    public static void show_netcotni() {
        if (netcotni.size() > 0) {
            System.out.println();
            System.out.println("Numbers are odd");
            for (int i = 0; i < netcotni.size(); i++) {
                System.out.print(netcotni.get(i) + ",");
            }
            System.out.println(netcotni.get(netcotni.size() - 1));
        } else {
            System.out.println("We haven't odd number");
        }
    }

    public static void order_tcotni() {
        int temp;
        for (int i = 0; i < tcotni.size(); i++) {
            for (int j = i + 1; j < tcotni.size(); j++) {
                if (tcotni.get(i) > tcotni.get(j)) {
                    temp = tcotni.get(i);
                    tcotni.set(i, tcotni.get(j));
                    tcotni.set(j, temp);

                }
            }
        }

        //print sorted elements
        System.out.println("Pair Order:");
        for (int i = 0; i < tcotni.size()-1; i++) {
            System.out.print(tcotni.get(i) + ",");
        }
        System.out.println(tcotni.get(tcotni.size()-1));
    }
    public static void order_netcotni() {
        int temp;
        for (int i = 0; i < netcotni.size(); i++) {
            for (int j = i + 1; j < netcotni.size(); j++) {
                if (netcotni.get(i) > netcotni.get(j)) {
                    temp = netcotni.get(i);
                    netcotni.set(i, netcotni.get(j));
                    netcotni.set(j, temp);

                }
            }
        }

        //print sorted elements
        System.out.println("Odd Order:");
        for (int i = 0; i < netcotni.size()-1; i++) {
            System.out.print(netcotni.get(i) + ",");
        }
        System.out.println(netcotni.get(netcotni.size()-1));
    }
}
