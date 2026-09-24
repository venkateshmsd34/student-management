package com.example.student_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student_management.entity.Student;
import com.example.student_management.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    // Create
    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    // Read All
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // Read By Id
    public Student getStudentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // Update
    public Student updateStudent(Long id, Student student) {
        Student existing = getStudentById(id);

        existing.setName(student.getName());
        existing.setEmail(student.getEmail());
        existing.setCourse(student.getCourse());

        return repository.save(existing);
    }

    // Delete
    public String deleteStudent(Long id) {
        repository.deleteById(id);
        return "Student deleted successfully";
    }
}