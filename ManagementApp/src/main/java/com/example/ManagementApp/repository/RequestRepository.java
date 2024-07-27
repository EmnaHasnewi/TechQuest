package com.example.ManagementApp.repository;

import com.example.ManagementApp.entity.Request;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByUserId(Long userId);
    List<Request> findAllByOrderByIdDesc();
}