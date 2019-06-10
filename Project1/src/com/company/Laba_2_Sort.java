package com.company;

public class Laba_2_Sort {
    public static void main(String[] args) {
        sort();
        show();
    }
    static int[] A = new int[]{6,2,3,1,4,5};
    static void sort(){
        for (int i = 0; i <A.length ; i++) {
            for (int j = i+1; j <A.length ; j++) {
                if(A[i]>A[j]){
                    int help=A[i];
                    A[i]=A[j];
                    A[j]=help;

                }
            }
        }
    }
    static void show(){
        for (int i = 0; i <A.length; i++) {
            System.out.println(A[i]);
        }
    }
}
