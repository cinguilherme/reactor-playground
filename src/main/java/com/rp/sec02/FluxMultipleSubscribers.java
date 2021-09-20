package com.rp.sec02;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxMultipleSubscribers {

    public static void main(String[] args) {

        Flux<Integer> integerFlux = Flux.just(1, 2, 3, 4, 5, 6, 7, 8);

        Flux<Integer> evenFlux = integerFlux.filter(i -> i % 2 == 0);

        Mono<Integer> summedUpMono = integerFlux.reduce((acc, curr) -> acc += curr);

        integerFlux.subscribe(it -> System.out.println("Sub 1: " + it));
        evenFlux.subscribe(it -> System.out.println("Sub 2: " + it));

        summedUpMono.subscribe(it -> System.out.println("summed up value = " + it));

    }


}
