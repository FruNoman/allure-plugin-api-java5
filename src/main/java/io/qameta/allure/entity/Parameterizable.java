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

import java.util.List;

import java8.util.function.Function;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

/**
 * @author charlie (Dmitry Baev).
 */
public interface Parameterizable {

    List<Parameter> getParameters();

    default List<String> getParameterValues() {
        return StreamSupport.stream(getParameters())
                .map(new Function<Parameter, String>() {
                    @Override
                    public String apply(Parameter parameter) {
                        return parameter.getValue();
                    }
                })
                .collect(Collectors.toList());
    }
}
