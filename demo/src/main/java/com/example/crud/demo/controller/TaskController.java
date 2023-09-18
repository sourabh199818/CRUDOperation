package com.example.crud.demo.controller;


import com.example.crud.demo.dto.TaskResponse;
import com.example.crud.demo.exception.ResourceNotFoundException;
import com.example.crud.demo.model.Task;
import com.example.crud.demo.repository.TaskRepository;
import com.example.crud.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@Controller
@RequestMapping("/api/controller")
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;
    private final TaskService taskService;

    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public String getAllTasks(Model model) {
        List<TaskResponse> taskResponses = taskService.getAlltask();
        model.addAttribute("tasks", taskResponses);
      return  "index";
    }

        @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskRepository.save(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") Long id) {
        ResponseEntity<Task> responseEntity = taskService.getTaskById(id);
        return responseEntity;
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not exist with id " + id));

        existingTask.setTask(task.getTask());
        existingTask.setDescription(task.getDescription());

        Task updatedTask = taskRepository.save(existingTask);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTask(@PathVariable Long id) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not exist with id " + id));

        taskRepository.delete(existingTask);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
