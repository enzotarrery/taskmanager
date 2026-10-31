package fr.demo.taskmanager.domain;

import java.util.Objects;
import java.util.UUID;

public record UserId(UUID value) {
    
    public UserId {
        Objects.requireNonNull(value);
    }
}
