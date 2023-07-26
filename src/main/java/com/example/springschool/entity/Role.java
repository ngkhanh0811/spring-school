package com.example.springschool.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity(name="role")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="role_name")
    private String roleName;

    @ManyToMany(mappedBy = "roleList")
    List<User> userList;

    @ManyToMany(mappedBy = "roleList")
    List<Team> teamList;
}