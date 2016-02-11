package com.wannabe.smartsearch;

import com.intellij.openapi.components.ProjectComponent;
import org.jetbrains.annotations.NotNull;

/**
 * Created by wannabe on 10.02.16.
 */
public class SmartSearchComponent implements ProjectComponent {

	@Override
	@NotNull
	public String getComponentName() {
		return "SmartSearchComponent";
	}

	@Override
	public void projectOpened() {
	}

	@Override
	public void projectClosed() {

	}

	@Override
	public void initComponent() {
	}

	@Override
	public void disposeComponent() {

	}
}
