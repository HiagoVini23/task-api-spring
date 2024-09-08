package com.example.todosimple.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.todosimple.models.TaskGroup;

@Repository
public interface TaskGroupRepository extends JpaRepository<TaskGroup, Long> {

    @Query(value = "SELECT * FROM task_group t WHERE t.user_id = :id", nativeQuery = true)
    List<TaskGroup> findByUserId(@Param("id") Long id);
}