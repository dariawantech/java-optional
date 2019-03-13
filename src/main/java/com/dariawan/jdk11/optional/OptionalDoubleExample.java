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
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionalDoubleExample {

    public static void main(String[] args) {
        OptionalDouble optionalDouble = OptionalDouble.of(8.88);
        System.out.println(8.88 == optionalDouble.getAsDouble());
        
        optionalDouble = OptionalDouble.empty();
        System.out.println(optionalDouble.orElse(9.99));
        
        List<OptionalDouble> doubles = Arrays.asList(
                OptionalDouble.of(1.01),
                OptionalDouble.of(2.12),
                OptionalDouble.of(8.88),
                OptionalDouble.of(9.99)
        );

        List<Double> list1 = doubles.stream()
                .map(OptionalDouble::getAsDouble)
                .collect(Collectors.toList());
        System.out.println(list1);

        List<OptionalDouble> otherDoubles = new ArrayList<>();
        otherDoubles.addAll(doubles);
        otherDoubles.addAll(Arrays.asList(OptionalDouble.empty()));
        
        List<Double> list2 = otherDoubles.stream()
                .filter(OptionalDouble::isPresent)
                .filter(o -> o.getAsDouble() > 5.05)
                .map(OptionalDouble::getAsDouble)
                .collect(Collectors.toList());
        System.out.println(list2);

        List<String> list3 = otherDoubles.stream()
                .flatMap(o -> o.isPresent() ? Stream.of(o.getAsDouble()) : Stream.of(Double.valueOf("5.05")))
                .map(d -> Double.toString(d))
                .collect(Collectors.toList());
        System.out.println(list3);        
        
        Double d = doubles.stream().findFirst()
                .orElse(OptionalDouble.of(5.05)).getAsDouble();
        System.out.println(d);
    }
}
