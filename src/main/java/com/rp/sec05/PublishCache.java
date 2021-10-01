package com.rp.sec05;

import com.rp.courseutil.DefaultSubscriber;
import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class PublishCache {

    public static void main(String[] args) {

        Flux<String> movies = Flux.fromStream(getMovies())
                .delaySubscription(Duration.ofMillis(200))
                .delayElements(Duration.ofSeconds(1))
                //                .publish()
                //                .autoConnect(0);
                .cache(5);
        movies.subscribe(new DefaultSubscriber("rosa"));

        Util.sleepSeconds(6);

        movies.subscribe(new DefaultSubscriber("mike"));

        Util.sleepSeconds(10);
    }


    private static Stream<String> getMovies() {
        return Stream.of(title(),
                         title(),
                         title(),
                         title(),
                         title(),
                         title(),
                         title(),
                         title(),
                         title(),
                         title(),
                         title(),
                         title());
    }

    private static String title() {
        return Util.faker()
                .book()
                .title();
    }
}
