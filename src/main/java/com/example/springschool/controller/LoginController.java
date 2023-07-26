package com.example.springschool.controller;

import com.example.springschool.dto.LoginResponseDto;
import com.example.springschool.entity.User;
import com.example.springschool.repo.UserRepository;
import com.example.springschool.service.impl.JWTUtils;
import com.example.springschool.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class LoginController extends BaseController {
    private static Logger logger = LogManager.getLogger(ClassController.class);
    @Autowired
    UserService service;
    @Autowired
    UserRepository repo;
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody User user, HttpServletRequest request){
        logger.info("Process = login, username = {}", user.getUsername());
        Long start_time = System.currentTimeMillis();
        LoginResponseDto loginResponseDto = null;
        String result = "";
        HttpStatus status = null;
        try{
        if (service.checkLogin(user)) {
            result = JWTUtils.genToken(user);
            status = HttpStatus.OK;
            loginResponseDto = service.returnLogin(user);
        } else{
            result = "user is not valid";
            status = HttpStatus.UNAUTHORIZED;
        }
        } catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logKpi(start_time, "login");
//        System.out.println(loginResponseDto);
        return new ResponseEntity<LoginResponseDto>(loginResponseDto, status);
    }

    @GetMapping("/users")
    public ResponseEntity<?> users(HttpServletRequest request){
        System.out.println("kajsglAKGF");
        JWTUtils jwtUtils = new JWTUtils();
        String token = JWTUtils.genToken(repo.findAll().get(0));
        System.err.println(jwtUtils.getExpireDateFromToken(token));
        System.err.println(jwtUtils.isTokenExpire(token));
        System.err.println(jwtUtils.getUsernameFromToken(token));
        return ResponseEntity.ok(repo.findAll());
    }
}
