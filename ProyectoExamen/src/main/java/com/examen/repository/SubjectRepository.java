package com.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examen.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
