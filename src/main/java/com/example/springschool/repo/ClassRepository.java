package com.example.springschool.repo;

import com.example.springschool.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Classes, Long>, JpaSpecificationExecutor {
    @Query(value = "from classes c where c.className =: className")
    Optional<Classes> findByName(@Param("className") String className);
}