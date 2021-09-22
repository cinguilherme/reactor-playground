package com.rp.sec03;

import com.rp.courseutil.DefaultSubscriber;
import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class FluxPush {

    public static void main(String[] args) {
        Flux.push(getTillCanada())
                .subscribe(new DefaultSubscriber("fluxCreateSampler"));
    }

    private static Consumer<FluxSink<Object>> getTillCanada() {
        return fluxSink -> {
            while (true) {
                var name = Util.faker()
                        .country()
                        .name();
                fluxSink.next(name);
                if (name.equalsIgnoreCase("canada")) {
                    fluxSink.complete();
                    break;
                }
            }
        };
    }
}
