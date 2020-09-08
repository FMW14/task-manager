//package com.vtb.idrteam.taskmanager.entities;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.ColumnDefault;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Data
//@Table(name = "notifications")
//@NoArgsConstructor
//public class Notification {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Task task;
//
//    @Column(name = "message")
//    private String message;
//
//    @CreationTimestamp
//    @Column(name = "created_at")
//    @ColumnDefault("current_timestamp")
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    @Column(name = "updated_at")
//    @ColumnDefault("current_timestamp")
//    private LocalDateTime updatedAt;
//}
