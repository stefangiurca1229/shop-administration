package com.myshopexample.model.admin;

import com.myshopexample.model.department.Department;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
public class Administrator implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToMany
    @JoinTable(
            name="administrator_deprment",
            joinColumns = @JoinColumn(name = "admin_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "department_id",referencedColumnName = "id")
    )
    private List<Department> departments;

    public Administrator(){}
    public Administrator(String name, String password, Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }
}
