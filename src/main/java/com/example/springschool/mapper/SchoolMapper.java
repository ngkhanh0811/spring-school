package com.example.springschool.mapper;

import com.example.springschool.dto.ClassesDto;
import com.example.springschool.dto.SkillsDto;
import com.example.springschool.dto.StudentsDto;
import com.example.springschool.entity.Classes;
import com.example.springschool.entity.Skills;
import com.example.springschool.entity.Students;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface SchoolMapper {
    Classes classDtoToEntity(ClassesDto classesDto);
    ClassesDto classEntityToDto(Classes classes);

    Skills skillDtoToEntity(SkillsDto skillsDto);
    SkillsDto skillEntityToDto(Skills skills);

    Students studentDtoToEntity(StudentsDto studentsDto);
    StudentsDto studentEntityToDto(Students students);
}
