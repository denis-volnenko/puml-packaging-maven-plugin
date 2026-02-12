package ru.volnenko.maven.plugin.puml.parser;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class RootParser {

    private File file;

    @NonNull
    public RootParser file(@NonNull final File file) {
        this.file = file;
        return this;
    }

    private static List<String> files(final String folderPathString) {
        final Path rootFolder = Paths.get(folderPathString);
        final List<String> result = new ArrayList<>();
        try ( final Stream<Path> paths = Files.walk(rootFolder)) {
            final List<Path> allFiles = paths.filter(new Predicate<Path>() {
                @Override
                public boolean test(final Path path) {
                    return Files.isRegularFile(path);
                }
            }).collect(Collectors.toList());
            for ( final Path file : allFiles) {
                result.add(file.toAbsolutePath().toString());
            }
            return result;
        } catch ( final IOException e) {
            return Collections.emptyList();
        }
    }

    @SneakyThrows
    public void save(@NonNull final File build) {
        FileUtils.fileWrite(build, text());
    }

    @SneakyThrows
    public String text() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final String file: files(file.getAbsolutePath())) {
            stringBuilder.append(FileUtils.fileRead(file)).append("\n").append("\n");
        }
        return stringBuilder.toString();
    }
    
}
