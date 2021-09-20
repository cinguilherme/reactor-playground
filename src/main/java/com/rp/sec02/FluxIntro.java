package com.rp.sec02;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import static com.rp.courseutil.Util.onComplete;
import static com.rp.courseutil.Util.onError;
import static com.rp.courseutil.Util.onNext;

public class FluxIntro {

    public static void main(String[] args) {

        fluxOfInts().subscribe(onNext(), onError(), onComplete());

        fluxOfData().subscribe(onNext(), onError(), onComplete());

    }

    private static Flux<Integer> fluxOfInts() {
        return Flux.just(1, 2, 3, 4, 5);
    }

    private static Flux<Object> fluxOfData() {
        return Flux.just(1, 2, 3, 4, "a", Util.faker()
                                              .funnyName()
                                              .name());
    }

}
