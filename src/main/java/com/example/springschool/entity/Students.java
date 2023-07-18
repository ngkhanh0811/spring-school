package com.example.springschool.entity;

import com.example.springschool.constant.Gender;
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
@Entity(name = "students")
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="studentName", nullable = false)
    private String studentName;
    @Column(name="code", nullable = false)
    private String code;
    @Column(name="birthDate")
    private Date birthDate;
    @Column(name="gender", nullable = false)
    private int gender;
    @Column (name="address")
    private String address;
    @Column(name="number")
    private String number;


    @PrePersist
    public void beforeInsert() {
        if (gender != Gender.MALE.val && gender!= Gender.FEMALE.val && gender != Gender.OTHER.val){
            this.gender = Gender.OTHER.val;
        }
    }

    @PreUpdate
    public void beforeUpdate() {
        if (gender != Gender.MALE.val && gender!= Gender.FEMALE.val && gender != Gender.OTHER.val){
            this.gender = Gender.OTHER.val;
        }
    }

    @ManyToMany
    @JoinTable(
            name = "class_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id"))
    private List<Classes> classesList;

    @ManyToMany
    @JoinTable(
            name = "skill_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skills> skillList;
}
