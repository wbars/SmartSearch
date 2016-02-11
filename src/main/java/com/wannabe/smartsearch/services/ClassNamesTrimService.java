package com.wannabe.smartsearch.services;

import com.intellij.openapi.compiler.CompilerManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.concurrent.Callable;

/**
 * Created by wannabe on 10.02.16.
 */
public class ClassNamesTrimService extends FileCrawlerTrimService {

	private ClassNamesTrimService(final Callable<Set<String>> classNamesGetter, @Nullable CompilerManager compilerManager) {
		super(classNamesGetter, compilerManager);
	}

	public static DataTrimService newInstance(final Callable<Set<String>> classNamesGetter, @Nullable CompilerManager compilerManager) {
		return new ClassNamesTrimService(classNamesGetter, compilerManager);
	}

	public String removeFaceContent(@NotNull String data) {
		return removeFaceContent(data, s -> s + "(\\.\\w+\\(.*\\))?");
	}

}
