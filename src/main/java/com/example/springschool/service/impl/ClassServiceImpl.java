package com.example.springschool.service.impl;

import com.example.springschool.dto.ClassesDto;
import com.example.springschool.entity.Classes;
import com.example.springschool.exception.BusinessException;
import com.example.springschool.mapper.SchoolMapper;
import com.example.springschool.repo.ClassRepository;
import com.example.springschool.service.ClassServiceRepository;
import com.example.springschool.spec.ClassesSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ClassServiceImpl implements ClassServiceRepository {
    @Autowired
    private ClassRepository repo;
    @Autowired
    private SchoolMapper mapper;
    @Autowired
    private ClassesSpecification spec;
    @Override
    public List<ClassesDto> gets(ClassesDto criteria){
        Pageable pageable = PageRequest.of(criteria.getPageNumber(), criteria.getPageSize());
        Page<Classes> classesList = repo.findAll(spec.filter(criteria), pageable);
        return classesList.getContent()
                .stream()
                .map(mapper::classEntityToDto)
                .collect(Collectors.toList());
    }
    public ClassesDto createNewItem(ClassesDto classesDto){
        Date currentDate = new Date();
        if (classesDto.getStartTime().compareTo(currentDate) > 0 || classesDto.getEndTime().compareTo(currentDate) < 0){
            throw new BusinessException("201", "Invalid time");
        }
        try{
            Classes classes = mapper.classDtoToEntity(classesDto);
            Classes returnEntity = repo.save(classes);
            return mapper.classEntityToDto(returnEntity);
        }
        catch (Exception e){
            throw new BusinessException("201", "Object existed");
        }
    }
}
