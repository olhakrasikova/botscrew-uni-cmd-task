package com.example.uni_test.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity()
@Table(name = "departments")
public class Department {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private  String department_name;

    @Column
    private String head_of_deparmtent;
}
