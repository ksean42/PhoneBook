package com.myapp.phonebook;

import java.util.Hashtable;
import java.util.List;

public class Search {
    static boolean tooLong = false;
    public static int linearSearch(List<Phone> dir, List<String> find) {
        int count = 0;

        for(String i : find) {
            if(findLinear(dir, i))
                count++;
        }
        return count;
    }

    private static boolean findLinear(List<Phone> dir, String str) {
        for(Phone i : dir) {
            if(i.name.equals(str)){
                return true;
            }
        }
        return false;
    }

    public static int jumpingSearch(List<Phone> hay, List<String> needle) {
        int count = 0;
        for(String i : needle) {
            if(findJump(hay, i))
                count++;
        }
        return count;
    }

    public static boolean findJump(List<Phone> hay, String needle) {
        int len = hay.size();
        int block = (int)Math.sqrt(len);
        int i = 1;
        int insideBlock;

        while(i < len) {
            if(hay.get(i).name.equals(needle))
                return true;
            if(hay.get(i).name.compareTo(needle) > 0) {
                insideBlock = i - block;
                while(insideBlock > 0 && insideBlock <= i) {
                    if(hay.get(insideBlock).name.equals(needle))
                        return true;
                    insideBlock++;
                }
                return false;
            }
            i += block;
        }
        insideBlock = hay.size()  - block;
        while(insideBlock < hay.size()) {
            if(hay.get(insideBlock).name.equals(needle))
                return true;
            insideBlock++;
        }
        return false;
    }

    public static int binarySearch(List<Phone> hay, List<String> needle) {

        int count = 0;
        for(String i : needle) {
            if(findBinary(hay, i, 0, hay.size() - 1))
                count++;
        }
        return count;
    }

    public static boolean findBinary(List<Phone> hay, String needle, int low, int high) {
        int middle = (high + low) / 2;
        Phone mid = hay.get(middle);
        if(hay.get(low).name.equals(needle) || hay.get(high).name.equals(needle) || mid.name.equals(needle))
            return true;

        if(mid.name.compareTo(needle) < 0 && findBinary(hay, needle, middle + 1, high))
            return true;
        else if(mid.name.compareTo(needle) > 0 && findBinary(hay, needle, low, middle - 1))
            return true;

        return false;
    }

    public static int findInHashTable(Hashtable<String, String> table, List<String> find) {
        int count = 0;
        for(String i : find) {
            if (table.containsKey(i))
                count++;
        }
        return count;
    }

}
