package com.rp.sec02;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.rp.courseutil.Util.onComplete;
import static com.rp.courseutil.Util.onError;
import static com.rp.courseutil.Util.onNext;

public class FluxFromMono {

    public static void main(String[] args) {

        Flux.from(Mono.just("another mono"))
            .subscribe(onNext());


        Flux.range(0, 10)
            .map(i -> i * i)
            .filter(i -> i > 5)
            .next()
            .subscribe(onNext(), onError(), onComplete());

    }
}
