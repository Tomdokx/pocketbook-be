package fei.upce.st60982.pocketbook.DataClasses;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("Id")
    private int id;
    @Column
    @JsonProperty("Title")
    private String title;
    @Column
    @JsonProperty("Description")
    private String description;
    @Column
    @JsonProperty("Done")
    private boolean done;
    @Column
    @JsonProperty("Deleted")
    private boolean deleted;
    @Column
    @JsonProperty("DeletedDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deleted_date;
    @Column
    @JsonProperty("CreationDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creation_date;
    @Column
    @JsonProperty("UpdateDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime update_date;

    @JsonProperty("Author")
    @ManyToOne
    @JoinColumn(name="app_user_id", nullable=false)
    @JsonBackReference(value="user-task")
    private User author;

    @JsonProperty("Notes")
    @OneToMany(mappedBy = "assignment")
    @JsonManagedReference(value="task-note")
    private Set<Note> notes = new HashSet<>();
}
