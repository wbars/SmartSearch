package com.wannabe.smartsearch.services;

import com.intellij.openapi.compiler.CompilerManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.concurrent.Callable;

/**
 * Created by wannabe on 11.02.16.
 */
public class FileNamesTrimService extends FileCrawlerTrimService {

	public FileNamesTrimService(Callable<Set<String>> fileNameGetter, @Nullable CompilerManager compilerManager) {
		super(fileNameGetter, compilerManager);
	}

	@Override
	public String removeFaceContent(@NotNull String data) {
		return removeFaceContent(data, s -> s + "\\.java(:\\d+)?");
	}
}
