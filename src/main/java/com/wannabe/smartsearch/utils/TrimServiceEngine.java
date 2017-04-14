package com.wannabe.smartsearch.utils;

import com.intellij.openapi.compiler.CompilerManager;
import com.intellij.openapi.project.Project;
import com.wannabe.smartsearch.model.SelectedData;
import com.wannabe.smartsearch.services.ClassNamesTrimService;
import com.wannabe.smartsearch.services.DataTrimService;
import com.wannabe.smartsearch.services.FileNamesTrimService;
import com.wannabe.smartsearch.services.PrettyTrimService;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by wannabe on 01.03.16.
 */
// Must me initialized only once during app bootstrap and injected as needed. Stateless.
public final class TrimServiceEngine {

    private final Collection<DataTrimService> trimServices;

    public TrimServiceEngine(@NotNull Project project) {
        Collection<DataTrimService> trimServices = new LinkedList<>();
        trimServices.add(ClassNamesTrimService.newInstance(
                new GetClassNamesFunction(project),
                CompilerManager.getInstance(project)
        ));
        trimServices.add(FileNamesTrimService.newInstance(
                new GetFilenamesFunction(project),
                CompilerManager.getInstance(project)
        ));
        trimServices.add(PrettyTrimService.INSTANCE);
        this.trimServices = trimServices;

    }

    public final String trimData(@NotNull String input) {
        SelectedData selectedDataWrapper = new SelectedData(input);
        trimServices.stream().forEach(selectedDataWrapper::trim);
        return selectedDataWrapper.getData();
    }
}
