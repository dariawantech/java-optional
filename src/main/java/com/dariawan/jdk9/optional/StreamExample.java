/**
 * Java Optional With Examples v1 (https://www.dariawan.com)
 * Copyright (C) 2019 Dariawan <hello@dariawan.com>
 *
 * Creative Commons Attribution-ShareAlike 4.0 International License
 *
 * Under this license, you are free to: # Share - copy and redistribute the
 * material in any medium or format # Adapt - remix, transform, and build upon
 * the material for any purpose, even commercially.
 *
 * The licensor cannot revoke these freedoms as long as you follow the license
 * terms.
 *
 * License terms: # Attribution - You must give appropriate credit, provide a
 * link to the license, and indicate if changes were made. You may do so in any
 * reasonable manner, but not in any way that suggests the licensor endorses you
 * or your use. # ShareAlike - If you remix, transform, or build upon the
 * material, you must distribute your contributions under the same license as
 * the original. # No additional restrictions - You may not apply legal terms or
 * technological measures that legally restrict others from doing anything the
 * license permits.
 *
 * Notices: # You do not have to comply with the license for elements of the
 * material in the public domain or where your use is permitted by an applicable
 * exception or limitation. # No warranties are given. The license may not give
 * you all of the permissions necessary for your intended use. For example,
 * other rights such as publicity, privacy, or moral rights may limit how you
 * use the material.
 *
 * You may obtain a copy of the License at
 * https://creativecommons.org/licenses/by-sa/4.0/
 * https://creativecommons.org/licenses/by-sa/4.0/legalcode
 */
package com.dariawan.jdk9.optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample {

    public static void main(String[] args) {
        List<Optional<Student>> students = Arrays.asList(
                Optional.of(new Student(101, "Reed Richards")),
                Optional.of(new Student(102, "Susan Storm")),
                Optional.of(new Student(103, "Johnny Storm")),
                Optional.of(new Student(104, "Ben Grimm"))
        );

        List<Student> list1 = students.stream()
                .map(Optional::get)
                .collect(Collectors.toList());
        System.out.println(list1);

        Student nullStudent = null;
        List<Optional<Student>> otherStudents = new ArrayList<>();
        otherStudents.addAll(students);
        otherStudents.addAll(Arrays.asList(Optional.ofNullable(nullStudent)));

        List<Student> list2 = otherStudents.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        System.out.println(list2);

        List<Student> list3 = otherStudents.stream()
                .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
                .collect(Collectors.toList());
        System.out.println(list3);
        
        List<String> list4 = otherStudents.stream()
                .flatMap(o -> o.isPresent() ? Stream.of(o.get().getName()) : Stream.empty())
                .map(Optional::get)
                .collect(Collectors.toList());
        System.out.println(list4);
        
        Student student = otherStudents.stream().findFirst()
                .orElse(Optional.of(new Student(999, "Unknown"))).get();
        System.out.println(student);
    }
}
