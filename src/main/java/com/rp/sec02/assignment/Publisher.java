package com.rp.sec02.assignment;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Publisher {

    public static Flux<Integer> getPriceUpdate() {
        AtomicInteger ref = new AtomicInteger(100);
        return Flux.interval(Duration.ofMillis(100))
                   .map(ig -> Util.faker()
                                  .random()
                                  .nextInt(-5, 5))
                   .map(n -> ref.getAndAccumulate(n, Integer::sum));
    }

}
