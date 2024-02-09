package com.examen;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.examen.consola.ConsoleSubjectCrud;

@SpringBootApplication
public class ConsoleCrudApplication implements CommandLineRunner {

    private final ConsoleSubjectCrud consoleSubjectCrud;

    public ConsoleCrudApplication(ConsoleSubjectCrud consoleSubjectCrud) {
        this.consoleSubjectCrud = consoleSubjectCrud;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsoleCrudApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        consoleSubjectCrud.iniciarCrud();
    }
}

