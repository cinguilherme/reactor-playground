package com.rp.sec08.assignment;

import com.rp.courseutil.DefaultSubscriber;
import com.rp.courseutil.Util;
import com.rp.sec04.helpers.PurchaseOrder;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class Assignment08 {

    public static void main(String[] args) {

        Map<String, Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>>> map = Map.of("kids",
                                                                                     OrderProcessor.kidsProcessing(),
                                                                                     "automotive",
                                                                                     OrderProcessor.automotiveProcessing());

        Set<String> set = map.keySet();


        OrderService.orderStream()
                .filter(p -> set.contains(p.getCategory()))
                .groupBy(PurchaseOrder::getCategory)
                .flatMap(gf -> map.get(gf.key())
                        .apply(gf))
                .subscribe(new DefaultSubscriber("assignment 8"));

        Util.sleepSeconds(20);
    }

}
