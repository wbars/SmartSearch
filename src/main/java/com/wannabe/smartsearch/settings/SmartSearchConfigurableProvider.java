package com.wannabe.smartsearch.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurableProvider;
import org.jetbrains.annotations.Nullable;

/**
 * Created by wannabe on 14.02.16.
 */
public class SmartSearchConfigurableProvider extends ConfigurableProvider {

	@Nullable
	@Override
	public Configurable createConfigurable() {
		return new SmartSearchManagerConfigurable();
	}
}
