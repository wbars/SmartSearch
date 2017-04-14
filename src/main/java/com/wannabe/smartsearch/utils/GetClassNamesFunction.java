package com.wannabe.smartsearch.utils;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.FileIndexFacade;
import com.intellij.openapi.util.Computable;
import com.intellij.psi.PsiClass;
import com.intellij.psi.search.ProjectScopeImpl;

import java.util.Collection;
import java.util.Set;
import java.util.function.Supplier;

import static com.intellij.openapi.application.ApplicationManager.getApplication;
import static com.intellij.psi.search.searches.AllClassesSearch.search;
import static java.util.stream.Collectors.toSet;

/**
 * Created by wannabe on 10.02.16.
 */
public class GetClassNamesFunction implements Supplier<Collection<String>> {

    private final Project project;

    public GetClassNamesFunction(Project project) {
        this.project = project;
    }

    @Override
    public Set<String> get() {
        return getApplication().runReadAction((Computable<Set<String>>) () ->
                search(new ProjectScopeImpl(project, FileIndexFacade.getInstance(project)), project)
                        .findAll()
                        .stream()
                        .map(PsiClass::getQualifiedName)
                        .collect(toSet()));
    }
}
