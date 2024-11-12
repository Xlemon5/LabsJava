package org.example;

import java.time.Duration;
import java.time.Instant;

public interface PerformanceResult {

    long AddPerformanceTime();
    long GetPerformanceTime();
    long DeletePerformanceTime();

    default long measureTime(Runnable original){
        Instant startTime, endTime;
        startTime = Instant.now();
        original.run();
        endTime = Instant.now();
        return Duration.between(startTime, endTime).toMillis();
    }

}
