package com.rp.sec04;

import com.rp.courseutil.DefaultSubscriber;
import reactor.core.publisher.Flux;

public class SwitchIfEmpty {

    public static void main(String[] args) {

        getNumber().filter(i -> i > 12)
                .log()
                .switchIfEmpty(fallback())
                .subscribe(new DefaultSubscriber("switchIfEmpty"));

    }

    private static Flux<Integer> fallback() {
        System.out.println("switch to fallback");
        return Flux.range(1, 100);
    }

    public static Flux<Integer> getNumber() {
        return Flux.range(1, 0);
    }
}
