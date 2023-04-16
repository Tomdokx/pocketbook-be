package fei.upce.st60982.pocketbook.Controllers;

import fei.upce.st60982.pocketbook.DataClasses.Note;
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
public class NoteController {
    private UserDAO userRepository;
    private TaskDAO taskRepository;
    private NoteDAO noteRepository;

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @GetMapping("/note/{id}")
    public Note getNoteById(@PathVariable(value="id") final long id){
        Note note = noteRepository.findNoteById(id);
        if(note == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        return note;
    }

    @GetMapping("/note/addNote")
    public void addNote(@RequestBody Note note){
        noteRepository.save(note);
    }

    @GetMapping(value={"/note/deleteNote/{id}"})
    public void deteleNote(@PathVariable(value="id") final long id){
        noteRepository.deleteById(id);
    }

    @GetMapping(value={"/note/updateNote/{id}"})
    public void updateNote(@PathVariable(value="id") final int id, @Valid @RequestBody Note note){
        Note n = noteRepository.findNoteById(id);
        n.setContent(note.getContent());
        n.setTitle(note.getTitle());
        n.setOwner(note.getOwner());
        n.setAssignment(note.getAssignment());
        n.setDeleted(note.isDeleted());
        n.setDeleted_date(note.getDeleted_date());
        n.setCreation_date(note.getCreation_date());
        n.setUpdate_date(note.getUpdate_date());
        noteRepository.save(n);
    }
}
