package com.devfactory.codecache.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import lombok.SneakyThrows;

public class ExecutionHelper {

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public void runParallel(int concurrency, int times, Runnable runnable) {
        IntStream.rangeClosed(1, concurrency).parallel().forEach((index) -> runAsync(times, runnable));
    }

    @SneakyThrows
    private void runAsync(int times, Runnable runnable) {
        executorService.submit(() -> runConsecutiveTimes(times, runnable)).get();
    }

    private void runConsecutiveTimes(int times, Runnable runnable) {
        IntStream.rangeClosed(1, times).forEach((index) -> runnable.run());
    }
}
