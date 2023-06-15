package fei.upce.st60982.pocketbook.DataClasses;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fei.upce.st60982.pocketbook.Config.CustomDateDeserializer;
import fei.upce.st60982.pocketbook.Config.CustomDateSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="Note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("Id")
    private int id;
    @Column
    @JsonProperty("Title")
    private String title;
    @Column
    @JsonProperty("Content")
    private String content;
    @Column
    @JsonProperty("Deleted")
    private boolean deleted;
    @JsonProperty("DeletedDate")
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deleted_date;
    @JsonProperty("CreationDate")
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creation_date;
    @JsonProperty("UpdateDate")
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime update_date;

    @JsonProperty("Owner")
    @ManyToOne
    @JoinColumn(name="app_user_id", nullable=true)
    @JsonBackReference(value="user-note")
    private User owner;

    @JsonProperty("Assignment")
    @ManyToOne
    @JoinColumn(name="task_id", nullable=true)
    @JsonBackReference(value="task-note")
    private Task assignment;
}
