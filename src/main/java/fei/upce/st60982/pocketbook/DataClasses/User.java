package fei.upce.st60982.pocketbook.DataClasses;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fei.upce.st60982.pocketbook.Config.CustomDateDeserializer;
import fei.upce.st60982.pocketbook.Config.CustomDateSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @JsonProperty("Id")
    private long id;
    @Column
    @NotNull
    @NotEmpty
    @JsonProperty("Username")
    private String username;
    @Column
    @JsonProperty("Password")
    private String password;
    @Column
    @JsonProperty("Active")
    private boolean active;
    @Column
    @JsonProperty("Admin_role")
    private boolean admin_role;
    @Column
    @JsonProperty("Deleted")
    private boolean deleted;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("DeletedDate")
    private LocalDateTime deleted_date;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("CreationDate")
    private LocalDateTime creation_date;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("UpdateDate")
    private LocalDateTime update_date;

    @OneToMany(mappedBy = "author")
    @JsonManagedReference(value="user-task")
    @JsonProperty("Tasks")
    private Set<Task> tasks = new HashSet<>();

    @OneToMany(mappedBy = "owner")
    @JsonManagedReference(value="user-note")
    @JsonProperty("Notes")
    private Set<Note> notes = new HashSet<>();
}
