package fei.upce.st60982.pocketbook.Controllers;

import fei.upce.st60982.pocketbook.DataClasses.Note;
import fei.upce.st60982.pocketbook.DataClasses.Task;
import fei.upce.st60982.pocketbook.DataClasses.User;
import fei.upce.st60982.pocketbook.Repositories.NoteDAO;
import fei.upce.st60982.pocketbook.Repositories.TaskDAO;
import fei.upce.st60982.pocketbook.Repositories.UserDAO;
import javax.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level= AccessLevel.PRIVATE)
@RestController
public class TaskController {
    private UserDAO userRepository;
    private TaskDAO taskRepository;
    private NoteDAO noteRepository;

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskRepository.findAll().stream().filter(p->!p.isDeleted()).toList();
    }

    @GetMapping("/tasks/all")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/task/{id}")
    public Task getTaskById(@PathVariable(value="id") final int id){
        Task task = taskRepository.findTaskById(id);
        if(task == null || task.isDeleted()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        return task;
    }

    @PostMapping("/task/addTask")
    public void addTask(@RequestBody Task task){
        taskRepository.save(task);
    }

    @DeleteMapping(value={"/task/deleteTask/{id}"})
    public void deteleTask(@PathVariable(value="id") final int id){
        Task t = taskRepository.findTaskById(id);
        t.setDeleted(true);
        t.setDeleted_date(LocalDateTime.now());
        taskRepository.save(t);
    }

    @PutMapping(value={"/task/updateTask/{id}"})
    public void updateTask(@PathVariable(value="id") final int id, @Valid @RequestBody Task task){
        Task t = taskRepository.findTaskById(id);
        t.setDone(task.isDone());
        t.setDescription(task.getDescription());
        t.setTitle(task.getTitle());
        t.setNotes(task.getNotes());
        t.setDeleted(task.isDeleted());
        if(t.isDeleted())
            t.setDeleted_date(task.getDeleted_date());
        t.setCreation_date(task.getCreation_date());
        t.setUpdate_date(LocalDateTime.now());
        taskRepository.save(t);
    }

    @GetMapping(value={"/task/doneChange/{id}"})
    public boolean doneChangeTask(@PathVariable(value="id") final int id){
        Task t = taskRepository.findTaskById(id);
        if(t == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        else
            t.setDone(!t.isDone());
        taskRepository.save(t);
        return true;
    }

    @GetMapping("/task/byAuthor/{id}")
    public List<Task> getTaskByAuthorId(@PathVariable(value="id") final long id){
        User user = userRepository.findUserById(id);
        List<Task> l = taskRepository.findTasksByAuthor(user);
        return l.stream().filter(p -> !p.isDeleted()).toList();
    }

    @GetMapping("/task/byAuthor")
    public List<Task> getTaskByAuthor(@RequestBody User user){
        List<Task> l = taskRepository.findTasksByAuthor(user);
        return l.stream().filter(p -> !p.isDeleted()).toList();
    }
    @PostMapping("task/addNote/{id}")
    public ResponseEntity<Note> addNoteToTask(@PathVariable(value="id") final int id, @RequestBody Note note){
        note.setCreation_date(LocalDateTime.now());
        Task t = taskRepository.findTaskById(id);
        if(t == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        note.setAssignment(t);
        note.setUpdate_date(LocalDateTime.now());
        Note n = noteRepository.save(note);
        if (n == null) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        } else {
            return new ResponseEntity<>(n, HttpStatus.CREATED);
        }
    }
}
