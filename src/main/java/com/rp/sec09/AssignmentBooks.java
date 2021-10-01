package com.rp.sec09;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class AssignmentBooks {

    public static void main(String[] args) {

        Flux<BookOrder> bookOrderFlux = bookOrdersStream();

        RevenueService revenueService = new RevenueService();

        revenueService.processStream(bookOrderFlux);

        Util.sleepSeconds(20);

        revenueService.present();

    }

    private static Flux<BookOrder> bookOrdersStream() {
        return Flux.interval(Duration.ofMillis(100))
                   .map(i -> randomNewBookOrder())
                   .log();
    }

    private static BookOrder randomNewBookOrder() {
        return new BookOrder(Util.faker()
                                 .book()
                                 .title(), Util.faker()
                                               .book()
                                               .genre(), Util.faker()
                                                             .number()
                                                             .randomDouble(2, 10, 200));
    }
}
