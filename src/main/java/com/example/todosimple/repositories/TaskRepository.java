package com.example.todosimple.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.todosimple.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Usando query derivada baseada em convenção do Spring Data JPA
    List<Task> findByTaskGroupId(Long id);

    // Alternativamente, usando JPQL (se necessário mais controle sobre a query)
    //@Query("SELECT t FROM Task t WHERE t.taskGroup.id = :id")
    //List<Task> findByTaskGroupId(@Param("id") Long id);
}
