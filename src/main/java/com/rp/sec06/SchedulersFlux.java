package com.rp.sec06;

import com.rp.courseutil.DefaultSubscriber;
import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class SchedulersFlux {

    public static void main(String[] args) {

        getIntegerFluxIntegers().subscribeOn(Schedulers.parallel())
                .subscribe(new DefaultSubscriber("receive from parallel"));

        Util.sleepSeconds(3);

    }

    private static Flux<Integer> getIntegerFluxIntegers() {
        return Flux.range(1, 1000)
                .map(i -> i * 2)
                .delayElements(Duration.ofMillis(100));
    }

}
