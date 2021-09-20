package com.rp.sec01;

import com.rp.courseutil.Util;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lec06SuppRefactoring {

    public static void main(String[] args) {

        IntStream.range(0, 20)
                 .forEach(i -> getName()
                         .subscribeOn(Schedulers.boundedElastic())
                         .subscribe(Util.onNext()));

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");

    }

    private static Mono<String> getName() {
        System.out.println("entered get name method");

        return Mono.fromSupplier(Lec06SuppRefactoring::supplyName)
                   .map(String::toUpperCase);
    }

    private static String supplyName() {
        System.out.println("generating name...");
        Util.sleepSeconds(2);
        return Util.faker()
                   .name()
                   .fullName();
    }
}
