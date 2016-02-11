package com.wannabe.smartsearch.actions;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.compiler.CompilerManager;
import com.intellij.openapi.project.Project;
import com.wannabe.smartsearch.model.SelectedData;
import com.wannabe.smartsearch.services.ClassNamesTrimService;
import com.wannabe.smartsearch.services.DataTrimService;
import com.wannabe.smartsearch.services.FileNamesTrimService;
import com.wannabe.smartsearch.services.PrettyTrimService;
import com.wannabe.smartsearch.utils.GetClassNamesFunction;
import com.wannabe.smartsearch.utils.GetFilenamesFunction;
import org.jetbrains.annotations.NotNull;

import java.net.URLEncoder;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by wannabe on 10.02.16.
 */
public class SmartSearch extends AnAction {

	private static Collection<DataTrimService> trimServices;

	public static void init(@NotNull Project project) {
		trimServices = new LinkedList<>();
		trimServices.add(ClassNamesTrimService.newInstance(
			new GetClassNamesFunction(project),
			CompilerManager.getInstance(project)
		));
		trimServices.add(FileNamesTrimService.newInstance(
			new GetFilenamesFunction(project),
			CompilerManager.getInstance(project)
		));
		trimServices.add(PrettyTrimService.getInstance());
	}

	@Override
	public void update(AnActionEvent e) {
		boolean enabled = e.getData(CommonDataKeys.EDITOR) != null
			&& e.getRequiredData(CommonDataKeys.EDITOR).getSelectionModel().getSelectedText() != null
			&& e.getProject() != null;
		e.getPresentation().setEnabledAndVisible(enabled);
	}

	@Override
	public void actionPerformed(AnActionEvent e) {
		String selectedData = e.getRequiredData(CommonDataKeys.EDITOR).getSelectionModel().getSelectedText();
		if (selectedData == null || e.getProject() == null) {
			return;
		}
		SelectedData selectedDataWrapper = new SelectedData(selectedData);
		trimServices.stream().forEach(selectedDataWrapper::trim);
		BrowserUtil.browse("http://www.google.com/search?q=" + URLEncoder.encode(selectedDataWrapper.getData()));
	}
}
