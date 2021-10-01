package com.rp.sec08;

import com.rp.courseutil.DefaultSubscriber;
import com.rp.courseutil.Util;
import com.rp.sec08.helper.American;
import com.rp.sec08.helper.Emirates;
import com.rp.sec08.helper.Qatar;
import reactor.core.publisher.Flux;

public class MergeFluxes {

    public static void main(String[] args) {

        Flux.merge(American.getFlights(), Emirates.getFlights(), Qatar.getFlights())
            .subscribe(new DefaultSubscriber("Flight Checker"));

        Util.sleepSeconds(10);
    }
}
