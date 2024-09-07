package com.example.todosimple.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todosimple.services.TaskService;

@RestController
@RequestMapping("/task")
@Validated
public class TaskController {
 
    @Autowired
    private TaskService taskService;
}
