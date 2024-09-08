package com.example.todosimple.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.todosimple.models.TaskGroup;
import com.example.todosimple.models.User;
import com.example.todosimple.repositories.TaskGroupRepository;
import com.example.todosimple.repositories.UserRepository;

@Service
public class TaskGroupService {

    @Autowired
    private TaskGroupRepository taskGroupRepository;

    @Autowired
    private UserRepository userRepository;

    public TaskGroup findById(Long id) {
        Optional<TaskGroup> taskGroup = this.taskGroupRepository.findById(id);
        return taskGroup.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Grupo de Tarefas não encontrado! Id: " + id));
    }

    public List<TaskGroup> findAllByUserId(Long userId) {
        List<TaskGroup> taskGroups = this.taskGroupRepository.findByUserId(userId);
        if (taskGroups.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
                "Nenhum grupo de tarefas encontrado para o usuário com ID: " + userId);
        }
        return taskGroups;
    }

    @Transactional
    public TaskGroup create(TaskGroup obj) {
        User user = userRepository.findById(obj.getUser().getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
            "Usuário não encontrado para criar o grupo de tarefas. Id: " + obj.getUser().getId()));
        obj.setId(null);
        obj.setUser(user);
        return this.taskGroupRepository.save(obj);
    }

    @Transactional
    public TaskGroup update(TaskGroup obj) {
        TaskGroup existingGroup = findById(obj.getId());
        existingGroup.setUser(obj.getUser());
        return this.taskGroupRepository.save(existingGroup);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.taskGroupRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "Não é possível excluir o grupo de tarefas, pois há entidades relacionadas.");
        }
    }
}
