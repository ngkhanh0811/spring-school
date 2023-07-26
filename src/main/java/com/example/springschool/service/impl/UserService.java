package com.example.springschool.service.impl;

import com.example.springschool.dto.LoginResponseDto;
import com.example.springschool.entity.User;
import com.example.springschool.repo.UserRepository;
import com.example.springschool.service.UserServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService extends A_Service implements UserServiceRepository {
    @Autowired
    private UserRepository repo;

    public List<User> getAll(){
        return repo.findAll();
    }


//    public static List<User> users = new ArrayList<>();

//    static {
//        User user1 = new User(1, "admin1", "admin@123");
//        User user2 = new User(2, "admin2", "admin@123");
//        User user3 = new User(3, "admin3", "admin@123");
//
//        user1.setRoles(new String[]{"ROLE_ADMIN"});
//        user2.setRoles(new String[]{"ROLE_USER", "ROLE_ADMIN"});
//        user3.setRoles(new String[]{"ROLE_USER"});
//
//        users.add(user1);
//        users.add(user2);
//        users.add(user3);
//    }
    public LoginResponseDto returnLogin(User user){
        return LoginResponseDto.builder()
                .access_token(JWTUtils.genRefreshToken(user))
                .refresh_token(JWTUtils.genRefreshToken(user))
                .expired_time(System.currentTimeMillis() + JWTUtils.expireTime).build();
    }

    @Override
    public User findById(long id) {
        return repo.findById(id);
    }

    @Override
    public boolean add(User user) {
        if (!Objects.isNull(user) && user.getId() == 0){
            repo.save(user);
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
        for (User existedUser: getAll()){
            if (user.getUsername().equals(existedUser.getUsername()) && user.getPassword().equals(existedUser.getPassword())){
                return true;
            }
        }
        return false;
    }
}
