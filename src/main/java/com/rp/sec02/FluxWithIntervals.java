package com.rp.sec02;

import com.rp.courseutil.Util;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;

import static com.rp.courseutil.Util.onNext;

public class FluxWithIntervals {

    public static void main(String[] args) {


        Disposable subscribe = Flux.interval(Duration.ofMillis(200))
                                   .map(n -> Util.faker()
                                                 .name()
                                                 .fullName())
                                   .log()
                                   .subscribe(onNext());

        Util.sleepSeconds(3);

        subscribe.dispose();
        
    }

}
