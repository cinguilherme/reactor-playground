package com.rp.sec04.helpers;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {

    private static Map<Integer, List<PurchaseOrder>> db = new HashMap<>();

    static {

        List<PurchaseOrder> one = List.of(new PurchaseOrder(1),
                                          new PurchaseOrder(1),
                                          new PurchaseOrder(1),
                                          new PurchaseOrder(1));
        List<PurchaseOrder> two = List.of(new PurchaseOrder(2),
                                          new PurchaseOrder(2),
                                          new PurchaseOrder(2),
                                          new PurchaseOrder(2));

        db.put(1, one);
        db.put(2, two);
    }

    public static Flux<PurchaseOrder> getOrders(int userId) {
        return Flux.create((FluxSink<PurchaseOrder> purchaseOrderFluxSink) -> {
                    db.get(userId)
                            .forEach(purchaseOrderFluxSink::next);
                    purchaseOrderFluxSink.complete();
                })
                .delayElements(Duration.ofMillis(500));
    }
}
