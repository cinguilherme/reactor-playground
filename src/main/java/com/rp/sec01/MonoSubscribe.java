package com.rp.sec01;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

public class MonoSubscribe {

    public static void main(String[] args) {

        //publisher
        Mono<Integer> mono = Mono.just("ball")
                                 .map(String::toUpperCase)
                                 .map(it -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return it + " ";
                }).map(it -> it.concat("Another"))
                                 .map(String::length);

        // 1
        //mono.subscribe();

        // 2
        mono.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete());




    }
}
