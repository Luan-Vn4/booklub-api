package br.upe.booklubapi.utils;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

public abstract class UnitUtils {

    public static TemporalUnit temporalUnitFromTimeUnit(TimeUnit timeUnit) {
        return switch (timeUnit) {
            case DAYS -> ChronoUnit.DAYS;
            case HOURS -> ChronoUnit.HOURS;
            case MINUTES -> ChronoUnit.MINUTES;
            case SECONDS -> ChronoUnit.SECONDS;
            case MILLISECONDS -> ChronoUnit.MILLIS;
            case MICROSECONDS -> ChronoUnit.MICROS;
            case NANOSECONDS -> ChronoUnit.NANOS;
        };
    }

    public static Duration getDuration(long amount, TimeUnit unit) {
        return Duration.of(amount, temporalUnitFromTimeUnit(unit));
    }

}
