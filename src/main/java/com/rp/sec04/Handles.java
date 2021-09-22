package com.rp.sec04;

import com.rp.courseutil.DefaultSubscriber;
import reactor.core.publisher.Flux;

public class Handles {

    public static void main(String[] args) {


        Flux.range(1, 20)
                .handle((integer, synchronousSink) -> {
                    if (integer == 7) {
                        synchronousSink.next(integer);
                        synchronousSink.complete();
                    }

                    if (integer % 2 == 0) {
                        synchronousSink.next(integer);
                    } else {
                        synchronousSink.next(integer + 1);
                    }
                })
                .take(5)
                .subscribe(new DefaultSubscriber("handler"));

    }
}
