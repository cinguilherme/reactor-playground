package com.rp.sec02;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import static com.rp.courseutil.Util.faker;

public class FluxRange {

    public static void main(String[] args) {

        Flux<Integer> cacheFlux = Flux.range(1, 10000)
                                      .map(i -> i * i)
                                      .filter(i -> i > 100000)
                                      .map(i -> i / 3)
                                      .cache();


        //cacheFlux.subscribe(it -> System.out.print("," + it));

        //cacheFlux.subscribe(it -> System.out.print("," + it));

        Flux.range(0, 10)
            .log()
            .map(i -> faker().name()
                             .fullName())
            .log()
            .subscribe(Util.onNext());


    }
}
