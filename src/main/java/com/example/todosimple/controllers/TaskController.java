package com.example.todosimple.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.todosimple.models.Task;
import com.example.todosimple.services.TaskService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/task")
@Validated
public class TaskController {
 
    @Autowired
    private TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {
        Task task = this.taskService.findById(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<Task> create(@Valid @RequestBody Task task) {
        Task createdTask = this.taskService.create(task);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(createdTask.getId()).toUri();
        return ResponseEntity.created(uri).body(createdTask);
    }
    
    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Task> update(@PathVariable Long id, @Valid @RequestBody Task task) {
        task.setId(id);
        Task updatedTask = this.taskService.update(task);
        return ResponseEntity.ok(updatedTask);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> findAllByUserId(@PathVariable Long userId) {
        List<Task> tasks = this.taskService.findAllByUserId(userId);
        return ResponseEntity.ok(tasks);
    }
    
}
