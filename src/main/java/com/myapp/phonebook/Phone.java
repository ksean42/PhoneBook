package com.myapp.phonebook;

public class Phone implements Comparable<Phone>{
    String name;
    String number;

    public Phone(String str) {
        String[] input;
        input = str.split(" ", 2);
        name = input[1];
        number = input[0];
    }

    @Override
    public int compareTo(Phone o) {
        return name.compareTo(o.name);
    }
}
