/**
 * Java Optional With Examples v1 (https://www.dariawan.com)
 * Copyright (C) 2019 Dariawan <hello@dariawan.com>
 *
 * Creative Commons Attribution-ShareAlike 4.0 International License
 *
 * Under this license, you are free to:
 * # Share - copy and redistribute the material in any medium or format
 * # Adapt - remix, transform, and build upon the material for any purpose,
 *   even commercially.
 *
 * The licensor cannot revoke these freedoms
 * as long as you follow the license terms.
 *
 * License terms:
 * # Attribution - You must give appropriate credit, provide a link to the
 *   license, and indicate if changes were made. You may do so in any
 *   reasonable manner, but not in any way that suggests the licensor
 *   endorses you or your use.
 * # ShareAlike - If you remix, transform, or build upon the material, you must
 *   distribute your contributions under the same license as the original.
 * # No additional restrictions - You may not apply legal terms or
 *   technological measures that legally restrict others from doing anything the
 *   license permits.
 *
 * Notices:
 * # You do not have to comply with the license for elements of the material in
 *   the public domain or where your use is permitted by an applicable exception
 *   or limitation.
 * # No warranties are given. The license may not give you all of
 *   the permissions necessary for your intended use. For example, other rights
 *   such as publicity, privacy, or moral rights may limit how you use
 *   the material.
 *
 * You may obtain a copy of the License at
 *   https://creativecommons.org/licenses/by-sa/4.0/
 *   https://creativecommons.org/licenses/by-sa/4.0/legalcode
 */
package com.dariawan.jdk8.optional;

import java.util.Optional;

public class MapExample {
    
    public static void main(String[] args) {
        Employee emp = new Employee();
        emp.setDepartment(new Department(555, "Digital Transformation"));
        
        /*
        Optional<Department> od = emp.getDepartment();
        Optional<String> name = od.map(Department::getName);
        System.out.println("Department name: " + name);
        */
        Optional<Department> od = emp.getDepartment();
        Optional<Optional<String>> on = od.map(Department::getName);
        System.out.println("Department name: " + on.get());
        
        Optional<String> optName = Optional.ofNullable(null);
        System.out.println("Map value: " + optName.map(String::toUpperCase));
        
        optName = Optional.of("Fintech");
        System.out.println("Map value: " + optName.map(String::toUpperCase));
        
        Optional<Department> dept = Optional.of(new Department(10, "IT"));
        dept.map(Department::getName)
                .filter(nm -> "IT".equals(nm))
                .ifPresent(v -> System.out.println("From IT Department"));
    }
}
