package com.wannabe.smartsearch.services;

import org.jetbrains.annotations.NotNull;

/**
 * Created by wannabe on 10.02.16.
 */

/**
 * Interface provide only one method for trimming input data
 * <br />
 * <br />
 * For register new TrimService you should implement this interface and add new instance to Smart Search action via init method
 * <br />
 * <br />
 * Implementation should be thread-safe.
 *
 * @see com.wannabe.smartsearch.actions.SmartSearch
 */
public interface DataTrimService {

    /**
     * This method should remove remove some text (or none) from input
     *
     * @param data Data to be trimmed
     * @return trimmed data
     */
    String removeFaceContent(@NotNull String data);
}
