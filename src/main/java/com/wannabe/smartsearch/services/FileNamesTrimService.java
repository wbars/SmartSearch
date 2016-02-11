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

	private FileNamesTrimService(Callable<Set<String>> fileNameGetter, @Nullable CompilerManager compilerManager) {
		super(fileNameGetter, compilerManager);
	}

	public static DataTrimService newInstance(Callable<Set<String>> fileNameGetter, @Nullable CompilerManager compilerManager) {
		return new FileNamesTrimService(fileNameGetter, compilerManager);
	}

	@Override
	public String removeFaceContent(@NotNull String data) {
		return removeFaceContent(data, s -> s + "\\.java(:\\d+)?");
	}
}
