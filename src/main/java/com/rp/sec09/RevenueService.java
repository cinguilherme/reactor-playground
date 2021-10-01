package com.rp.sec09;

import com.rp.courseutil.DefaultSubscriber;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class RevenueService {

    private static final List<String> importantCategories = List.of(
            "Classic", "Science Fiction", "Fantasy", "Suspense", "Thriller");

    private AtomicReference<Double> revenueTotal;
    AtomicReference<Map<String, Double>> revenueCategoryMap;

    public RevenueService() {
        revenueTotal = new AtomicReference<>(0.0d);
        revenueCategoryMap = new AtomicReference<>(new HashMap<>());
        revenueCategoryMap.get()
                          .put("Fantasy", 0.0);
        revenueCategoryMap.get()
                          .put("Suspense", 0.0);
        revenueCategoryMap.get()
                          .put("Science fiction", 0.0);
        revenueCategoryMap.get()
                          .put("Thriller", 0.0);
        revenueCategoryMap.get()
                          .put("Classic", 0.0);
    }

    public void processStream(Flux<BookOrder> orderFlux) {
        orderFlux.log()
                 .filter(this::relevantCategory)
                 .buffer(5)
                 .log()
                 .doOnNext(listOfRelevants -> {
                     listOfRelevants.stream()
                                    .forEach(bookOrder -> {
                                        try {
                                            updates(bookOrder);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            System.out.println(bookOrder);
                                        }

                                    });
                 })
                 .subscribe(new DefaultSubscriber("Revenue by category"));

    }

    private synchronized void updates(BookOrder bookOrder) {
        System.out.println(1);

        double v = revenueTotal.get() + bookOrder.getPrice();
        String category = bookOrder.getCategory();
        Double curRevenue = revenueCategoryMap.get()
                                              .get(category);
        try {

            revenueTotal.set(v);

            System.out.printf("current revenue for cat %s : %.2f%n", bookOrder.getCategory(),
                              curRevenue);
            revenueCategoryMap.get()
                              .put(bookOrder.getCategory(), bookOrder.getPrice() + curRevenue);
        } catch (Exception e) {

            e.printStackTrace();
        }
        System.out.println(2);
    }

    private boolean relevantCategory(BookOrder bookOrder) {
        return importantCategories.stream()
                                  .anyMatch(c -> c.equalsIgnoreCase(bookOrder.getCategory()));
    }

    public void present() {
        System.out.println(revenueCategoryMap);
        System.out.println(revenueTotal);
    }
}
