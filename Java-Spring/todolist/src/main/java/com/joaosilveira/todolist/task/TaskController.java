package com.joaosilveira.todolist.task;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @PostMapping("/")
    public Task create(@RequestBody Task task, HttpServletRequest request) {
        var userId = (request.getAttribute("userId"));
        task.setUserId((Long) userId);
        var taskCreated = taskRepository.save(task);
        return taskCreated;
    }

}
