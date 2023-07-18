package com.example.springschool.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity(name="skills")
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="skillName", nullable = false)
    private String skillName;
    @ManyToMany(mappedBy = "skillList")
    private List<Students> studentsList;
}
