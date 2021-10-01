package com.rp.sec08.helper;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class American {

    public static Flux<String> getFlights() {
        return Flux.range(1, 10)
                   .map(n -> "American: Flight number: " + n)
                   .delayElements(Duration.ofMillis(100))
                   .map(fli -> fli + "Price: " + Util.faker()
                                                     .number()
                                                     .randomDouble(2, 100, 9000));
    }

}
