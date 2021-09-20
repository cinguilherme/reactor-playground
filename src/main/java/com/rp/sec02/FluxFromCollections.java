package com.rp.sec02;

import reactor.core.publisher.Flux;

import java.util.List;

import static com.rp.courseutil.Util.onComplete;
import static com.rp.courseutil.Util.onError;
import static com.rp.courseutil.Util.onNext;

public class FluxFromCollections {

    public static void main(String[] args) {

        List<Integer> integers = List.of(1, 2, 3, 4, 5);

        Integer[] arrInt = {1, 2, 3, 4};

        Flux.fromStream(integers::stream)
            .subscribe(onNext(), onError(), onComplete());
        Flux.fromStream(integers::stream)
            .subscribe(onNext(), onError(), onComplete());

        Flux.fromStream(integers.stream())
            .subscribe(it -> System.out.println("streamed: " + it));

        Flux.fromArray(arrInt)
            .subscribe(it -> System.out.println("it is " + it));

        Flux.fromIterable(integers)
            .subscribe(it -> System.out.println("it is " + it));

    }
}
