package com.example.hibernate2;

import com.example.hibernate2.entity.User;
import com.example.hibernate2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.security.SecurityProperties;


@SpringBootApplication
public class Hibernate2Application implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Hibernate2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User("Katty", "Perry", "katty@gmail", 32));
        userRepository.addNew(new User(2L, "Mark", "Bolt", "mark@mail.ua", 43),
                new User(3L, "Simona", "Freedom", "simona@gukrnet", 95));
        System.out.println(userRepository.getById(1L));
        userRepository.getByEmail("mark@mail.ua").forEach(System.out::println);
        userRepository.updateUser(new User(1L, "Katty", "Perry", "katty@gmail", 35));
        userRepository.selectAll().forEach(System.out::println);
        userRepository.delete(1L);
    }
}
