package com.example.springschool.controller;

import com.example.springschool.dto.ClassesDto;
import com.example.springschool.service.ClassServiceRepository;
import com.example.springschool.service.impl.ClassServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "api/v1")
public class ClassController extends BaseController {
    @Autowired
    private ClassServiceRepository classService;

    @PostMapping(value="/classes")
    public ResponseEntity<?> gets(@RequestBody ClassesDto criteria, HttpServletRequest request){
        return ResponseEntity.ok(classService.gets(criteria));
    }

    @PostMapping(value="classes/create")
    public ResponseEntity<?> createNewItem(@RequestBody ClassesDto classesDto, HttpServletRequest request){
        ClassesDto classesDto1 = classService.createNewItem(classesDto);
        return ResponseEntity.ok(classesDto1);
    }
}
