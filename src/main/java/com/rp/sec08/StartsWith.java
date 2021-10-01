package com.rp.sec08;

import com.rp.courseutil.DefaultSubscriber;
import com.rp.sec08.helper.NameGen;

public class StartsWith {

    public static void main(String[] args) {
        NameGen gen = new NameGen();
        gen.generateNames()
           .take(2)
           .subscribe(new DefaultSubscriber("sam"));

        gen.generateNames()
           .filter(n -> n.startsWith("G"))
           .take(1)
           .subscribe(new DefaultSubscriber("picky"));

        gen.generateNames()
           .take(20)
           .subscribe(new DefaultSubscriber("tim"));

    }

}
