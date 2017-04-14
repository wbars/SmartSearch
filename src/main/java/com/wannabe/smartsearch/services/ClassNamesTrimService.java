package com.wannabe.smartsearch.services;

import com.intellij.openapi.compiler.CompilerManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by wannabe on 10.02.16.
 */
public class ClassNamesTrimService extends FileCrawlerTrimService {

    private ClassNamesTrimService(final Supplier<Collection<String>> classNamesGetter, @Nullable CompilerManager compilerManager) {
        super(classNamesGetter, compilerManager);
    }

    public static Function<String, String> newInstance(final Supplier<Collection<String>> classNamesGetter, @Nullable CompilerManager compilerManager) {
        return new ClassNamesTrimService(classNamesGetter, compilerManager);
    }

    @Override
    public String apply(@NotNull String data) {
        return removeFaceContent(data, s -> s + "(\\.\\w+\\(.*\\))?");
    }

}
