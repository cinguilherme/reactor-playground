package com.rp.sec05.assignment;


// emits purchase orders periodically

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;

public class OrderService {

    public static Flux<Order> ordersIncoming() {
        return Flux.range(1, 50)
                .map(x -> newRandomOrder())
                .delayElements(Duration.ofMillis(300));
    }

    private static Order newRandomOrder() {
        var price = Util.faker()
                .commerce()
                .price(1.0, 2000.0);
        var uuid = UUID.randomUUID();
        var details = Util.faker()
                .commerce()
                .productName();

        return new Order(uuid, details, BigDecimal.valueOf(Double.parseDouble(price)));
    }


}
