package fei.upce.st60982.pocketbook.DataClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@DynamicInsert
@DynamicUpdate
@Table(name="AppUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    @NotNull
    @NotEmpty
    @Max(255)
    private String username;
    @Column
    private String password;
    @Column
    private boolean active;
    @Column
    private LocalDate creation_date;
    @Column
    private LocalDate update_date;

    @OneToMany(mappedBy = "Author")
    @JsonManagedReference
    private Set<Task> tasks = new HashSet<>();

    @OneToMany(mappedBy = "owner")
    @JsonManagedReference
    private Set<Note> notes = new HashSet<>();
}
