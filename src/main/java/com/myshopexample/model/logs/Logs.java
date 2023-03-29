package com.myshopexample.model.logs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String adminName;
    private String description;
    @Enumerated(EnumType.STRING)
    private Action action;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate logDate;
    public Logs(){}
    public Logs(String adminName, String description, Action action, LocalDate logDate){
        this.adminName = adminName;
        this.description = description;
        this.action = action;
        this.logDate = logDate;
    }
}
