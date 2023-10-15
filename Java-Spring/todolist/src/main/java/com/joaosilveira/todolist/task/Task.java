package com.joaosilveira.todolist.task;

import com.joaosilveira.todolist.users.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


/*
 *
 * ID
 * Usuário (ID_USUARIO)
 * Descrição
 * Título
 * Data de início
 * Data de término
 * Prioridade
 *
 */


@Data
@Entity(name = "tb_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String description;
    @Column(length = 50)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
