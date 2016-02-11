package com.wannabe.smartsearch.utils;

import com.intellij.openapi.application.ApplicationManager;
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
 * Created by wannabe on 11.02.16.
 */
public class GetFilenamesFunction implements Callable<Set<String>> {

	private final Project project;

	public GetFilenamesFunction(Project project) {
		this.project = project;
	}

	@Override
	public Set<String> call() throws Exception {
		return ApplicationManager.getApplication().runReadAction((Computable<Set<String>>) () ->
			AllClassesSearch.search(new ProjectScopeImpl(project, FileIndexFacade.getInstance(project)), project)
				.findAll()
				.stream()
				.map(PsiClass::getName)
				.collect(Collectors.toSet()));
	}
}
