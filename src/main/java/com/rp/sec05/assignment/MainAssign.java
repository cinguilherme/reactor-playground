package com.rp.sec05.assignment;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class MainAssign {

    public static void main(String[] args) {

        Flux<Order> orderFlux = OrderService.ordersIncoming();
        
        orderFlux.subscribe(new InventoryService());
        orderFlux.subscribe(new RevenueService());


        Util.sleepSeconds(20);
    }
}
