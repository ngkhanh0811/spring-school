package com.example.springschool.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassesDto extends PageDto{
    private long id;
    private String className;
    private String code;
    private String description;
    private Date startTime;
    private Date endTime;
    private int currentSem;
    private int size;
}
