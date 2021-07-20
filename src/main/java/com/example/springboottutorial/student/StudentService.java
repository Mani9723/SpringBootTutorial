package com.example.springboottutorial.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//Service Layer
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents()
    {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentEmail = studentRepository
                .findStudentByEmail(student.getEmail());
        if(studentEmail.isPresent()){
            throw new IllegalStateException("Email is already registered");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("Student "+ studentId +" Does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId,String name, String email)
    {
        // Check if student exists thru ID
        // If name and email are not null then update them

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student " +studentId+ " not found"));
        if(name != null && !name.isEmpty() && !student.getName().equals(name)){
            student.setName(name);
        }
        if(email != null && !email.isEmpty()
                && studentRepository.findStudentByEmail(email).isEmpty()
                && !student.getEmail().equals(email)){
            student.setEmail(email);
        }


    }
}
