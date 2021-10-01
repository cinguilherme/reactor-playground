package com.rp.sec06;

import com.rp.courseutil.DefaultSubscriber;
import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class PubSubOk {

    public static void main(String[] args) {

        Flux<String> publishOn = Flux.range(1, 20)
                .map(i -> i + 27)
                .map(i -> String.format("http://localhost/api/%d", i))
                .publishOn(Schedulers.boundedElastic());

        publishOn.subscribeOn(Schedulers.parallel())
                .doOnNext(s -> System.out.println("Thread -- [" + Thread.currentThread()
                        .getName() + "] " + "this is it: " + s))
                .publishOn(Schedulers.boundedElastic())
                .map(i -> i + "/detailer/now")
                .map(i -> String.format("{command: \"%s\"}", i))
                .doOnNext(s -> System.out.println("Thread -- [" + Thread.currentThread()
                        .getName() + "] " + "this is it: " + s))
                .subscribe(new DefaultSubscriber("Sub vs Pub"));

        Util.sleepSeconds(5);

    }
}
