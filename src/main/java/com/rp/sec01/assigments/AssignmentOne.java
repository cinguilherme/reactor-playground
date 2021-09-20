package com.rp.sec01.assigments;

import com.rp.courseutil.Util;

public class AssignmentOne {

    public static void main(String[] args) {

        FileService.create("sampleTwoX.txt", "content")
                   .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

        FileService.read("sampleTwoX.txt")
                   .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

        FileService.delete("sampleTwoX.txt")
                   .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

        FileService.read("sample2.txt")
                   .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

        FileService.create("sample3.txt", "wont be delete automatically")
                   .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

    }
}
