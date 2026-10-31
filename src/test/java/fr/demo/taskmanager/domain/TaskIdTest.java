package fr.demo.taskmanager.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class TaskIdTest {
    
    @Test
    void shouldBeEqualWhenValuesAreIdentical() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        TaskId taskId1 = new TaskId(uuid);
        TaskId taskId2 = new TaskId(uuid);

        // Act + Assert
        assertEquals(taskId1, taskId2);
    }

    @Test
    void shouldNotBeEqualWhenValuesAreDifferent() {
        // Arrange
        TaskId taskId1 = new TaskId(UUID.randomUUID());
        TaskId taskId2 = new TaskId(UUID.randomUUID());

        // Act + Assert
        assertNotEquals(taskId1, taskId2);
    }

    @Test
    void shouldRejectNullValue() {
        assertThrows(
            NullPointerException.class,
            () -> new TaskId(null)
        );
    }

    @Test
    void shouldHaveSameHashCodeWhenValuesAreIdentical() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        TaskId taskId1 = new TaskId(uuid);
        TaskId taskId2 = new TaskId(uuid);

        // Act + Assert
        assertEquals(taskId1.hashCode(), taskId2.hashCode());
    }

    @Test
    void shouldPreserveValueAfterCreation() {
        UUID uuid = UUID.randomUUID();
        TaskId id = new TaskId(uuid);

        assertEquals(uuid, id.value());
    }
}
