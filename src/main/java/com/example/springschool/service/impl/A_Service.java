package com.example.springschool.service.impl;

import com.example.springschool.constant.ErrorCode;
import com.example.springschool.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public abstract class A_Service {
    void validateText(String ...texts){
        Arrays.asList(texts).stream().forEach(item -> validateText(item));
    }
    void validateText(List<String> texts){
        texts.stream().forEach(item -> {
            validateText(item, true, null, null);
        });
    }
    void validateText(String text){
        validateText(text, true, null, null);
    }
    void validateText(String text, boolean mandatory, Integer minLg, Integer maxLg){
        if (mandatory && StringUtils.isEmpty(text)){
            throw new BusinessException(ErrorCode.PARAM_INVALID.code, "Param invalid");
        }
        //todo: validate...
    }
}
