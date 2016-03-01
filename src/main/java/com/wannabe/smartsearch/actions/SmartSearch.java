package com.wannabe.smartsearch.actions;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.wannabe.smartsearch.settings.SmartSearchManagerConfigurable;
import com.wannabe.smartsearch.utils.TrimServiceEngine;
import org.jetbrains.annotations.NotNull;

import java.net.URLEncoder;

/**
 * Created by wannabe on 10.02.16.
 */
public class SmartSearch extends AnAction {

	private static TrimServiceEngine trimServiceEngine;

	public static void init(@NotNull TrimServiceEngine trimServiceEngine) {
		SmartSearch.trimServiceEngine = trimServiceEngine;
	}

	static void doSearch(@NotNull String query) {
		BrowserUtil.browse(SmartSearchManagerConfigurable.getCurrentPrefix() + URLEncoder.encode(query));
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
		doSearch(trimServiceEngine.trimData(selectedData));
	}
}
