package com.rp.sec03.assignment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class FileLinesProducer {

    public static final Path PATH = Paths.get("src/main/resources/assignment/sec03");

    private static BufferedReader getFileBuffer(Path path) throws IOException {
        return Files.newBufferedReader(path);
    }

    private static Callable<BufferedReader> buff(Path path) {
        return () -> getFileBuffer(path);
    }

    private static BiFunction<BufferedReader, SynchronousSink<String>, BufferedReader> process() {
        return (br, sink) -> {
            try {
                final String line = br.readLine();
                if (line == null || line.equalsIgnoreCase("End")) {
                    sink.complete();
                } else {
                    sink.next(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
                sink.error(e);
            }
            return br;
        };
    }

    public static Flux<String> fileLinesReader(String fileName) {

        Path resolve = PATH.resolve(fileName);

        return Flux.generate(buff(resolve), process(), close());

    }

    private static Consumer<BufferedReader> close() {
        return br -> {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

}
