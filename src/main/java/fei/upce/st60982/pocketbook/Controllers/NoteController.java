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
import net.bytebuddy.asm.Advice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level= AccessLevel.PRIVATE)
@RestController
public class NoteController {
    private UserDAO userRepository;
    private TaskDAO taskRepository;
    private NoteDAO noteRepository;

    @GetMapping("/notes")
    public List<Note> getNotes() {
        return noteRepository.findAll().stream().filter(p->!p.isDeleted()).toList();
    }

    @GetMapping("/notes/all")
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }


    @GetMapping("/note/{id}")
    public Note getNoteById(@PathVariable(value="id") final int id){
        Note note = noteRepository.findNoteById(id);
        if(note == null || note.isDeleted()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        return note;
    }

    @PostMapping("/note/addNote")
    public ResponseEntity<Note> addNote(@RequestBody Note note){
        Note n = noteRepository.save(note);
        note.setCreation_date(LocalDateTime.now());
        note.setUpdate_date(LocalDateTime.now());

        if (n == null) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        } else {
            return new ResponseEntity<>(n, HttpStatus.CREATED);
        }
    }

    @PostMapping("/note/addNoteToOwner/{id}")
    public ResponseEntity<Note> addNoteWithOwner(@PathVariable(value="id") final long id, @RequestBody Note note){
        User u = userRepository.findUserById(id);
        note.setOwner(u);
        note.setCreation_date(LocalDateTime.now());
        note.setUpdate_date(LocalDateTime.now());
        Note n = noteRepository.save(note);

        if (n == null) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        } else {
            return new ResponseEntity<>(n, HttpStatus.CREATED);
        }
    }

    @PostMapping("/note/addNoteToAssignment/{id}")
    public ResponseEntity<Note> addNoteWithAssignment(@PathVariable(value="id") final int id,@RequestBody Note note){
        Task t = taskRepository.findTaskById(id);
        note.setAssignment(t);
        note.setCreation_date(LocalDateTime.now());
        note.setUpdate_date(LocalDateTime.now());
        Note n = noteRepository.save(note);


        if (n == null) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        } else {
            return new ResponseEntity<>(n, HttpStatus.CREATED);
        }
    }

    @DeleteMapping(value={"/note/deleteNote/{id}"})
    public void deteleNote(@PathVariable(value="id") final int id){
        Note n = noteRepository.findNoteById(id);
        n.setDeleted(true);
        n.setDeleted_date(LocalDateTime.now());
        noteRepository.save(n);
    }

    @GetMapping(value={"/note/updateNote/{id}"})
    public void updateNote(@PathVariable(value="id") final int id, @Valid @RequestBody Note note){
        Note n = noteRepository.findNoteById(id);
        n.setContent(note.getContent());
        n.setTitle(note.getTitle());
        n.setDeleted(note.isDeleted());
        if(n.isDeleted())
            n.setDeleted_date(note.getDeleted_date());
        n.setCreation_date(note.getCreation_date());
        n.setUpdate_date(LocalDateTime.now());
        noteRepository.save(n);
    }

    @GetMapping("/note/byOwner/{id}")
    public List<Note> getNotesByOwnerId(@PathVariable(value="id") final long id){
        User user = userRepository.findUserById(id);
        List<Note> l = noteRepository.findNotesByOwner(user);
        return l.stream().filter(p -> !p.isDeleted()).toList();
    }

    @GetMapping("/note/byOwner")
    public List<Note> getNotesByOwner(@RequestBody User user){
        List<Note> l = noteRepository.findNotesByOwner(user);
        return l.stream().filter(p -> !p.isDeleted()).toList();
    }

    @GetMapping("/note/byAssignment/{id}")
    public List<Note> getNotesByAssignmentId(@PathVariable(value="id") final int id){
        Task task = taskRepository.findTaskById(id);
        List<Note> l = noteRepository.findNotesByAssignment(task);
        return l.stream().filter(p -> !p.isDeleted()).toList();
    }

    @GetMapping("/note/byAssignment")
    public List<Note> getNotesByAssignment(@RequestBody Task task){
        List<Note> l = noteRepository.findNotesByAssignment(task);
        return l.stream().filter(p -> !p.isDeleted()).toList();
    }
}
