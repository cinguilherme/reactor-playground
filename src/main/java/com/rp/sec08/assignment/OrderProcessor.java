package com.rp.sec08.assignment;

import com.rp.sec04.helpers.PurchaseOrder;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class OrderProcessor {

    public static Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>> automotiveProcessing() {
        return purchaseOrderFlux -> purchaseOrderFlux.doOnNext(p -> p.setPrice(1.1 * p.getPrice()))
                .doOnNext(p -> p.setItem("{{ " + p.getItem() + "}}"));
    }

    public static Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>> kidsProcessing() {
        return purchaseOrderFlux -> purchaseOrderFlux.doOnNext(p -> p.setPrice(0.5 * p.getPrice()))
                .flatMap(p -> Flux.just(getFreeKidsOrder(), p))
                .doOnNext(p -> p.setItem("{{KID: " + p.getItem() + "}}"));
    }

    public static PurchaseOrder getFreeKidsOrder() {
        PurchaseOrder order = new PurchaseOrder(1);
        order.setItem("Free: " + order.getItem());
        order.setPrice(0.0);
        return order;
    }

}
