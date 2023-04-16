package fei.upce.st60982.pocketbook.Controllers;

import fei.upce.st60982.pocketbook.DataClasses.Task;
import fei.upce.st60982.pocketbook.DataClasses.User;
import fei.upce.st60982.pocketbook.Repositories.NoteDAO;
import fei.upce.st60982.pocketbook.Repositories.TaskDAO;
import fei.upce.st60982.pocketbook.Repositories.UserDAO;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level= AccessLevel.PRIVATE)
@RestController
public class TaskController {
    private UserDAO userRepository;
    private TaskDAO taskRepository;
    private NoteDAO noteRepository;

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/task/{id}")
    public Task getTaskById(@PathVariable(value="id") final long id){
        Task task = taskRepository.findTaskById(id);
        if(task == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        return task;
    }

    @GetMapping("/task/addTask")
    public void addTask(@RequestBody Task task){
        taskRepository.save(task);
    }

    @GetMapping(value={"/task/deleteTask/{id}"})
    public void deteleTask(@PathVariable(value="id") final long id){
        taskRepository.deleteById(id);
    }

    @GetMapping(value={"/task/updateTask/{id}"})
    public void updateTask(@PathVariable(value="id") final int id, @Valid @RequestBody Task task){
        Task t = taskRepository.findTaskById(id);
        t.setDone(task.isDone());
        t.setDescription(task.getDescription());
        t.setTitle(task.getTitle());
        t.setAuthor(task.getAuthor());
        t.setNotes(task.getNotes());
        t.setDeleted(task.isDeleted());
        t.setDeleted_date(task.getDeleted_date());
        t.setCreation_date(task.getCreation_date());
        t.setUpdate_date(task.getUpdate_date());
        taskRepository.save(t);
    }
}
