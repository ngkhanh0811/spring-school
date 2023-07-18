package com.example.springschool.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity(name = "classes")
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="className", nullable = false, unique = true)
    private String className;
    @Column(name="code", nullable = false, unique = true)
    private String code;
    @Column(name="description")
    private String description;
    @Column(name="startTime", nullable = false)
    private Date startTime;
    @Column(name="endtime")
    private Date endTime;
    @Column(name="currentSem")
    private int currentSem;
    @Column(name="size")
    private int size;
    @ManyToMany(mappedBy = "classesList", cascade = CascadeType.ALL)
    private List<Students> studentsList;
}
