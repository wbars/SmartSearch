package com.wannabe.smartsearch.utils;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.FileIndexFacade;
import com.intellij.openapi.util.Computable;
import com.intellij.psi.PsiClass;
import com.intellij.psi.search.ProjectScopeImpl;
import com.intellij.psi.search.searches.AllClassesSearch;

import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * Created by wannabe on 10.02.16.
 */
public class GetClassNamesFunction implements Callable<Set<String>> {

	public static final Logger logger = Logger.getInstance(GetClassNamesFunction.class);

	private final Project project;

	public GetClassNamesFunction(Project project) {
		this.project = project;
	}

	@Override
	public Set<String> call() throws Exception {
		logger.info("Start fetching class names");
		return ApplicationManager.getApplication().runReadAction((Computable<Set<String>>) () ->
			AllClassesSearch.search(new ProjectScopeImpl(project, FileIndexFacade.getInstance(project)), project)
				.findAll()
				.stream()
				.map(PsiClass::getQualifiedName)
				.collect(Collectors.toSet()));
	}
}
