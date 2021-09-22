package com.rp.sec03.assignment;

import com.rp.courseutil.DefaultSubscriber;

public class FileReaderService {

    public static void main(String[] args) {

        FileLinesProducer.fileLinesReader("the_file.txt")
                .subscribe(new DefaultSubscriber("per_line_subscriber"));


    }

}
