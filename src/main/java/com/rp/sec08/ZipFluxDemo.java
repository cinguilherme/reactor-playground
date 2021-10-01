package com.rp.sec08;

import com.rp.courseutil.DefaultSubscriber;
import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

import static java.time.LocalTime.now;

public class ZipFluxDemo {

    public static void main(String[] args) {

        Flux<Integer> intes = Flux.range(1, 10)
                                  .delayElements(Duration.ofMillis(10))
                                  .map(i -> i * 2)
                                  .filter(i -> i > 5);

        Flux<String> ses = Flux.range(1, 10)
                               .delayElements(Duration.ofMillis(300))
                               .log()
                               .map(i -> String.format("Generated when: " + now()));

        Flux<Double> pis = Flux.range(1, 10)
                               .delayElements(Duration.ofMillis(50))
                               .map(i -> Double.valueOf(i * 100));


        Flux.zip(intes, ses, pis)
            .doOnNext(tuple3 -> {
                Integer t1 = tuple3.getT1();
                String t2 = tuple3.getT2();
                Double t3 = tuple3.getT3();
                String res = String.format("%s : for the int %d with cost of: ", t2, t1) + t3;
                System.out.println(res);
            })
            .log()
            .take(3)
            .subscribe(new DefaultSubscriber("Zipping"));

        Util.sleepSeconds(5);
    }

}
