package com.wannabe.smartsearch.services;

import com.intellij.openapi.compiler.CompilerManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Created by wannabe on 11.02.16.
 */
public class FileNamesTrimService extends FileCrawlerTrimService {

    private FileNamesTrimService(Supplier<Collection<String>> fileNameGetter, @Nullable CompilerManager compilerManager) {
        super(fileNameGetter, compilerManager);
    }

    public static FileNamesTrimService newInstance(Supplier<Collection<String>> fileNameGetter, @Nullable CompilerManager compilerManager) {
        return new FileNamesTrimService(fileNameGetter, compilerManager);
    }

    @Override
    public String apply(@NotNull String data) {
        return removeFaceContent(data, s -> "^" + s + "\\.\\w+(:\\d+)?$");
    }
}
