package com.example.demo.model;

import com.example.demo.model.sampleEntity;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface sampleRepository extends JpaRepository<sampleEntity, BigDecimal> {

}
