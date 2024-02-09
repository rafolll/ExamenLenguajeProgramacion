package com.examen.consola;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.examen.entity.Subject;
import com.examen.service.SubjectService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class ConsoleSubjectCrud {

    private final SubjectService subjectService;
    private final Scanner scanner;

    @Autowired
    public ConsoleSubjectCrud(SubjectService subjectService) {
        this.subjectService = subjectService;
        this.scanner = new Scanner(System.in);
    }

    public void iniciarCrud() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Registrar Subject");
            System.out.println("2. Editar Subject");
            System.out.println("3. Eliminar Subject");
            System.out.println("4. Listar Subjects");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    registrarSubject();
                    break;
                case 2:
                    editarSubject();
                    break;
                case 3:
                    eliminarSubject();
                    break;
                case 4:
                    listarSubjects();
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close();
    }

    private void registrarSubject() {
        System.out.println("Ingrese el nombre del Subject:");
        String subject = scanner.nextLine();
        System.out.println("Ingrese los créditos del Subject:");
        String credits = scanner.nextLine();

        Subject newSubject = new Subject();
        newSubject.setSubject(subject);
        newSubject.setCredits(credits);

        subjectService.registrarSubject(newSubject);
        System.out.println("Subject registrado correctamente.");
    }

    private void editarSubject() {
        System.out.println("Ingrese el ID del Subject a editar:");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Limpiar el buffer de entrada

        Optional<Subject> optionalSubject = subjectService.obtenerSubject(id);
        if (optionalSubject.isPresent()) {
            Subject subject = optionalSubject.get();
            System.out.println("Ingrese el nuevo nombre del Subject:");
            String newSubjectName = scanner.nextLine();
            System.out.println("Ingrese los nuevos créditos del Subject:");
            String newSubjectCredits = scanner.nextLine();

            subject.setSubject(newSubjectName);
            subject.setCredits(newSubjectCredits);

            subjectService.actualizarSubject(id, subject);
            System.out.println("Subject actualizado correctamente.");
        } else {
            System.out.println("No se encontró ningún Subject con el ID proporcionado.");
        }
    }

    private void eliminarSubject() {
        System.out.println("Ingrese el ID del Subject a eliminar:");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Limpiar el buffer de entrada

        subjectService.eliminarSubject(id);
        System.out.println("Subject eliminado correctamente.");
    }

    private void listarSubjects() {
        List<Subject> subjects = subjectService.listarSubjects();
        if (subjects.isEmpty()) {
            System.out.println("No hay Subjects registrados.");
        } else {
            System.out.println("Listado de Subjects:");
            for (Subject subject : subjects) {
                System.out.println("ID: " + subject.getId() + ", Nombre: " + subject.getSubject() + ", Créditos: " + subject.getCredits());
            }
        }
    }
}

