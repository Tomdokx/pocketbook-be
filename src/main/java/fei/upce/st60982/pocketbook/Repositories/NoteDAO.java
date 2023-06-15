package fei.upce.st60982.pocketbook.Repositories;

import fei.upce.st60982.pocketbook.DataClasses.Note;
import fei.upce.st60982.pocketbook.DataClasses.Task;
import fei.upce.st60982.pocketbook.DataClasses.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteDAO extends JpaRepository<Note, Integer> {
    Note findNoteById(int id);
    List<Note> findNotesByOwner(User user);
    List<Note> findNotesByAssignment(Task task);
}
