package com.rp.sec08.helper;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGen {

    List<String> cache = new ArrayList<>();

    public Flux<String> generateNames() {

        return Flux.generate(stringSynchronousSink -> {
                       String name = Util.faker()
                                         .name()
                                         .firstName();
                       Util.sleepSeconds(1);
                       System.out.println("generated fresh name: " + name);
                       cache.add(name);
                       stringSynchronousSink.next(name);
                   })
                   .cast(String.class)
                   .startWith(cache);
    }

}
