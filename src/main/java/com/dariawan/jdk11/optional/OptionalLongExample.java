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
import java.util.OptionalLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionalLongExample {

    public static void main(String[] args) {
        OptionalLong optionalLong = OptionalLong.of(2147483648l);
        System.out.println(2147483648l == optionalLong.getAsLong());
        
        optionalLong = OptionalLong.empty();
        System.out.println(optionalLong.orElse(2147484646l));
        
        List<OptionalLong> longs = Arrays.asList(
                OptionalLong.of(2147483748l),
                OptionalLong.of(2147483859l),
                OptionalLong.of(2147484535l),
                OptionalLong.of(2147484646l)
        );

        List<Long> list1 = longs.stream()
                .map(OptionalLong::getAsLong)
                .collect(Collectors.toList());
        System.out.println(list1);

        List<OptionalLong> otherLongs = new ArrayList<>();
        otherLongs.addAll(longs);
        otherLongs.addAll(Arrays.asList(OptionalLong.empty()));
        
        List<Long> list2 = otherLongs.stream()
                .filter(OptionalLong::isPresent)
                .filter(o -> o.getAsLong() > 2147484147l)
                .map(OptionalLong::getAsLong)
                .collect(Collectors.toList());
        System.out.println(list2);

        List<String> list3 = otherLongs.stream()
                .flatMap(o -> o.isPresent() ? Stream.of(o.getAsLong()) : Stream.of(Long.MAX_VALUE))
                .map(l -> Long.toString(l))
                .collect(Collectors.toList());
        System.out.println(list3);
        
        
        Long l = longs.stream().findFirst()
                .orElse(OptionalLong.of(Long.MAX_VALUE)).getAsLong();
        System.out.println(l);
    }
}
