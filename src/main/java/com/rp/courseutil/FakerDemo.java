package com.rp.courseutil;

import com.github.javafaker.Faker;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FakerDemo {

    public static void main(String[] args) {

        List<String> names = IntStream.range(0, 100)
                                      .mapToObj(n -> Faker.instance()
                                                          .name()
                                                          .fullName())
                                      .collect(Collectors.toList());

        names.stream()
             .forEach(System.out::println);

        Flux.range(1, 10)
            .subscribeWith(new DefaultSubscriber("Magick"));

    }
}
