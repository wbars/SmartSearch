package com.wannabe.smartsearch.model;

import com.wannabe.smartsearch.services.DataTrimService;

/**
 * Created by wannabe on 10.02.16.
 */
public class SelectedData {

	private String data;

	public SelectedData(String data) {
		this.data = data;
	}

	public SelectedData trim(DataTrimService dataTrimService) {
		data = dataTrimService.removeFaceContent(data);
		return this;
	}

	public String getData() {
		return data;
	}
}
