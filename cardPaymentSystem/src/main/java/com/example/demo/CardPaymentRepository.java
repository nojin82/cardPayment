package com.example.demo;

import com.example.demo.CardPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardPaymentRepository extends JpaRepository<CardPaymentEntity, String> {

}
