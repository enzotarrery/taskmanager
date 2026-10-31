package fr.demo.taskmanager.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

class TaskTest {

    // ------------ CREATION ------------ //

    @Test
    void shouldCreateTaskWithDefaultExpectedValues() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);

        // Assert
        assertEquals(id, task.getId());
        assertEquals(title, task.getTitle());
        assertEquals("", task.getDescription());
        assertEquals(TaskStatus.UNDEFINED, task.getStatus());
        assertEquals(TaskPriority.UNDEFINED, task.getPriority());
        assertNull(task.getAssigneeId());
        assertNotNull(task.getSchedule());
        assertNull(task.getSchedule().startDate());
        assertNull(task.getSchedule().endDate());
    }

    @Test
    void shouldRejectIsNullValue() {
        // Arrange
        String title = "Créer une page de connexion";

        // Act + Assert
        assertThrows(
            NullPointerException.class,
            () -> new Task(null, title)
        );
    }

    @Test
    void shouldRejectTitleNullValue() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());

        // Act + Assert
        assertThrows(
            NullPointerException.class,
            () -> new Task(id, null)
        );
    }

    @Test
    void shouldRejectEmptyTitle() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "";

        // Act + Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> new Task(id, title)
        );
    }

    @Test
    void shouldRejectBlankTitle() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "   ";

        // Act + Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> new Task(id, title)
        );
    }


    // ------------ UPDATE ------------ //

    @Test
    void shouldChangeTaskTitleToExpectedValue() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        String newTitle = "Créer une page de login";
        task.changeTitle(newTitle);

        // Assert
        assertEquals(newTitle, task.getTitle());
    }

    @Test
    void shouldRejectTitleChangedToEmptyValue() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        String newTitle = "";
        
        // Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> task.changeTitle(newTitle)
        );
    }

    @Test
    void shouldRejectTitleChangedToBlankValue() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        String newTitle = "  ";
        
        // Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> task.changeTitle(newTitle)
        );
    }

    @Test
    void shouldRejectTitleChangedToNullValue() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        String newTitle = null;
        
        // Assert
        assertThrows(
            NullPointerException.class,
            () -> task.changeTitle(newTitle)
        );
    }

    @Test
    void shouldChangeTaskDescriptionToExpectedValue() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        String newDescription = "Faire l'intégration + le système d'authentification.";
        task.changeDescription(newDescription);

        // Assert
        assertEquals(newDescription, task.getDescription());
    }

    @Test
    void shouldChangeTaskDescriptionToEmptyValue() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        String newDescription = "";
        task.changeDescription(newDescription);

        // Assert
        assertEquals(newDescription, task.getDescription());
    }

    @Test
    void shouldChangeTaskDescriptionToBlankValue() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        String newDescription = "  ";
        task.changeDescription(newDescription);

        // Assert
        assertEquals(newDescription, task.getDescription());
    }

    @Test
    void shouldRejectDescriptionChangedToNullValue() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);

        // Assert
        assertThrows(
            NullPointerException.class,
            () -> task.changeDescription(null)
        );
    }

    @Test
    void shouldChangeTaskStatusToExpectedValue() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        TaskStatus newStatus = TaskStatus.TO_DO;
        task.changeStatus(newStatus);

        // Assert
        assertEquals(newStatus, task.getStatus());
    }

    @Test
    void shouldRejectTaskStatusChangedToNullValue() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);

        // Assert
        assertThrows(
            NullPointerException.class,
            () -> task.changeStatus(null)
        );
    }

    @Test
    void shouldChangeTaskPriorityToExpectedValue() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        TaskPriority newPriority = TaskPriority.HIGH;
        task.changePriority(newPriority);

        // Assert
        assertEquals(newPriority, task.getPriority());
    }

    @Test
    void shouldRejectTaskPriorityChangedToNullValue() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);

        // Assert
        assertThrows(
            NullPointerException.class,
            () -> task.changePriority(null)
        );
    }

    @Test
    void shouldChangeTaskAssigneeToExpectedValue() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        UserId newAssigneeId = new UserId(UUID.randomUUID());
        task.changeAssignee(newAssigneeId);

        // Assert
        assertEquals(newAssigneeId, task.getAssigneeId());
    }

    @Test
    void shouldRejectAssigneeChangedToNullValue() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);

        // Assert
        assertThrows(
            NullPointerException.class,
            () -> task.changeAssignee(null)
        );
    }

    @Test
    void shouldRemoveTaskAssignee() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        UserId newAssigneeId = new UserId(UUID.randomUUID());
        task.changeAssignee(newAssigneeId);
        task.removeAssignee();

        // Assert
        assertNull(task.getAssigneeId());
    }

    @Test
    void shouldChangeScheduleToExpectedValue() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        ZoneId zone = ZoneId.of("Europe/Paris");
        ZonedDateTime startDate = ZonedDateTime.of(2026, 10, 28, 16, 5, 0, 0, zone);
        ZonedDateTime endDate = ZonedDateTime.of(2026, 11, 4, 14, 35, 0, 0, zone);
        TaskSchedule newSchedule = new TaskSchedule(startDate, endDate);

        // Act
        Task task = new Task(id, title);
        task.changeSchedule(newSchedule);

        // Assert
        assertEquals(startDate, task.getSchedule().startDate());
        assertEquals(endDate, task.getSchedule().endDate());
    }

    @Test
    void shouldRejectScheduleChangedToNullValue() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);

        // Assert
        assertThrows(
            NullPointerException.class,
            () -> task.changeSchedule(null)
        );
    }

    @Test
    void shouldEmptySchedule() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        ZoneId zone = ZoneId.of("Europe/Paris");
        ZonedDateTime startDate = ZonedDateTime.of(2026, 10, 28, 16, 5, 0, 0, zone);
        ZonedDateTime endDate = ZonedDateTime.of(2026, 11, 4, 14, 35, 0, 0, zone);
        TaskSchedule newSchedule = new TaskSchedule(startDate, endDate);

        // Act
        Task task = new Task(id, title);
        task.changeSchedule(newSchedule);
        task.emptySchedule();

        // Assert
        assertNull(task.getSchedule().startDate());
        assertNull(task.getSchedule().endDate());
    }

    @Test
    void shouldArchiveTask() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        task.archive();

        // Assert
        assertTrue(task.isArchived());
    }

    @Test
    void shouldRejectArchivedTaskTitleChange() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        task.archive();

        // Assert
        assertThrows(
            IllegalStateException.class,
            () -> task.changeTitle("Créer une page de login")
        );
    }

    @Test
    void shouldRejectArchivedTaskDescriptionChange() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        task.archive();

        // Assert
        assertThrows(
            IllegalStateException.class,
            () -> task.changeDescription("Faire l'intégration + le système d'authentification.")
        );
    }

    @Test
    void shouldRejectArchivedTaskStatusChange() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        task.archive();

        // Assert
        assertThrows(
            IllegalStateException.class,
            () -> task.changeStatus(TaskStatus.IN_PROGRESS)
        );
    }

    @Test
    void shouldRejectArchivedTaskPriorityChange() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        task.archive();

        // Assert
        assertThrows(
            IllegalStateException.class,
            () -> task.changePriority(TaskPriority.MEDIUM)
        );
    }

    @Test
    void shouldRejectArchivedTaskAssigneeChange() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        task.archive();

        // Assert
        assertThrows(
            IllegalStateException.class,
            () -> task.changeAssignee(new UserId(UUID.randomUUID()))
        );
    }

    @Test
    void shouldRejectArchivedTaskScheduleChange() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        ZoneId zone = ZoneId.of("Europe/Paris");
        ZonedDateTime startDate = ZonedDateTime.of(2026, 10, 28, 16, 5, 0, 0, zone);
        ZonedDateTime endDate = ZonedDateTime.of(2026, 11, 4, 14, 35, 0, 0, zone);
        TaskSchedule newSchedule = new TaskSchedule(startDate, endDate);

        // Act
        Task task = new Task(id, title);
        task.archive();

        // Assert
        assertThrows(
            IllegalStateException.class,
            () -> task.changeSchedule(newSchedule)
        );
    }

    @Test
    void shouldRejectArchivedTaskScheduleEmptying() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        task.archive();
        
        // Assert
        assertThrows(
            IllegalStateException.class,
            () -> task.emptySchedule()
        );
    }

    @Test
    void shouldUnarchiveTask() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        task.archive();
        task.unarchive();

        // Assert
        assertFalse(task.isArchived());
    }

    @Test
    void shouldDeleteTask() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        task.delete();

        // Assert
        assertTrue(task.isDeleted());
    }

    @Test
    void shouldRejectDeletedTaskTitleChange() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        task.delete();

        // Assert
        assertThrows(
            IllegalStateException.class,
            () -> task.changeTitle("Créer une page de login")
        );
    }

    @Test
    void shouldRejectDeletedTaskDescriptionChange() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        task.delete();

        // Assert
        assertThrows(
            IllegalStateException.class,
            () -> task.changeDescription("Faire l'intégration + le système d'authentification.")
        );
    }

    @Test
    void shouldRejectDeletedTaskStatusChange() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        task.delete();

        // Assert
        assertThrows(
            IllegalStateException.class,
            () -> task.changeStatus(TaskStatus.IN_PROGRESS)
        );
    }

    @Test
    void shouldRejectDeletedTaskPriorityChange() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        task.delete();

        // Assert
        assertThrows(
            IllegalStateException.class,
            () -> task.changePriority(TaskPriority.MEDIUM)
        );
    }

    @Test
    void shouldRejectDeletedTaskAssigneeChange() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        task.delete();

        // Assert
        assertThrows(
            IllegalStateException.class,
            () -> task.changeAssignee(new UserId(UUID.randomUUID()))
        );
    }

    @Test
    void shouldRejectDeletedTaskScheduleChange() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        ZoneId zone = ZoneId.of("Europe/Paris");
        ZonedDateTime startDate = ZonedDateTime.of(2026, 10, 28, 16, 5, 0, 0, zone);
        ZonedDateTime endDate = ZonedDateTime.of(2026, 11, 4, 14, 35, 0, 0, zone);
        TaskSchedule newSchedule = new TaskSchedule(startDate, endDate);

        // Act
        Task task = new Task(id, title);
        task.delete();

        // Assert
        assertThrows(
            IllegalStateException.class,
            () -> task.changeSchedule(newSchedule)
        );
    }

    @Test
    void shouldRejectDeletedTaskScheduleEmptying() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        task.delete();
        
        // Assert
        assertThrows(
            IllegalStateException.class,
            () -> task.emptySchedule()
        );
    }

    @Test
    void shouldRestoreTask() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        task.delete();
        task.restore();

        // Assert
        assertFalse(task.isDeleted());
    }


    // ------------ TRANSITIONS ETATS ------------ //

    @Test
    void shouldRejectDeletedTaskArchiving() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        task.delete();

        // Assert
        assertThrows(
            IllegalStateException.class,
            () -> task.archive()
        );
    }

    @Test
    void shouldRejectDeletedTaskUnarchiving() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        task.archive();
        task.delete();

        // Assert
        assertThrows(
            IllegalStateException.class,
            () -> task.unarchive()
        );
    }

    @Test
    void shouldDeleteArchivedTask() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        task.archive();
        task.delete();

        // Assert
        assertTrue(task.isArchived());
        assertTrue(task.isDeleted());
    }

    @Test
    void shouldRestoreArchivedTask() {
        // Arrange
        TaskId id = new TaskId(UUID.randomUUID());
        String title = "Créer une page de connexion";

        // Act
        Task task = new Task(id, title);
        task.archive();
        task.delete();
        task.restore();

        // Assert
        assertTrue(task.isArchived());
        assertFalse(task.isDeleted());
    }


}
