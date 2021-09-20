package com.rp.sec03;

import com.rp.courseutil.DefaultSubscriber;
import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class FluxCreateIssueFix {

    public static void main(String[] args) {
        Flux.create(getTillCanada())
            .take(3)
            .subscribe(new DefaultSubscriber("fluxCreateSampler"));
    }

    private static Consumer<FluxSink<Object>> getTillCanada() {
        return fluxSink -> {
            while (true) {
                var name = Util.faker()
                               .country()
                               .name();
                System.out.println("Emitting: " + name);
                fluxSink.next(name);
                if (name.equalsIgnoreCase("canada") || fluxSink.isCancelled()) {
                    fluxSink.complete();
                    break;
                }
            }
        };
    }

}
