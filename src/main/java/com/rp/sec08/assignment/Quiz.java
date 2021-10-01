package com.rp.sec08.assignment;

import com.rp.courseutil.DefaultSubscriber;
import reactor.core.publisher.Flux;

public class Quiz {

    public static void main(String[] args) {

        Flux<String> str = Flux.just("a", "b", "c");

        str.startWith(str)
           .subscribe(new DefaultSubscriber());
    }
}
