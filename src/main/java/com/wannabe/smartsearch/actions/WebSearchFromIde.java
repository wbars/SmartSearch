package com.wannabe.smartsearch.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.Pair;
import com.wannabe.smartsearch.utils.TrimServiceEngine;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.wannabe.smartsearch.actions.SmartSearch.doSearch;

/**
 * Created by wannabe on 29.02.16.
 */
public class WebSearchFromIde extends AnAction {

	private static TrimServiceEngine trimServiceEngine;

	public static void init(@NotNull TrimServiceEngine trimServiceEngine) {
		WebSearchFromIde.trimServiceEngine = trimServiceEngine;
	}

	@Override
	public void actionPerformed(@Nullable AnActionEvent e) {
		if (e == null) {
			return;
		}
		final Project project = e.getData(PlatformDataKeys.PROJECT);
		Pair<String, Boolean> request = Messages.showInputDialogWithCheckBox("Do web search",
			"SmartSearch",
			"Apply smart search filter",
			false,
			true,
			null, null, null);
		String query = request.getFirst();

		if (query == null) {
			return;
		}
		if (query.length() == 0) {
			Messages.showErrorDialog(project, "Input something =)", "Can't Do Search");
			return;
		}
		if (request.getSecond()) {
			query = trimServiceEngine.trimData(query);
		}
		doSearch(query);
	}
}
