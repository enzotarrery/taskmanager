package fr.demo.taskmanager.domain;

import java.time.ZonedDateTime;
import java.util.Objects;

public record TaskSchedule(ZonedDateTime startDate, ZonedDateTime endDate) {

    public TaskSchedule {
        this.ensureStartDateIsBeforeEndDate(startDate, endDate);

        this.ensureDateIsValid(startDate);
        this.ensureDateIsValid(endDate);
    }

    public TaskSchedule withStartDate(ZonedDateTime startDate) {
        Objects.requireNonNull(startDate, "Task start date cannot be null.");

        return new TaskSchedule(startDate, this.endDate);
    }

    public TaskSchedule withEndDate(ZonedDateTime endDate) {
        Objects.requireNonNull(endDate, "Task end date cannot be null.");

        return new TaskSchedule(this.startDate, endDate);
    }

    public TaskSchedule removeStartDate() {
        return new TaskSchedule(null, this.endDate);
    }

    public TaskSchedule removeEndDate() {
        return new TaskSchedule(this.startDate, null);
    }

    private void ensureStartDateIsBeforeEndDate(ZonedDateTime startDate, ZonedDateTime endDate) {
        if (startDate == null || endDate == null) {
            return;
        }

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(
                "Task start date must not be after end date."
            );
        }
    }

    private void ensureValidMinuteInterval(ZonedDateTime date) {
        if (date != null && date.getMinute() % 5 != 0) {
            throw new IllegalArgumentException(
                "Minutes must be in a 5-minute interval."
            );
        }
    }

    private void ensureValidSecondValue(ZonedDateTime date) {
        if (date != null && date.getSecond() != 0) {
            throw new IllegalArgumentException(
                "Seconds must be equal to 0."
            );
        }
    }

    private void ensureValidNanoValue(ZonedDateTime date) {
        if (date != null && date.getNano() != 0) {
            throw new IllegalArgumentException(
                "Nanoseconds must be equal to 0."
            );
        }
    }

    private void ensureDateIsValid(ZonedDateTime date) {
        this.ensureValidMinuteInterval(date);
        this.ensureValidSecondValue(date);
        this.ensureValidNanoValue(date);
    }
}
