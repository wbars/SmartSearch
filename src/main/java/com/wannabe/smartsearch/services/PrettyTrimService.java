package com.wannabe.smartsearch.services;

import org.jetbrains.annotations.NotNull;

/**
 * Created by wannabe on 11.02.16.
 */
public enum PrettyTrimService implements DataTrimService {
    INSTANCE;

    @Override
    public String removeFaceContent(@NotNull String data) {
        return data
                .trim()
                .replaceAll("\\s+", " ");
    }
}
