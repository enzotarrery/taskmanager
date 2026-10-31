package fr.demo.taskmanager.domain;

import java.util.Objects;

public class Task {
    
    private final TaskId id;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private UserId assigneeId;
    private TaskSchedule schedule;
    private boolean isArchived;
    private boolean isDeleted;

    public Task(
        TaskId id,
        String title
    ) {
        Objects.requireNonNull(id, "Task id cannot be null.");
        Objects.requireNonNull(title, "Task title cannot be null.");

        if (title.isBlank()) {
            throw new IllegalArgumentException(
                "Task title cannot be blank."
            );
        }

        this.id = id;
        this.title = title;
        this.description = "";
        this.status = TaskStatus.UNDEFINED;
        this.priority = TaskPriority.UNDEFINED;
        this.schedule = new TaskSchedule(null, null);
        this.isArchived = false;
        this.isDeleted = false;
    }

    public void changeTitle(String newTitle) {
        this.ensureCanBeUpdated();

        Objects.requireNonNull(newTitle, "Task title cannot be null.");

        if (newTitle.isBlank()) {
            throw new IllegalArgumentException(
                "Task title cannot be blank."
            );
        }

        this.title = newTitle;
    }

    public void changeDescription(String newDescription) {
        this.ensureCanBeUpdated();

        this.description = Objects.requireNonNull(newDescription, "Task description cannot be null.");
    }

    public void changeStatus(TaskStatus newStatus) {
        this.ensureCanBeUpdated();

        this.status = Objects.requireNonNull(newStatus, "Task status cannot be null.");
    }

    public void changePriority(TaskPriority newPriority) {
        this.ensureCanBeUpdated();

        this.priority = Objects.requireNonNull(newPriority, "Task priority cannot be null.");
    }

    public void changeAssignee(UserId newAssigneeId) {
        this.ensureCanBeUpdated();

        this.assigneeId = Objects.requireNonNull(newAssigneeId, "Task assignee cannot be null.");
    }

    public void removeAssignee() {
        this.ensureCanBeUpdated();

        this.assigneeId = null;
    }

    public void changeSchedule(TaskSchedule newSchedule) {
        this.ensureCanBeUpdated();

        Objects.requireNonNull(newSchedule, "Task schedule cannot be null.");
        
        this.schedule = newSchedule;
    }

    public void emptySchedule() {
        this.ensureCanBeUpdated();

        this.schedule = new TaskSchedule(null, null);
    }

    public void archive() {
        if (this.isDeleted) {
            throw new IllegalStateException(
                "Deleted task cannot be archived."
            );
        }

        this.isArchived = true;
    }

    public void unarchive() {
        if (this.isDeleted) {
            throw new IllegalStateException(
                "Deleted task cannot be unarchived."
            );
        }

        this.isArchived = false;
    }

    public boolean isArchived() {
        return this.isArchived;
    }

    public void delete() {
        this.isDeleted = true;
    }

    public void restore() {
        this.isDeleted = false;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }

    private void ensureCanBeUpdated() {
        if (this.isArchived) {
            throw new IllegalStateException(
                "Archived task cannot be updated."
            );
        }

        if (this.isDeleted) {
            throw new IllegalStateException(
                "Deleted task cannot be updated."
            );
        }
    }

    public TaskId getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public TaskStatus getStatus() {
        return this.status;
    }

    public TaskPriority getPriority() {
        return this.priority;
    }

    public UserId getAssigneeId() {
        return this.assigneeId;
    }

    public TaskSchedule getSchedule() {
        return this.schedule;
    }
}
