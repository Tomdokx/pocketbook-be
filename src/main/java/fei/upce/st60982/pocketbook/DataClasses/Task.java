package fei.upce.st60982.pocketbook.DataClasses;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="Task")
public class Task {
    @Id
    private int id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private boolean done;
    @Column
    private boolean deleted;
    @Column
    private LocalDate deleted_date;
    @Column
    private LocalDate creation_date;
    @Column
    private LocalDate update_date;

    @ManyToOne
    @JoinColumn(name="app_user_id", nullable=false)
    @JsonBackReference
    private User author;

    @OneToMany(mappedBy = "assignment")
    @JsonManagedReference
    private Set<Note> notes = new HashSet<>();
}
