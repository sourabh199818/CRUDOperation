package com.example.crud.demo.service;

import com.example.crud.demo.dto.TaskResponse;
import com.example.crud.demo.exception.ResourceNotFoundException;
import com.example.crud.demo.model.Task;
import com.example.crud.demo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TaskService {
        private final TaskRepository taskRepository;

        public List<TaskResponse> getAlltask() {
                List<Task> task = taskRepository.findAll();

                return task.stream().map(this::maptoTaskResponse).collect(Collectors.toList());
        }


        public ResponseEntity<Task> getTaskById(@PathVariable Long id)
        {
                Task tas = taskRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id" +id));
                return ResponseEntity.ok(tas);
        }
        private TaskResponse maptoTaskResponse(Task task) {
                return TaskResponse.builder()
                        .id(task.getId())
                        .task(task.getTask())
                        .description(task.getDescription())
                        .build();
        }
//        public int   deleteatask( Long id) {
//
//                List<Long> s = new ArrayList<>();
//                s = tas
//                taskRepository.deleteById(id);
//
//                return 1;
//
//        }




        }
