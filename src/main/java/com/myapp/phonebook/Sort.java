package com.myapp.phonebook;

import java.util.*;

public class Sort {
    static boolean tooLong = false;
    public static void bubbleSort(List<Phone> list, long start) {
        Phone temp;
        boolean sorted = false;
        while(!sorted) {
            sorted = true;
            long currTime = System.currentTimeMillis();
            if((currTime-start)/60000 > 1) {
                tooLong = true;
                return;
            }
            for(int i = 1; i < list.size(); i++) {
                if(list.get(i).compareTo(list.get(i - 1)) < 0) {
                    temp = list.get(i);
                    list.set(i, list.get(i - 1));
                    list.set(i - 1, temp);
                    sorted = false;
                }
            }
        }
    }

    public static boolean isSorted(List<Phone> list) {
        for(int i = 1; i < list.size(); i++) {
            if(list.get(i).compareTo(list.get(i - 1)) < 0)
                return false;
        }
        return true;
    }

    public static void quickSort(List<Phone> dir, int low, int high, long start) {
//        long currTime = System.currentTimeMillis();
//        if((currTime-start)/60000 > 1) {
//            tooLong = true;
//            return;
//        }
        if(low < high) {
            int pi = partition(dir, low, high);
            quickSort(dir, low, pi - 1, start);
            quickSort(dir, pi + 1, high,start);
        }


    }

    public static int partition(List<Phone> dir, int low, int high) {
       Phone pivot = dir.get(high);
       int i = low;
       while(i < high) {
           if(dir.get(i).compareTo(pivot) > 0) {
               dir.add(high + 1, dir.get(i));
               dir.remove(i);
               high--;
               continue;
           }
           i++;
       }
       return high;
    }
}
