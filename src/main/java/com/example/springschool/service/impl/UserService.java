package com.example.springschool.service.impl;

import com.example.springschool.dto.LoginResponseDto;
import com.example.springschool.entity.User;
import com.example.springschool.service.UserServiceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService extends A_Service implements UserServiceRepository {
    public static void main(String[] args) {
        JWTUtils jwtUtils = new JWTUtils();
        String token = JWTUtils.genToken(users.get(0));
        System.err.println(jwtUtils.getExpireDateFromToken(token));
        System.err.println(jwtUtils.isTokenExpire(token));
        System.err.println(jwtUtils.getUsernameFromToken(token));
    }

    public static List<User> users = new ArrayList<>();

    static {
        User user1 = new User(1, "admin1", "admin@123");
        User user2 = new User(2, "admin2", "admin@123");
        User user3 = new User(3, "admin3", "admin@123");

        user1.setRoles(new String[]{"ROLE_ADMIN"});
        user2.setRoles(new String[]{"ROLE_USER", "ROLE_ADMIN"});
        user3.setRoles(new String[]{"ROLE_USER"});

        users.add(user1);
        users.add(user2);
        users.add(user3);
    }
    public LoginResponseDto returnLogin(User user){
        return LoginResponseDto.builder()
                .access_token(JWTUtils.genRefreshToken(user))
                .refresh_token(JWTUtils.genRefreshToken(user))
                .expired_time(System.currentTimeMillis() + JWTUtils.expireTime).build();
    }

    @Override
    public User findById(long id) {
        return users.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
    }

    @Override
    public boolean add(User user) {
        if (!Objects.isNull(user) && user.getId() == 0){
            validateText(user.getUsername());
            Optional<User> existedUser = users.stream().filter(u -> u.getUsername().equals(user.getUsername())).findFirst();
            if (existedUser.isPresent()) return false;
            users.add(user);
            return true;
        }
        return false;
    }

    @Override
    public User getByName(String username) {
        return null;
    }

    @Override
    public boolean checkLogin(User user) {
        for (User existedUser: users){
            if (user.getUsername().equals(existedUser.getUsername()) && user.getPassword().equals(existedUser.getPassword())){
                return true;
            }
        }
        return false;
    }
}
