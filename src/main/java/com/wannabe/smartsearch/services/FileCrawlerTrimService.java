package com.wannabe.smartsearch.services;

import com.intellij.openapi.compiler.CompilationStatusListener;
import com.intellij.openapi.compiler.CompileContext;
import com.intellij.openapi.compiler.CompilerManager;
import com.intellij.openapi.diagnostic.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * Created by wannabe on 11.02.16.
 */
public abstract class FileCrawlerTrimService implements DataTrimService {

	public static final Logger logger = Logger.getInstance(FileCrawlerTrimService.class);
	protected static final ExecutorService executor = Executors.newFixedThreadPool(2);
	protected Future<Set<String>> names;

	/**
	 * todo make compilerManager not null, now nullable because of tests
	 */
	protected FileCrawlerTrimService(final Callable<Set<String>> classNamesGetter, @Nullable CompilerManager compilerManager) {
		names = executor.submit(classNamesGetter);

		if (compilerManager != null) {
			compilerManager.addCompilationStatusListener(new CompilationStatusListener() {
				@Override
				public void compilationFinished(boolean aborted, int errors, int warnings, CompileContext compileContext) {
					names = executor.submit(classNamesGetter);
				}

				@Override
				public void fileGenerated(String outputRoot, String relativePath) {
					//do nothing
				}
			});
		} else {
			logger.warn("Cant register listener for compiler manager: compile manager is null");
		}
	}

	protected String removeFaceContent(@NotNull String data, Function<String, String> regexpGetter) {
		final String[] wrapper = {data};
		try {
			names
				.get()
				.forEach(s -> wrapper[0] = wrapper[0]
					.replaceAll(regexpGetter.apply(s), "")
				);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			names.cancel(true);
		} catch (ExecutionException e) {
			//todo
		}
		return wrapper[0];
	}
}
