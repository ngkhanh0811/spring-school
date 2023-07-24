package com.example.springschool.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.secure.spi.GrantedPermission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//@Data
//@NoArgsConstructor
//@SuperBuilder
//@Entity(name = "user")
@Getter
@Setter
public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    @Column(name="username")
    private String username;
//    @Column(name="password")
    private String password;
    String[] roles;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(
//            name="user_role",
//            joinColumns = @JoinColumn(name="user_id"),
//            inverseJoinColumns = @JoinColumn(name="role_id"))
//    private List<Role> roleList;

    public User(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public List<GrantedAuthority> getAuthorities(){
        //cách 1:
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (String role : roles){
//            authorities.add(new SimpleGrantedAuthority(role));
//        }
//        return authorities;

        //cách 2:
        return Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
