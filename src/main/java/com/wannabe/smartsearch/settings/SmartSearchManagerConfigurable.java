package com.wannabe.smartsearch.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.apache.commons.lang.WordUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by wannabe on 14.02.16.
 */
public class SmartSearchManagerConfigurable implements Configurable {

	public static final Map<SearchHost, String> searchHostsPrefixes;
	private static final Object counterLock = new Object();
	private static volatile SearchHost currentHost = SearchHost.GOOGLE;

	static {
		Map<SearchHost, String> prefixes = new TreeMap<>();
		prefixes.put(SearchHost.GOOGLE, "http://www.google.com/search?q=");
		prefixes.put(SearchHost.YANDEX, "https://yandex.ru/search/?text=");
		prefixes.put(SearchHost.YAHOO, "https://search.yahoo.com/search?p=");
		prefixes.put(SearchHost.BING, "https://www.bing.com/search?q=");

		searchHostsPrefixes = Collections.unmodifiableMap(prefixes);
	}

	private JComboBox searchHost;
	@SuppressWarnings({"unused", "unread"})
	private JPanel searchHostPanel;
	private JPanel root;
	@SuppressWarnings({"unused", "unread"})
	private JLabel searchHostLabel;
	@SuppressWarnings({"unused", "unread"})
	private JPanel panel;

	public static String getCurrentPrefix() {
		return searchHostsPrefixes.get(currentHost);
	}

	@Nls
	@Override
	public String getDisplayName() {
		return "Smart search";
	}

	@Nullable
	@Override
	public String getHelpTopic() {
		return null;
	}

	@Nullable
	@Override
	public JComponent createComponent() {
		searchHostsPrefixes.keySet().stream()
			.forEachOrdered(searchHost::addItem);
		searchHost.setSelectedItem(currentHost);
		return root;
	}

	@Override
	public boolean isModified() {
		return !searchHost.getSelectedItem().equals(currentHost);
	}

	@Override
	public void apply() throws ConfigurationException {
		SearchHost selectedKey = (SearchHost) searchHost.getSelectedItem();
		if (selectedKey != null && searchHostsPrefixes.containsKey(selectedKey)) {
			synchronized (counterLock) {
				currentHost = selectedKey;
			}
		}
	}

	@Override
	public void reset() {

	}

	@Override
	public void disposeUIResources() {

	}

	public enum SearchHost {
		GOOGLE, YANDEX, YAHOO, BING;

		@Override
		public String toString() {
			return WordUtils.capitalizeFully(name());
		}
	}
}
