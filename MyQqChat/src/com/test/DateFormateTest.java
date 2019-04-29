package com.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormateTest {
    public static void main(String[] args) {
        DateFormat f1 = DateFormat.getDateTimeInstance();
        DateFormat f2 = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
        System.out.println(f1.format(new Date()));
        System.out.println(f2.format(new Date()));
        System.out.println("10011 0012 0112 ".trim());
        System.out.println("10011 0012 0112 ".strip());
        //String[] info = "10087 10088 ".split(" ");
        String[] info = "10087".split(" ");
        System.out.println(info.length);
        for(String str:info){
            System.out.println(str);
        }
        //System.out.println(info[1]+"aaa");
    }
}
