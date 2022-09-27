package com.example.uni_test.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "lectors")
public class Lector {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    private int salary;

    @Column
    private String degree;

}