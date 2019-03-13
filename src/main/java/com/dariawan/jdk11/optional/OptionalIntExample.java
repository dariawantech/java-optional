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
package com.dariawan.jdk11.optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionalIntExample {

    public static void main(String[] args) {
        OptionalInt optionalInt = OptionalInt.of(888);
        System.out.println(888 == optionalInt.getAsInt());
        
        optionalInt = OptionalInt.empty();
        System.out.println(optionalInt.orElse(999));
        
        List<OptionalInt> integers = Arrays.asList(
                OptionalInt.of(101),
                OptionalInt.of(212),
                OptionalInt.of(888),
                OptionalInt.of(999)
        );

        List<Integer> list1 = integers.stream()
                .map(OptionalInt::getAsInt)
                .collect(Collectors.toList());
        System.out.println(list1);

        List<OptionalInt> otherInts = new ArrayList<>();
        otherInts.addAll(integers);
        otherInts.addAll(Arrays.asList(OptionalInt.empty()));
        
        List<Integer> list2 = otherInts.stream()
                .filter(OptionalInt::isPresent)
                .filter(o -> o.getAsInt() > 500)
                .map(OptionalInt::getAsInt)
                .collect(Collectors.toList());
        System.out.println(list2);

        List<String> list3 = otherInts.stream()
                .flatMap(o -> o.isPresent() ? Stream.of(o.getAsInt()) : Stream.of(Integer.valueOf("500")))
                .map(i -> Integer.toString(i))
                .collect(Collectors.toList());
        System.out.println(list3);        
        
        Integer i = integers.stream().findFirst()
                .orElse(OptionalInt.of(500)).getAsInt();
        System.out.println(i);
    }
}
