package fei.upce.st60982.pocketbook.Repositories;

import fei.upce.st60982.pocketbook.DataClasses.Note;
import fei.upce.st60982.pocketbook.DataClasses.Task;
import fei.upce.st60982.pocketbook.DataClasses.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteDAO extends JpaRepository<Note, Long> {
    Note findNoteById(long id);
    List<Note> findByOwner(User user);
    List<Note> findByAssignment(Task task);
}
