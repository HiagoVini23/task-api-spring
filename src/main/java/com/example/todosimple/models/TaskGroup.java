package com.example.todosimple.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Objects;

@Entity
@Table(name = TaskGroup.TABLE_NAME)
public class TaskGroup {
    public static final String TABLE_NAME = "task_group";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", length = 255, nullable = false)
    @NotBlank
    @Size(min = 1, max = 255)
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "taskGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Task> tasks = new ArrayList<>();

    public TaskGroup() {
    }

    public TaskGroup(Long id, User user, List<Task> tasks) {
        this.id = id;
        this.user = user;
        this.tasks = tasks;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskGroup id(Long id) {
        setId(id);
        return this;
    }

    public TaskGroup user(User user) {
        setUser(user);
        return this;
    }

    public void addTask(Task task) {
        task.setTaskGroup(this);
        this.tasks.add(task);
    }

    public void removeTask(Task task) {
        task.setTaskGroup(null);
        this.tasks.remove(task);
    }

    public TaskGroup tasks(List<Task> tasks) {
        setTasks(tasks);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        TaskGroup taskGroup = (TaskGroup) obj;
        return Objects.equals(id, taskGroup.id) &&
                Objects.equals(user, taskGroup.user) &&
                Objects.equals(tasks, taskGroup.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, tasks);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", user='" + getUser() + "'" +
                ", tasks='" + getTasks() + "'" +
                "}";
    }

}
