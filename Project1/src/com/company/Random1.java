package com.company;

import java.util.ArrayList;
import java.util.List;

public class Random1 {
    public static void main(String[] args) {
        list();
        sort();
        show();
    }

    public static List<Integer> list = new ArrayList<Integer>();
    public static List<Integer> help = new ArrayList<Integer>();
    public static List<Integer> reped = new ArrayList<Integer>();

    public static void list() {
        for (int i = 0; i < 200; i++) {
            int a =-100+(int) (Math.random() * 201);
            list.add(a);
            reped.add(a);
        }
    }

    public static void sort() {
        for (int i = 0; i <list.size()-1 ; ) {
            if (list.get(i)>0){
                int j=i;
                for (;list.get(j)>0;j++){
                    if(j<199){
                        help.add(list.get(j));
                    }else {
                        help.add(list.get(j));
                        j++;
                        break;
                    }
                }
                j--;
                if(help.size()>1){
                    list.set(i,(help.get(0)+help.get(help.size()-1))/2);
                    list.set(j,(help.get(0)+help.get(help.size()-1))/2);
                }
                help.clear();
                i =j+1;
            }else i++;
        }
        for (int i = 0; i <list.size()-1 ; ) {
            if (list.get(i)<0){
                int j=i;
                for (;list.get(j)<0;j++){
                    if(j<199){
                        help.add(list.get(j));
                    }else {
                        help.add(list.get(j));
                        j++;
                        break;
                    }
                }
                j--;
                if(help.size()>1){
                    list.set(i,(help.get(0)+help.get(help.size()-1))/2);
                    list.set(j,(help.get(0)+help.get(help.size()-1))/2);
                }
                help.clear();
                i =j+1;
            }else i++;
        }
    }

    public static void show() {
        for (int i = 0; i < 199; i++) {
            System.out.print(reped.get(i)+", ");;
        }
        System.out.println(reped.get(199));
        for (int i = 0; i < 199; i++) {
            System.out.print(list.get(i)+", ");;
        }
        System.out.println(list.get(199));
        System.out.println(list.size());
    }
}
