package com.rp.sec01;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

public class Lec04 {

    public static void main(String[] args) {


        userRepository(3)
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        Util.onComplete()
                          );

    }

    public static Mono<String> userRepository(Integer id) {
        // found
        if(id == 1) {
            return Mono.just(Util.faker().name().firstName());
        }
        if(id ==2) {
            return Mono.error(new RuntimeException("this id is poison"));
        }
        // not found
        return Mono.empty();
    }
}
