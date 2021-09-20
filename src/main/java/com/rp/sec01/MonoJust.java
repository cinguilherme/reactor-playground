package com.rp.sec01;

import reactor.core.publisher.Mono;

public class MonoJust {

    public static void main(String[] args) {

        // publisher
        Mono<Integer> just = Mono.just(2);

        System.out.println(just);

        just.subscribe(System.out::println);

    }
}
