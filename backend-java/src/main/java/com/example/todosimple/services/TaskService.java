package com.example.todosimple.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.todosimple.models.Task;
import com.example.todosimple.models.TaskGroup;
import com.example.todosimple.repositories.TaskRepository;
import com.example.todosimple.repositories.TaskGroupRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskGroupRepository taskGroupRepository;

    public Task findById(Long id){
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Tarefa não encontrada! Id: " + id));
    }

    @Transactional
    public Task create(Task obj){
        TaskGroup taskGroup = taskGroupRepository.findById(obj.getTaskGroup().getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Grupo de tarefas não encontrado! Id: " + obj.getTaskGroup().getId()));
        obj.setId(null);
        obj.setTaskGroup(taskGroup);
        return this.taskRepository.save(obj);
    }
    
    @Transactional
    public Task update(Task obj){
        Task existingTask = findById(obj.getId());
        existingTask.setDescription(obj.getDescription());
        return this.taskRepository.save(existingTask);
    }

    public void delete(Long id){
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "Não é possível excluir a tarefa, pois há entidades relacionadas.");
        }
    }

    public List<Task> findByTaskGroupId(Long taskGroupId) {
        taskGroupRepository.findById(taskGroupId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Grupo de tarefas não encontrado! Id: " + taskGroupId));
        return taskRepository.findByTaskGroupId(taskGroupId);
    }
}
