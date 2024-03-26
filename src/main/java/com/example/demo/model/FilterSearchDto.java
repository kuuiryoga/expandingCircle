package com.example.demo.model;

import java.io.Serializable;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


@Component
@SessionScope
public class FilterSearchDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String searchText;
	
	private Map<String,String> uniqueText;

	private Integer sortId;

	public void setDto(FilterSearchDto dto) {
		this.searchText = dto.getSearchText();
		this.uniqueText = dto.getUniqueText();
		this.sortId = dto.getSortId();
	}

	public Integer getSortId() {
		return sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public Map<String, String> getUniqueText() {
		return uniqueText;
	}

	public void setUniqueText(Map<String, String> uniqueText) {
		this.uniqueText = uniqueText;
	}
}
