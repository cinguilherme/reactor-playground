package com.rp.sec01;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

public class Lec08MonoFromRunnable {

    public static void main(String[] args) {


        Mono.fromRunnable(timeConsumingOp())
            .subscribe(Util.onNext(),
                       Util.onError(),
                       () -> {
                           System.out.println("process is done, sending emails");
                       });

    }

    private static Runnable timeConsumingOp() {
        return () -> {
            Util.sleepSeconds(2);
            System.out.println("OP completed");
        };
    }


}
