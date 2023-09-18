package com.example.crud.demo.repository;

import com.example.crud.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository< Task, Long> {

}
