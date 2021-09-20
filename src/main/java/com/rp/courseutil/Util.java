package com.rp.courseutil;

import com.github.javafaker.Faker;

import java.util.function.Consumer;

public class Util {

    public static Consumer<Object> onNext() {
        return o -> System.out.println("Received : "+ o);
    }

    public static Consumer<Throwable> onError() {
        return o -> System.out.println("Error : "+ o.getMessage());
    }

    public static Runnable onComplete() {
        return () -> System.out.println("Completed");
    }

    private static final Faker FAKER = Faker.instance();

    public static Faker faker() {
        return FAKER;
    }

    public static void sleepSeconds(Integer seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
