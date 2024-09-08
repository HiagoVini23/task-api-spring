package com.example.todosimple.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.todosimple.models.TaskGroup;
import com.example.todosimple.services.TaskGroupService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/taskgroup")
@Validated
public class TaskGroupController {

    @Autowired
    private TaskGroupService taskGroupService;

    @GetMapping("/{id}")
    public ResponseEntity<TaskGroup> findById(@PathVariable Long id) {
        TaskGroup taskGroup = taskGroupService.findById(id);
        return ResponseEntity.ok(taskGroup);
    }

    @PostMapping
    public ResponseEntity<TaskGroup> create(@Valid @RequestBody TaskGroup taskGroup) {
        TaskGroup createdTaskGroup = taskGroupService.create(taskGroup);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(createdTaskGroup.getId()).toUri();
        return ResponseEntity.created(uri).body(createdTaskGroup);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskGroup> update(@PathVariable Long id, @Valid @RequestBody TaskGroup taskGroup) {
        taskGroup.setId(id);
        TaskGroup updatedTaskGroup = taskGroupService.update(taskGroup);
        return ResponseEntity.ok(updatedTaskGroup);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskGroupService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskGroup>> findAllByUserId(@PathVariable Long userId) {
        List<TaskGroup> taskGroups = taskGroupService.findAllByUserId(userId);
        return ResponseEntity.ok(taskGroups);
    }
}
