package com.vtb.idrteam.taskmanager.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.vtb.idrteam.taskmanager.entities.simpletables.TaskStatus;
import com.vtb.idrteam.taskmanager.utils.Views;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString(of = {"id", "name", "description", "deadlineTime", "createdAt", "updatedAt", "archived", "taskStatus", "project"})
@Table(name = "tasks")
@NoArgsConstructor
public class Task {
    //Задачи могут иметь статусы: создана, в работе, передана на проверку, возвращена на доработку, завершена, отменена.
    //CREATED, IN_PROGRESS, ON_REVIEW, ON_REWORK, COMPLETED, CANCELED
//    public enum Status {
//        CREATED, IN_PROCESS, IN_REVIEW, IN_REWORK, COMPLETED, CANCELED;
//    }

    //Приоритет задачи имеет 6 уровней: в планах, очень низкий, низкий, средний, высокий, очень высокий.
//    public enum Priority{
//
//    }

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", length = 100)
    @JsonView(Views.Small.class)
    private String name;

    @Column(name = "description", length = 500)
    @JsonView(Views.Small.class)
    private String description;

    @Column(name = "archived")
    @JsonView(Views.Small.class)
    private Boolean archived;

    @Column(name = "deadline_time")
    @JsonView(Views.BigTask.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime deadlineTime;

    @CreationTimestamp
    @JsonView(Views.FullTask.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "created_at", updatable = false)
    @ColumnDefault("current_timestamp")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonView(Views.FullTask.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "updated_at")
    @ColumnDefault("current_timestamp")
    private LocalDateTime updatedAt;

    //Настройка видимости задачи
//    @JsonView(Views.BigTask.class)
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "task_authority_id", referencedColumnName = "id")
//    private TaskAuthority taskAuthority;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @JsonView(Views.BigTask.class)
    @OneToMany(mappedBy = "task",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<TaskParticipant> taskParticipants;

    @JsonIgnore
    @OneToMany(
            mappedBy = "task",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Notification> notifications = new ArrayList<>();

    @JsonView(Views.BigTask.class)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_state_id", referencedColumnName = "id")
    private TaskStatus taskStatus;
}
