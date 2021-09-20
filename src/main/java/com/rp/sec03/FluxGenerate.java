package com.rp.sec03;

import com.rp.courseutil.DefaultSubscriber;
import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.util.function.Consumer;

public class FluxGenerate {

    public static void main(String[] args) {

        Flux.generate(getCanada())
            .subscribe(new DefaultSubscriber("generate subscriber"));

    }

    private static Consumer<SynchronousSink<Object>> getCanada() {
        return synchronousSink -> {
            String name = Util.faker()
                              .country()
                              .name();
            synchronousSink.next(name);
            if (name.equalsIgnoreCase("canada")) {
                synchronousSink.complete();
            }
        };
    }
}
