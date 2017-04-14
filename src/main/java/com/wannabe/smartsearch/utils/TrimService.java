package com.wannabe.smartsearch.utils;

import com.intellij.openapi.compiler.CompilerManager;
import com.intellij.openapi.project.Project;
import com.wannabe.smartsearch.services.ClassNamesTrimService;
import com.wannabe.smartsearch.services.FileNamesTrimService;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

import static java.util.function.Function.identity;
import static java.util.stream.Stream.of;

/**
 * Created by wannabe on 01.03.16.
 */
public final class TrimService {

    private final Function<String, String> trimmer;


    public TrimService(@NotNull Project project) {
        trimmer = of(classNameFunction(project), fileNameFunction(project), String::trim)
                .reduce((d, d1) -> s -> d.apply(d1.apply(s))).orElse(identity());
    }

    @NotNull
    private Function<String, String> fileNameFunction(@NotNull Project project) {
        return FileNamesTrimService.newInstance(
                new GetFilenamesFunction(project),
                CompilerManager.getInstance(project)
        );
    }

    @NotNull
    private Function<String, String> classNameFunction(@NotNull Project project) {
        return ClassNamesTrimService.newInstance(
                new GetClassNamesFunction(project),
                CompilerManager.getInstance(project)
        );
    }

    public final String trim(@NotNull String input) {
        return trimmer.apply(input);
    }
}
