package com.rp.sec04;

import com.rp.courseutil.DefaultSubscriber;
import com.rp.courseutil.Util;
import com.rp.sec04.helpers.OrderService;
import com.rp.sec04.helpers.UserService;

public class ConcatMap {

    public static void main(String[] args) {
        UserService.getUsers()
                //mono or flux
                .concatMap(user -> OrderService.getOrders(user.getUserId()))
                .subscribe(new DefaultSubscriber("FlatMap"));


        Util.sleepSeconds(10);
    }
}
