package com.example.springschool.dto;/*Welcome to my show !

@author: NgKhanh
Date: 6/28/2023
Time: 6:46 PM

ProjectName: spring-demo*/

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class PageDto {
    private Integer pageSize;
    private Integer pageNumber;
    /* int và Integer khác nhau
    Integer: object -> nullable
    int: primative -> kiểu dữ liệu nguyên thủy (not null): dùng khi chắc chắn là not null
    */
}
