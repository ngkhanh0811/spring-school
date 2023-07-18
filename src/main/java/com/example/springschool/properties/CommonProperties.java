package com.example.springschool.properties;/*Welcome to my show !

@author: NgKhanh
Date: 6/28/2023
Time: 6:56 PM

ProjectName: spring-demo*/

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class CommonProperties {
    @Value("10")
    private Integer defaultPageSize;
    @Value("0")
    private Integer defaultPageNumber;
}

//@Data
//@Configuration(value = "application.common")
//public class CommonProperties {
//    private Integer defaultPageSize;
//    private Integer defaultPageNumber;
//}