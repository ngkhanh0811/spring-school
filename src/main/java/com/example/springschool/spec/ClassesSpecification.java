package com.example.springschool.spec;

import com.example.springschool.dto.ClassesDto;
import com.example.springschool.entity.Classes;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.ap.shaded.freemarker.template.utility.StringUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClassesSpecification {
    public Specification<Classes> filter(final ClassesDto criteria){
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            // if name exists -> search by name
            if (StringUtils.isNotEmpty(criteria.getClassName())){
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("className")), "%" + criteria.getClassName().toUpperCase() + "%"));
            }
            if (StringUtils.isNotEmpty(criteria.getCode())){
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("code")), "%" + criteria.getCode().toUpperCase() + "%"));
            }
            return criteriaBuilder.and(predicates.stream()
                    .toArray(Predicate[]::new));
        };
    }
}
