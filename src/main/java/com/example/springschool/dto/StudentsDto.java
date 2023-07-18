package com.example.springschool.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentsDto {
    private long id;
    private String studentName;
    private Date birthDate;
    private String gender;
    private String address;
    private String number;
}
