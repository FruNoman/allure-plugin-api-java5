/*
 *  Copyright 2019 Qameta Software OÜ
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.qameta.allure.entity;

import java8.util.Objects;
import java8.util.function.Predicate;
import java8.util.function.Supplier;
import java8.util.stream.RefStreams;
import java8.util.stream.Stream;

/**
 * Contains utils for generated entities.
 *
 * @since 2.0
 */
/*package private*/ final class EntityUtils {

    private EntityUtils() {
        throw new IllegalStateException("Do not instance");
    }

    @SafeVarargs
    public static <T> T firstNonNull(final T... items) {
        return RefStreams.of(items)
                .filter(new Predicate<T>() {
                    @Override
                    public boolean test(T t) {
                        return Objects.nonNull(t);
                    }
                })
                .findFirst().get();
//                .orElseThrow(new Supplier<Throwable>() {
//                    @Override
//                    public Throwable get() {
//                        return new IllegalStateException("At least one argument should be not null");
//                    }
//                });
    }
}
