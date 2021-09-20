package com.rp.sec01;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

public class Lec05FromSupplier {

    public static void main(String[] args) {

        //Mono<String> just = Mono.just(getName());

        Mono<String> stringMono = Mono.fromSupplier(Lec05FromSupplier::getName);

        stringMono.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete());


    }

    private static String getName() {
        System.out.println("generating name...");
        return Util.faker().name().fullName();
    }
}
