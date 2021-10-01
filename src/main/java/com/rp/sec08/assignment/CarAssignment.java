package com.rp.sec08.assignment;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

public class CarAssignment {

    public static void main(String[] args) {

        int months = 18;
        AtomicReference<Double> carValue = new AtomicReference<>(10000.00);

        //flux of dedreciation
        Flux<Integer> fluxDepreciation = getFluxDepreciation();

        //flux of demand multiplier
        Flux<Double> demandFlux = getDemandFlux();

        Flux.merge(fluxDepreciation, demandFlux)
            .log()
            .subscribe(number -> {
                updateCarValue(carValue, number);
            });

        Util.sleepSeconds(months);

        System.out.println("Current value after " + months + " is currently: $" + carValue.get());

    }

    private static void updateCarValue(AtomicReference<Double> carValueInit, Number number) {
        if (number.equals(100)) {
            double v = carValueInit.get() - number.intValue();
            carValueInit.set(v);
        } else {
            double v = carValueInit.get() * number.doubleValue();
            carValueInit.set(v);
        }
    }

    private static Flux<Double> getDemandFlux() {
        return Flux.interval(Duration.ofSeconds(3))
                   .map(x -> {
                       int r = Util.faker()
                                   .number()
                                   .numberBetween(8, 12);

                       return (Double.valueOf(r)) / 10d;
                   })
                   .cast(Double.class)
                   .delayElements(Duration.ofSeconds(3));
    }

    private static Flux<Integer> getFluxDepreciation() {
        return Flux.interval(Duration.ofSeconds(1))
                   .map(x -> 100)
                   .cast(Integer.class);
    }

}
