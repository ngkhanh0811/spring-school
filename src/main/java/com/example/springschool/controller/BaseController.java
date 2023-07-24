package com.example.springschool.controller;

import com.example.springschool.properties.CommonProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
    Logger logger = LogManager.getLogger(ClassController.class);
    @Autowired
    protected CommonProperties commonProperties;
    private int level1 = 5000;
    private int level2 = 10000;
    protected void logKpi(long startTime, String method){
        Long processTime = System.currentTimeMillis() - startTime;
        if (processTime < level1){
            logger.info("Process time method {} = {}", method, processTime);
        } else if (processTime >= level1 && processTime <= level2){
            logger.warn("Process time method {} = {}", method, processTime);
        } else {
            logger.warn("Process time method {} = {}", method, processTime);
            //todo: send mail ------> operator
        }
    }
}