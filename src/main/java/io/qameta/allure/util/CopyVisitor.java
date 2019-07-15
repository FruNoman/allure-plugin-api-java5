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
package io.qameta.allure.util;



import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Visitor that recursive copies directories.
 *
 * @since 2.0
 */
public class CopyVisitor extends SimpleFileVisitor<Path> {


    private final Path sourceDirectory;

    private final Path outputDirectory;

    public CopyVisitor(final Path sourceDirectory, final Path outputDirectory) {
        this.sourceDirectory = sourceDirectory;
        this.outputDirectory = outputDirectory;
    }

    @Override
    public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs) throws IOException {
        try {
            Files.createDirectories(dir);
        } catch (IOException e) {
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
        if (Files.notExists(file)) {
            return FileVisitResult.CONTINUE;
        }
        final Path dest = outputDirectory.resolve(sourceDirectory.relativize(file));
        try {
            Files.copy(file, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
        }
        return FileVisitResult.CONTINUE;
    }
}
