package com.rp.sec01;

import java.util.stream.Stream;

public class Lec01Stream {

    public static void main(String[] args) {
        Stream.of(1, 2, 3, 4,5,6,7).map(it -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return it * 2;
        }).forEach(n -> System.out.println(n));

        //System.out.println(integerStream);
    }
}
