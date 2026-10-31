package fr.demo.taskmanager.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

public class TaskScheduleTest {

    private static final ZoneId ZONE = ZoneId.of("Europe/Paris");
    private static final ZonedDateTime START_DATE = ZonedDateTime.of(2026, 10, 29, 10, 5, 0, 0, ZONE);
    private static final ZonedDateTime END_DATE = START_DATE.plusHours(1).withMinute(35);
    private static final ZonedDateTime PAST_START_DATE = ZonedDateTime.of(2020, 03, 16, 13, 25, 0, 0, ZONE);
    private static final ZonedDateTime PAST_END_DATE = PAST_START_DATE.plusHours(1);


    // ------------ CREATION ------------ //

    @Test
    void shouldBeEqualWhenValuesAreIdentical() {
        // Act
        TaskSchedule taskSchedule1 = new TaskSchedule(START_DATE, END_DATE);
        TaskSchedule taskSchedule2 = new TaskSchedule(START_DATE, END_DATE);

        // Assert
        assertEquals(taskSchedule1, taskSchedule2);
    }

    @Test
    void shouldNotBeEqualWhenValuesAreDifferent() {
        // Act
        TaskSchedule taskSchedule1 = new TaskSchedule(PAST_START_DATE, PAST_END_DATE);
        TaskSchedule taskSchedule2 = new TaskSchedule(START_DATE, END_DATE);

        // Assert
        assertNotEquals(taskSchedule1, taskSchedule2);
    }

    @Test
    void shouldAcceptNullStartDate() {
        // Act
        TaskSchedule taskSchedule = new TaskSchedule(null, END_DATE);

        // Assert
        assertNull(taskSchedule.startDate());
    }

    @Test
    void shouldAcceptNullEndDate() {
        // Act
        TaskSchedule taskSchedule = new TaskSchedule(START_DATE, null);

        // Assert
        assertNull(taskSchedule.endDate());
    }

    @Test
    void shouldAcceptNullStartDateAndEndDate() {
        // Act
        TaskSchedule taskSchedule = new TaskSchedule(null, null);

        // Assert
        assertNull(taskSchedule.startDate());
        assertNull(taskSchedule.endDate());
    }

    @Test
    void shouldHaveSameHashCodeWhenValuesAreIdentical() {
        // Act
        TaskSchedule taskSchedule1 = new TaskSchedule(START_DATE, END_DATE);
        TaskSchedule taskSchedule2 = new TaskSchedule(START_DATE, END_DATE);

        // Assert
        assertEquals(taskSchedule1.hashCode(), taskSchedule2.hashCode());
    }

    @Test
    void shouldPreserveValueAfterCreation() {
        // Act
        TaskSchedule taskSchedule = new TaskSchedule(START_DATE, END_DATE);

        // Assert
        assertEquals(START_DATE, taskSchedule.startDate());
        assertEquals(END_DATE, taskSchedule.endDate());
    }

    @Test
    void shouldAcceptStartDateEqualToEndDate() {
        // Act
        TaskSchedule taskSchedule = new TaskSchedule(START_DATE, START_DATE);

        // Assert
        assertEquals(taskSchedule.startDate(), taskSchedule.endDate());
    }

    @Test
    void shouldAcceptPastStartDate() {
        // Act
        TaskSchedule taskSchedule = new TaskSchedule(PAST_START_DATE, null);

        // Assert
        assertEquals(PAST_START_DATE, taskSchedule.startDate());
        assertNull(taskSchedule.endDate());
    }

    @Test
    void shouldAcceptPastEndDate() {
        // Act
        TaskSchedule taskSchedule = new TaskSchedule(null, PAST_END_DATE);

        // Assert
        assertNull(taskSchedule.startDate());
        assertEquals(PAST_END_DATE, taskSchedule.endDate());
    }

    @Test
    void shouldRejectStartDateWithMinutesNotIn5MinuteInterval() {
        // Arrange
        ZonedDateTime startDate = START_DATE.withMinute(12);

        // Act + Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> new TaskSchedule(startDate, null)
        );
    }

    @Test
    void shouldRejectEndDateWithMinutesNotIn5MinuteInterval() {
        // Arrange
        ZonedDateTime endDate = END_DATE.withMinute(37);

        // Act + Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> new TaskSchedule(null, endDate)
        );
    }

    @Test
    void shouldRejectStartDateWithNonZeroSeconds() {
        // Arrange
        ZonedDateTime startDate = START_DATE.withSecond(14);

        // Act + Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> new TaskSchedule(startDate, null)
        );
    }

    @Test
    void shouldRejectEndDateWithNonZeroSeconds() {
        // Arrange
        ZonedDateTime endDate = END_DATE.withSecond(45);

        // Act + Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> new TaskSchedule(null, endDate)
        );
    }

    @Test
    void shouldRejectStartDateWithNonZeroNanoseconds() {
        // Arrange
        ZonedDateTime startDate = START_DATE.withNano(23);

        // Act + Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> new TaskSchedule(startDate, null)
        );
    }

    @Test
    void shouldRejectEndDateWithNonZeroNanoseconds() {
        // Arrange
        ZonedDateTime endDate = END_DATE.withNano(35);

        // Act + Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> new TaskSchedule(null, endDate)
        );
    }

    @Test
    void shouldRejectStartDateAfterEndDate() {
        // Act + Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> new TaskSchedule(END_DATE, START_DATE)
        );
    }


    // ------------ UPDATE ------------ //

    @Test
    void shouldPreserveValueAfterUpdate() {
        // Arrange
        ZonedDateTime newStartDate = START_DATE.plusMinutes(5);
        ZonedDateTime newEndDate = END_DATE.plusDays(4);

        // Act
        TaskSchedule taskSchedule1 = new TaskSchedule(START_DATE, END_DATE);
        taskSchedule1.withStartDate(newStartDate);
        taskSchedule1.withEndDate(newEndDate);

        // Assert
        assertEquals(START_DATE, taskSchedule1.startDate());
        assertEquals(END_DATE, taskSchedule1.endDate());
    }

    @Test
    void shouldRejectStartDateChangeToNullValue() {
        // Act
        TaskSchedule taskSchedule = new TaskSchedule(START_DATE, END_DATE);

        // Assert
        assertThrows(
            NullPointerException.class,
            () -> taskSchedule.withStartDate(null)
        );
    }

    @Test
    void shouldRejectEndDateChangeToNullValue() {
        // Act
        TaskSchedule taskSchedule = new TaskSchedule(START_DATE, END_DATE);

        // Assert
        assertThrows(
            NullPointerException.class,
            () -> taskSchedule.withEndDate(null)
        );
    }

    @Test
    void shouldChangeStartDateToExpectedValueWithoutChangingEndDate() {
        // Arrange
        ZonedDateTime newStartDate = START_DATE.minusDays(10);
        
        // Act
        TaskSchedule taskSchedule = new TaskSchedule(START_DATE, END_DATE);
        taskSchedule = taskSchedule.withStartDate(newStartDate);

        // Assert
        assertEquals(newStartDate, taskSchedule.startDate());
        assertEquals(END_DATE, taskSchedule.endDate());
    }

    @Test
    void shouldChangeEndDateToExpectedValueWithoutChangingStartDate() {
        // Arrange
        ZonedDateTime newEndDate = END_DATE.plusDays(10);

        // Act
        TaskSchedule taskSchedule = new TaskSchedule(START_DATE, END_DATE);
        taskSchedule = taskSchedule.withEndDate(newEndDate);

        // Assert
        assertEquals(START_DATE, taskSchedule.startDate());
        assertEquals(newEndDate, taskSchedule.endDate());
    }

    @Test
    void shouldAcceptPastStartDateWhenUpdating() {
        // Act
        TaskSchedule taskSchedule = new TaskSchedule(null, null);
        taskSchedule = taskSchedule.withStartDate(PAST_START_DATE);

        // Assert
        assertEquals(PAST_START_DATE, taskSchedule.startDate());
    }

    @Test
    void shouldAcceptPastEndDateWhenUpdating() {
        // Act
        TaskSchedule taskSchedule = new TaskSchedule(null, null);
        taskSchedule = taskSchedule.withEndDate(PAST_END_DATE);

        // Assert
        assertEquals(PAST_END_DATE, taskSchedule.endDate());
    }

    @Test
    void shouldChangeStartDateToSameValueAsEndDate() {
        // Act
        TaskSchedule taskSchedule = new TaskSchedule(START_DATE, END_DATE);
        taskSchedule = taskSchedule.withStartDate(END_DATE);

        // Assert
        assertEquals(taskSchedule.startDate(), taskSchedule.endDate());
    }

    @Test
    void shouldChangeEndDateToSameValueAsStartDate() {
        // Act
        TaskSchedule taskSchedule = new TaskSchedule(START_DATE, END_DATE);
        taskSchedule = taskSchedule.withEndDate(START_DATE);

        // Assert
        assertEquals(taskSchedule.startDate(), taskSchedule.endDate());
    }

    @Test
    void shouldRejectStartDateChangeToValueAfterEndDate() {
        // Act
        TaskSchedule taskSchedule = new TaskSchedule(PAST_START_DATE, PAST_END_DATE);

        // Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> taskSchedule.withStartDate(START_DATE)
        );
    }

    @Test
    void shouldRejectEndDateChangeToValueBeforeStartDate() {
        // Act
        TaskSchedule taskSchedule = new TaskSchedule(START_DATE, END_DATE);

        // Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> taskSchedule.withEndDate(PAST_END_DATE)
        );
    }

    @Test
    void shouldRejectStartDateChangeToValueNotIn5MinuteInterval() {
        // Arrange
        ZonedDateTime newStartDate = START_DATE.withMinute(12);

        // Act
        TaskSchedule taskSchedule = new TaskSchedule(START_DATE, END_DATE);
        
        // Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> taskSchedule.withStartDate(newStartDate)
        );
    }

    @Test
    void shouldRejectEndDateChangeToValueNotIn5MinuteInterval() {
        // Arrange
        ZonedDateTime newEndDate = END_DATE.withMinute(37);

        // Act
        TaskSchedule taskSchedule = new TaskSchedule(START_DATE, END_DATE);
        
        // Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> taskSchedule.withEndDate(newEndDate)
        );
    }

    @Test
    void shouldRejectStartDateChangeWithNonZeroSeconds() {
        // Arrange
        ZonedDateTime newStartDate = START_DATE.withSecond(12);

        // Act
        TaskSchedule taskSchedule = new TaskSchedule(START_DATE, null);

        // Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> taskSchedule.withStartDate(newStartDate)
        );
    }

    @Test
    void shouldRejectEndDateChangeWithNonZeroSeconds() {
        // Arrange
        ZonedDateTime newEndDate = END_DATE.withSecond(45);

        // Act
        TaskSchedule taskSchedule = new TaskSchedule(null, END_DATE);

        // Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> taskSchedule.withEndDate(newEndDate)
        );
    }

    @Test
    void shouldRejectStartDateChangeWithNonZeroNanoseconds() {
        // Arrange
        ZonedDateTime newStartDate = START_DATE.withNano(23);

        // Act
        TaskSchedule taskSchedule = new TaskSchedule(START_DATE, null);

        // Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> taskSchedule.withStartDate(newStartDate)
        );
    }

    @Test
    void shouldRejectEndDateChangeWithNonZeroNanoseconds() {
        // Arrange
        ZonedDateTime newEndDate = END_DATE.withNano(35);

        // Act
        TaskSchedule taskSchedule = new TaskSchedule(null, END_DATE);

        // Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> taskSchedule.withEndDate(newEndDate)
        );
    }

    @Test
    void shouldRemoveStartDateWithoutChangingEndDate() {
        // Act
        TaskSchedule taskSchedule = new TaskSchedule(START_DATE, END_DATE);
        taskSchedule = taskSchedule.removeStartDate();

        // Assert
        assertNull(taskSchedule.startDate());
        assertEquals(END_DATE, taskSchedule.endDate());
    }

    @Test
    void shouldRemoveEndDateWithoutChangingStartDate() {
        // Act
        TaskSchedule taskSchedule = new TaskSchedule(START_DATE, END_DATE);
        taskSchedule = taskSchedule.removeEndDate();

        // Assert
        assertEquals(START_DATE, taskSchedule.startDate());
        assertNull(taskSchedule.endDate());
    }
}
