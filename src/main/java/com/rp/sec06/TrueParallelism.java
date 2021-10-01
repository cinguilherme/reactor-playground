package com.rp.sec06;

import com.rp.courseutil.DefaultSubscriber;
import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class TrueParallelism {
    public static void main(String[] args) {

        Flux.range(1, 910000)
                .parallel(4, 10)
                .runOn(Schedulers.parallel())
                .doOnNext(System.out::println)
                .map(i -> i * 2)
                .subscribe(new DefaultSubscriber("Paralles"));

        Util.sleepSeconds(3);

    }
}
