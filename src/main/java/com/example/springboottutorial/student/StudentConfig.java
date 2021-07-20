package com.example.springboottutorial.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student mariam = new Student("Marian","mariam@gmail.com",
                    LocalDate.of(1997,2,23));
            Student alex = new Student("Alex","alex@gmail.com",
                    LocalDate.of(2004,2,23));
            repository.saveAll(List.of(mariam,alex));
        };
    }
}
