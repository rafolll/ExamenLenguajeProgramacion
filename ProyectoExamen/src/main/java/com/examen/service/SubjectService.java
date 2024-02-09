package com.examen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.entity.Subject;
import com.examen.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject registrarSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Optional<Subject> obtenerSubject(Long id) {
        return subjectRepository.findById(id);
    }

    public List<Subject> listarSubjects() {
        return subjectRepository.findAll();
    }

    public Subject actualizarSubject(Long id, Subject newSubjectData) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()) {
            Subject existingSubject = optionalSubject.get();
            existingSubject.setSubject(newSubjectData.getSubject());
            existingSubject.setCredits(newSubjectData.getCredits());
            return subjectRepository.save(existingSubject);
        } else {
            throw new IllegalArgumentException("Subject con ID " + id + " no encontrado.");
        }
    }

    public void eliminarSubject(Long id) {
        subjectRepository.deleteById(id);
    }
}