package com.rp.sec08.helper;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Qatar {

    public static Flux<String> getFlights() {
        return Flux.range(1, 30)
                   .map(n -> "Qatar: Flight number: " + n)
                   .delayElements(Duration.ofMillis(50))
                   .map(fli -> fli + "Price: " + Util.faker()
                                                     .number()
                                                     .randomDouble(2, 100, 9000));
    }

}
