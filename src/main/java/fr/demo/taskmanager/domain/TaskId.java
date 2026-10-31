package fr.demo.taskmanager.domain;

import java.util.Objects;
import java.util.UUID;

public record TaskId(UUID value) {

    public TaskId {
        Objects.requireNonNull(value, "Task ID cannot be null.");
    }
}
