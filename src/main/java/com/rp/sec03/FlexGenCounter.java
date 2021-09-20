package com.rp.sec03;

import com.rp.courseutil.DefaultSubscriber;
import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class FlexGenCounter {

    public static void main(String[] args) {

        Flux.generate(() -> 0, (s, synchronousSink) -> {

                String name = Util.faker()
                                  .country()
                                  .name();

                synchronousSink.next(name);

                if (s == 100 || name.equalsIgnoreCase("canada")) {
                    synchronousSink.complete();
                }

                return s + 1;
            })
            .take(40)
            .subscribe(new DefaultSubscriber("state in flow"));

    }
}
