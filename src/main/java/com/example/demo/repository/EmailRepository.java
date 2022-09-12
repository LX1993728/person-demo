package com.example.demo.repository;

import com.example.demo.entities.EmailEntity;
import com.example.demo.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmailRepository extends JpaRepository<EmailEntity, Long> {
}
