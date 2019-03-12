package com.dariawan.jdk10.optional;

import com.dariawan.jdk9.optional.Student;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class GetExample {

    public static void main(String[] args) {

        List<Optional<Student>> students = Arrays.asList(
                Optional.of(new Student(101, "Reed Richards")),
                Optional.of(new Student(102, "Susan Storm")),
                Optional.of(new Student(103, "Johnny Storm")),
                Optional.of(new Student(104, "Ben Grimm"))
        );
        
        Optional<Student> od = students.stream()
                .filter(Optional::isPresent)
                .filter(o -> o.get().getId().isPresent())
                .filter(o -> o.get().getId().get() == 105)
                .findAny()
                .get();
        System.out.println(od);
    }
}
