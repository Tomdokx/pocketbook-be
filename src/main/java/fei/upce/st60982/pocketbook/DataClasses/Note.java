package fei.upce.st60982.pocketbook.DataClasses;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name="Note")
public class Note {

    @Id
    private int id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private boolean deleted;
    @Column
    private LocalDate deleted_date;
    @Column
    private LocalDate creation_date;
    @Column
    private LocalDate update_date;

    @ManyToOne
    @JoinColumn(name="app_user_id", nullable=true)
    @JsonBackReference
    private User owner;

    @ManyToOne
    @JoinColumn(name="task_id", nullable=true)
    @JsonBackReference
    private Task assignment;
}
